grammar IsiLang;

@header{
	import isilanguage.datastructures.IsiSymbol;
	import isilanguage.datastructures.IsiVariable;
	import isilanguage.datastructures.IsiSymbolTable;
	import isilanguage.datastructures.IsiTerm;
	import isilanguage.datastructures.IsiTypes;
	import isilanguage.datastructures.IsiOperator;
	import isilanguage.datastructures.AbstractExpression;
	import isilanguage.datastructures.NumericExpressionBuilder;
	import isilanguage.datastructures.TextualExpressionBuilder;
	import isilanguage.datastructures.RelationalExpressionBuilder;
	import isilanguage.datastructures.BooleanExpressionBuilder;
	import isilanguage.exceptions.IsiSemanticException;
	import isilanguage.ast.IsiProgram;
	import isilanguage.ast.AbstractCommand;
	import isilanguage.ast.CommandLeitura;
	import isilanguage.ast.CommandEscrita;
	import isilanguage.ast.CommandAtribuicao;
	import isilanguage.ast.CommandDecisao;
	import isilanguage.ast.CommandEnquanto;
	import isilanguage.ast.CommandFaca;
	import isilanguage.ast.CommandFor;
	import isilanguage.ast.CommandComentario;
	import java.util.ArrayList;
	import java.util.Stack;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private String _readID;
	private String _writeID;
	private String _attribID;
	private String _attribTerm;
	private IsiTerm _term = null; 
	private IsiTerm _newTerm = null;
	private String op, sign;
	private int type;
	private String _exprID;
	private String _exprContent;
	private String _exprAttrib;
	private String _exprDecision;
	private String _exprStep;
	private String _textComment;
	private int _tipoVariavel;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	private ArrayList<AbstractCommand> commands;
	private AbstractExpression _expr;
	private int _exprType;
	private String bool, _booleanExpr, _partialBooleanExpr, _condition;
	
	public void verificaID(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		}
	}
	
	public void atribuiVariavel(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		} else {
			IsiSymbol var = symbolTable.get(id);
			if (var instanceof IsiVariable){
				((IsiVariable) var).initializeVariable();
				symbolTable.add(var);
			} 
			
		}
	}
	
	public void useVariavel(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		} else {
			IsiSymbol var = symbolTable.get(id);
			if (var instanceof IsiVariable){
				((IsiVariable) var).useVariable();
				symbolTable.add(var);
			} 
			
		}
	}
	
	public void verificaAtribuicao(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		} else {
			IsiSymbol var = symbolTable.get(id);
			if (var instanceof IsiVariable && !((IsiVariable) var).wasAttributed()){
				throw new IsiSemanticException("Symbol "+id+" is used but not declared");
			} 
			
		}
	}
	
	public int obtemTipoId(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		} else {
			IsiSymbol var = symbolTable.get(id);
			if (var instanceof IsiVariable){
				return ((IsiVariable) var).getType();
			} 
			return -1;
			
		}
	}
	
	public IsiTerm atualizaTipoTermo(String termo, IsiTerm _term, int tipo, String op){
		 /* Verificação de tipo*/
	     if (_term == null){
	     	return  new IsiTerm(termo, tipo);
	     } else {
	        IsiTerm newTerm = new IsiTerm(termo, tipo);
	        return _term.checkTypeExpression(op, newTerm);
	     }
	}
	
	public void verificaTipoAtribuicao(String id, IsiTerm term){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		} else {
			IsiSymbol var = symbolTable.get(id);
			if (var instanceof IsiVariable){
				IsiTerm.checkAttributionType((IsiVariable) var, term);
			} 
			
		}
	}
	
	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}
	
	public void generateCode(){
		program.generateTargetPrettyPrinter();
	}
}

prog	: 'programa' decl bloco  'fimprog.'
           {  program.setVarTable(symbolTable);
           	  program.setComandos(stack.pop());
           	  symbolTable.verifyVariables();
           } 
		;
		
decl    :  (declaravar)+
        ;
        
        
declaravar :  'declare' tipo ID  {
	                              _varName = _input.LT(-1).getText();
	                              _varValue = null;
	                              symbol = new IsiVariable(_varName, _tipo, _varValue);
	                              if (!symbolTable.exists(_varName)){
	                                symbolTable.add(symbol);	
	                              }
	                              else{
	                  	            throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                              }
                                 } 
                            ( VIR 
              	             ID {
	                             _varName = _input.LT(-1).getText();
	                             _varValue = null;
	                             symbol = new IsiVariable(_varName, _tipo, _varValue);
	                             if (!symbolTable.exists(_varName)){
	                               symbolTable.add(symbol);	
	                             }
	                             else{
	                  	           throw new IsiSemanticException("Symbol "+_varName+" already declared");
	                             }
                                }
                            )* 
                            POINT
           ;
           
tipo       : 'numero' { _tipo = IsiVariable.NUMBER;  }
           | 'texto'  { _tipo = IsiVariable.TEXT;  }
           | 'booleano' { _tipo = IsiVariable.BOOLEAN;  }
           ;
        
bloco	: { curThread = new ArrayList<AbstractCommand>(); 
	        stack.push(curThread);  
          }
          (cmd)+
		;
		

cmd		:  cmdleitura  
 		|  cmdescrita 
 		|  cmdattrib
 		|  cmdselecao  
 		|  cmdrepeticao
 		|  comentarios
		;
		
cmdleitura	: 'leia' AP
                     ID { verificaID(_input.LT(-1).getText());
                     	  _readID = _input.LT(-1).getText();
                     	  atribuiVariavel(_readID);
                        } 
                     FP 
                     POINT 
                     
              {
              	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
              	CommandLeitura cmd = new CommandLeitura(_readID, var);
              	stack.peek().add(cmd);
              }   
			;
			
cmdescrita	: 'escreva' 
                 AP 
                 ( TEXTO {_writeID = _input.LT(-1).getText();}
                 | ID { verificaID(_input.LT(-1).getText());
	                    _writeID = _input.LT(-1).getText();
	                    useVariavel(_writeID);
                       }
                  ) 
                 FP 
                 POINT
               {
               	  CommandEscrita cmd = new CommandEscrita(_writeID);
               	  stack.peek().add(cmd);
               }
			;
			
cmdattrib	:  ID { verificaID(_input.LT(-1).getText());
                    _exprID = _input.LT(-1).getText();
                    
                    _exprType = obtemTipoId(_exprID);
                    
                    if(_exprType == IsiTypes.NUMBER){
                    	_expr = new NumericExpressionBuilder();
                    } else if (_exprType == IsiTypes.BOOLEAN){
                    	_expr = new BooleanExpressionBuilder();
                    } else if (_exprType == IsiTypes.TEXT){
                    	_expr = new TextualExpressionBuilder();
                    }
                    
                   } 
               ATTR { _exprContent = ""; } 
               (expr
               | condicao {_exprContent = _condition;}
               )
               POINT
               { 
                 //_exprContent = _expr.getExpression();
               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
               	 atribuiVariavel(_exprID);
               	 stack.peek().add(cmd);
               	 //verificaTipoAtribuicao(_exprID, _term);
               	 _term = null;
               	 op = null;
               }
			;
			
			
cmdselecao  :  'se' AP
                    condicao {_exprDecision = _condition;}
                    FP 
                    'entao'
                    ACH 
                    { curThread = new ArrayList<AbstractCommand>(); 
                      stack.push(curThread);
                    }
                    (cmd)+ 
                    FCH 
                    {
                       listaTrue = stack.pop();	
                       listaFalse = new ArrayList<AbstractCommand>();
                   	   CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
                    } 
                   ('senao' 
                   	 ACH
                   	 {
                   	 	curThread = new ArrayList<AbstractCommand>();
                   	 	stack.push(curThread);
                   	 } 
                   	(cmd+) 
                   	FCH
                   	{
                   		listaFalse = stack.pop();
                   		cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
                   	}
                   )? 
                   {
                   		stack.peek().add(cmd);
                   }
                   	
                   
            ; 
            
cmdrepeticao : 'enquanto' AP
                   		  condicao {_exprDecision = _condition;
                   		            System.out.println(_exprDecision);}
                    	  FP 
                          ACH
                          { curThread = new ArrayList<AbstractCommand>(); 
                      	  	stack.push(curThread);
                   		  }
                          (cmd)+
                          FCH
                          {
                        	commands = stack.pop();	
                    	  }
                    	  {
                   			CommandEnquanto cmd = new CommandEnquanto(_exprDecision, commands);
                   			stack.peek().add(cmd);
                   		  }
             	|
             	
             	'faca' 	  ACH
                       	  { curThread = new ArrayList<AbstractCommand>(); 
                       	 	stack.push(curThread);
                   	   	  }
                       	  (cmd)+
                       	  FCH
                       	  {
                         	commands = stack.pop();	
                       	  } 
                'enquanto' AP
                   		   condicao {_exprDecision = _condition;}
                    	   FP
                    	   POINT
                       {
                   	   	  CommandFaca cmd = new CommandFaca(_exprDecision, commands);
                   		  stack.peek().add(cmd);
                   	   }
                   	   
				|
				'para' 	  AP {_exprAttrib = "";}
						  (
							(
							(tipo ID {_varName = _input.LT(-1).getText();
			                          _varValue = null;
			                              symbol = new IsiVariable(_varName, _tipo, _varValue);
			                              
			                              if (!symbolTable.exists(_varName)){
			                                symbolTable.add(symbol);	
			                                _exprAttrib = _varName;
			                              }
			                              else{
			                  	            throw new IsiSemanticException("Symbol "+_varName+" already declared");
			                              }
			                              
			                           
			                            }
	                            ATTR  { _exprAttrib += _input.LT(-1).getText();}
								(ID  { _varName = _input.LT(-1).getText();
										if (!symbolTable.exists(_varName)){
			                                throw new IsiSemanticException("Symbol "+_varName+" not declared");
			                              }
									 }
								
								| (NUMBER | SIGNEDNUMBER)) {_exprContent =_input.LT(-1).getText(); _exprAttrib +=_exprContent;  }
								
								{
                                   	 
					               	 atribuiVariavel(_varName);

					               	 
					               	 _term = null;
					               	 op = null;
					            }
			                            
								) | 
								(
							ID   { _varName = _input.LT(-1).getText();
									if (!symbolTable.exists(_varName)){
			                             throw new IsiSemanticException("Symbol "+_varName+" not declared");
			                        }
			                        else{
			                            _exprAttrib = _varName;
			                        }
							     }
							)
							ATTR  { _exprAttrib += _input.LT(-1).getText();}
							(ID  { _varName = _input.LT(-1).getText();
									if (!symbolTable.exists(_varName)){
			                            throw new IsiSemanticException("Symbol "+_varName+" not declared");
			                        }
								 }
							| NUMBER) {_exprAttrib +=_input.LT(-1).getText();}
							))?
							POINT {_exprDecision = "";}
							(     
							ID    { _varName = _input.LT(-1).getText();
										if (!symbolTable.exists(_varName)){
			                                throw new IsiSemanticException("Symbol "+_varName+" not declared");
			                              }
			                              else{
			                  	            _exprDecision += _varName;
			                              }
								   }
							OPREL { _exprDecision += _input.LT(-1).getText(); }
							(ID { _varName = _input.LT(-1).getText();
										if (!symbolTable.exists(_varName)){
			                                throw new IsiSemanticException("Symbol "+_varName+" not declared");
			                            }
								}
							| (NUMBER | SIGNEDNUMBER)) {_exprDecision +=_input.LT(-1).getText(); }
							)?  { _exprStep = "";}
							POINT   
							(
							ID    { _varName = _input.LT(-1).getText();
										if (!symbolTable.exists(_varName)){
			                                throw new IsiSemanticException("Symbol "+_varName+" not declared");
			                            }
								  }
								  { _exprStep += _input.LT(-1).getText();}
							(	OP_INC_DEC { _exprStep += _input.LT(-1).getText();}
							
								| 	( 	OP_INC_DEC_EQ { _exprStep += _input.LT(-1).getText();} 
										(ID  { _varName = _input.LT(-1).getText();
											if (!symbolTable.exists(_varName)){
				                                throw new IsiSemanticException("Symbol "+_varName+" not declared");
				                              }
										 }
									 	| (NUMBER | SIGNEDNUMBER)) {_exprStep +=_input.LT(-1).getText();} 
									 )
									 
								|	(	ATTR { _exprStep += _input.LT(-1).getText();}
										ID  { _varName = _input.LT(-1).getText();
											if (!symbolTable.exists(_varName)){
				                                throw new IsiSemanticException("Symbol "+_varName+" not declared");
				                              }
										 } { _exprStep += _varName;}
										 OP { _exprStep += _input.LT(-1).getText();}
										(ID  { _varName = _input.LT(-1).getText();
											if (!symbolTable.exists(_varName)){
				                                throw new IsiSemanticException("Symbol "+_varName+" not declared");
				                              }
										 }
									 	| (NUMBER | SIGNEDNUMBER)) {_exprStep +=_input.LT(-1).getText();} 
									  )
									 
							)
							
							)?
							FP
							ACH
							{ curThread = new ArrayList<AbstractCommand>(); 
								stack.push(curThread);
							}
							  (cmd)+
							  FCH
							{
								listaTrue = stack.pop();	
							}
							{
								CommandFor cmd = new CommandFor(_exprAttrib, _exprDecision, _exprStep, listaTrue);
								stack.peek().add(cmd);
					}	                   	   
                   	   
                   	   
             	;
			
expr		:  (termo        
                
               | SIGNEDNUMBER { _attribTerm = _input.LT(-1).getText();
              	                _exprContent += _attribTerm;
              	       
              	                 /* Verificação de tipo*/
	               	             _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
	               	             
	               	             _expr.addElement(_attribTerm, IsiTypes.NUMBER);
                               }) 
                               
               ( (OP  { op = _input.LT(-1).getText();
	                    _exprContent += op;
	                    _expr.addOperator(op);}
	           termo)
	            
	           | SIGNEDNUMBER { _attribTerm = _input.LT(-1).getText();
              	                _exprContent += _attribTerm;
              	                op = "+";
              	       
              	                /* VerificaÃ§Ã£o de tipo*/
	               	            _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
	               	            _expr.addElement(_attribTerm, IsiTypes.NUMBER);
                              }
	           )*
			;
			
termo		: ID { _attribTerm = _input.LT(-1).getText();
                   verificaID(_attribTerm);
	               _exprContent += _attribTerm;
	               useVariavel(_attribTerm);
	               /* Verifica se uma variÃ¡vel usada foi atribuÃ­da */
	               verificaAtribuicao(_attribTerm);
	               
	               /* VerificaÃ§Ã£o de tipo*/
	               //_term = atualizaTipoTermo(_attribTerm, _term, obtemTipoId(_attribTerm), op);
	               
	               _expr.addElement(_attribTerm, obtemTipoId(_attribTerm));
	               
                 } 
            | 
              NUMBER { _attribTerm = _input.LT(-1).getText();
              	       _exprContent += _attribTerm;
              	       
              	       /* Verificacao de tipo*/
	               	   _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
	               	   
	               	   _expr.addElement(_attribTerm, IsiTypes.NUMBER);
                      }
            | 
              SIGNEDNUMBER { _attribTerm = _input.LT(-1).getText();
              	             if (op != null && op.contentEquals("+") && _attribTerm.substring(0, 1).contentEquals("+")){
              	             	_attribTerm = _attribTerm.substring(1);
              	             }
              	             _exprContent += _attribTerm;
              	             /* Verificação de tipo*/
	               		     _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
	               		     
	               		     _expr.addElement(_attribTerm, IsiTypes.NUMBER);
                            }
               
            | 
              TEXTO { _attribTerm = _input.LT(-1).getText();
              	      _exprContent += _attribTerm;
              	       
              	      /* Verificação de tipo*/
	               	  _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.TEXT, op);
	               	  
	               	  _expr.addElement(_attribTerm, IsiTypes.TEXT);
                    }
            | '(' {_exprContent += _input.LT(-1).getText();
                   _expr.openParentheses(); } 
                expr 
               ')' { _exprContent += _input.LT(-1).getText();
                     _expr.closeParentheses(); }
			;			
			
			
			
condicao : { _booleanExpr = "";
             _expr = new BooleanExpressionBuilder();
            }
           exprbooleana 
           
           { 
             //_condition =_booleanExpr;
             _condition = _expr.getExpression();
             _booleanExpr = ""; 
           }
         ;
			
exprbooleana  :                   
                 booleano         
                 (BOOLEANOBINARIO { 
                                   bool = _input.LT(-1).getText();
                                   if (bool.contentEquals("ou")){
                   		           		_booleanExpr +=  " || ";
                   		           		bool = "||";
                                   } else if (bool.contentEquals("e")) {
                   		           		_booleanExpr +=  " && ";
                   		           		bool = "&&";
                                   } else {
                                      throw new IsiSemanticException("Boolean symbol '"+bool+"' is not valid.");
                                   } 
                                   _expr.addOperator(bool);
                                   }
                                   
                 booleano         
                 )* 
              ;   
          
booleano :                  { _partialBooleanExpr = ""; }
            (BOOLEANOUNARIO { 
                             bool = _input.LT(-1).getText();
                             if (bool.contentEquals("nao")){
                   		      	_booleanExpr +=  "!";
                   		      	bool = "!";
                   			 } else {
                                throw new IsiSemanticException("Boolean symbol '"+bool+"' is not valid.");
                             }
                             _expr.addOperator(bool);
                            }
                            
            )?  
            '('             { 
                             _booleanExpr += _input.LT(-1).getText();
                             _expr.openParentheses();
                            }
            exprbooleana    
            ')'             { _booleanExpr +=  _input.LT(-1).getText();
                              _exprContent = "";
                              _partialBooleanExpr = ""; 
                              _expr.closeParentheses();
                            }
            |             { _term = null;}
            relacao       {   
                              //_booleanTerm = new IsiTerm(_term.getContent(), IsiTypes.BOOLEAN);
                              //_booleanExpr +=  _term.getContent();
                              op = null;
                              _exprContent = "";
                            }
                            
           |
           BOOL   {
                   bool = _input.LT(-1).getText();
                   if (bool.contentEquals("verdadeiro")){
                   		_booleanExpr +=  "true";
                   		bool = "true";
                   } else if (bool.contentEquals("falso")) {
                   		_booleanExpr +=  "false";
                   		bool = "false";
                   } else {
                       throw new IsiSemanticException("Boolean symbol '"+bool+"' is not valid.");
                   }
                   _expr.addElement(bool, IsiTypes.BOOLEAN);
                  }  
          
          | expr
                  
              
         ; 
         
relacao :  expr  
           OPREL         { _exprContent += " "+ _input.LT(-1).getText()+" ";
                           op = _input.LT(-1).getText();
                           if (op != null && !IsiOperator.isRelationalOperator(op)){
                                throw new IsiSemanticException("Expecting a relational operator, but got '"+op+"'");
                           }
                           _expr.addOperator(op);
                         }
           expr
        ;
        
operacao : '('            { _booleanExpr +=  _input.LT(-1).getText();
                            _expr.openParentheses();}
            termonumerico
            OP            { _exprContent += _input.LT(-1).getText();
                           op = _input.LT(-1).getText();
                           if (op != null && !IsiOperator.isNumericOperator(op)){
                                throw new IsiSemanticException("Expecting a numeric operator, but got '"+op+"'");
                           }
                           _expr.addOperator(op);
                         }
            termonumerico
            ')'           { _booleanExpr +=  _input.LT(-1).getText();
                            _expr.closeParentheses();}
         ;
        
termonumerico :  (ID { _attribTerm = _input.LT(-1).getText();
                       verificaID(_attribTerm);
	                   _exprContent += _attribTerm;
	                   useVariavel(_attribTerm);
	                   /* Verifica se uma variável usada foi atribui­da */
	                   verificaAtribuicao(_attribTerm);
	               
	                   /* Verificacao de tipo*/
	                   _term = atualizaTipoTermo(_attribTerm, _term, obtemTipoId(_attribTerm), op);
	                   
	                   _expr.addElement(_attribTerm, obtemTipoId(_attribTerm) );
	               
                      }
                 
                 | NUMBER { _attribTerm = _input.LT(-1).getText();
              	            _exprContent += _attribTerm;
              	       
              	           /* VerificaÃ§Ã£o de tipo*/
	               	       _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
	               	       
	               	       _expr.addElement(_attribTerm, IsiTypes.NUMBER);
                          }
           
                 | SIGNEDNUMBER { _attribTerm = _input.LT(-1).getText();
              	                  _exprContent += _attribTerm;
              	                   /* Verificacao de tipo*/
	               		          _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
	               		          
	               		          _expr.addElement(_attribTerm, IsiTypes.NUMBER);
                                }
           
                 )
              ;

			
BOOLEANOUNARIO : 'nao'
               ;
              
BOOLEANOBINARIO : 'ou' | 'e'
               ;
               
BOOL  : 'verdadeiro' | 'falso'
      ;

AP	: '('
	;
	
FP	: ')'
	;
	
SC	: ';'
	;
	
POINT : '.'
      ;
	
OP	: '+' | '-' | '*' | '/'
	;

OP_INC_DEC 	: '++' | '--' 
			;	
	
OP_INC_DEC_EQ	: '+=' | '-='	
				;

ATTR : ':='
	 ;
	 
VIR  : ','
     ;
     
ACH  : '{'
     ;
     
FCH  : '}'
     ;
	 
	 
OPREL : '>' | '<' | '>=' | '<=' | '==' | '!='
      ;
      
ID	: [a-z] ([a-z] | [A-Z] | [0-9])*
	;
	
NUMBER	: [0-9]+ ('.' [0-9]+)?
		;
		
SIGNEDNUMBER : ('+' | '-') [0-9]+ ('.' [0-9]+)?
             ;
		
SIGN  : ('+' | '-')
      ;
		
TEXTO : ('"' | '“') ([a-z] | [A-Z] | [0-9] | ' ' | '\n' )* ( '”' | '"')
      ;
      
COMENTARIO : '/*' .*? '*/'
		   ;
      
comentarios : (COMENTARIO {
                              _textComment = (_textComment == null ? "" : _textComment) + _input.LT(-1).getText() + " ";
                          } 
               )+
               {
	     	      CommandComentario cmd = new CommandComentario(_textComment);
	     	      stack.peek().add(cmd);
			   }
			;
	
		
WS	: (' ' | '\t' | '\n' | '\r') -> skip;

