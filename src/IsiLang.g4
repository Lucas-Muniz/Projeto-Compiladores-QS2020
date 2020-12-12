grammar IsiLang;

@header{
	import isilanguage.datastructures.IsiSymbol;
	import isilanguage.datastructures.IsiVariable;
	import isilanguage.datastructures.IsiSymbolTable;
	import isilanguage.datastructures.IsiTerm;
	import isilanguage.datastructures.IsiTypes;
	import isilanguage.datastructures.IsiOperator;
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
                   } 
               ATTR { _exprContent = ""; } 
               expr 
               POINT
               {
               	 CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
               	 atribuiVariavel(_exprID);
               	 stack.peek().add(cmd);
               	 verificaTipoAtribuicao(_exprID, _term);
               	 _term = null;
               	 op = null;
               }
			;
			
			
cmdselecao  :  'se' AP
                    ID    { op = null;
                            type = -1;
                            _exprDecision = _input.LT(-1).getText();
                            useVariavel(_input.LT(-1).getText());
                            _term = atualizaTipoTermo(_exprDecision, _term, obtemTipoId(_exprDecision), op); }
                    OPREL { _exprDecision += _input.LT(-1).getText();
                            op = _input.LT(-1).getText();
                            if (op != null && !IsiOperator.isRelationalOperator(op)){
                            	throw new IsiSemanticException("Expecting a relational operator, but got '"+op+"'");
                            }
                           }
                    (ID   { useVariavel(_input.LT(-1).getText());
                            type = obtemTipoId(_input.LT(-1).getText());}
                    | NUMBER  { type = IsiTypes.NUMBER;}
                    | SIGNEDNUMBER { type = IsiTypes.NUMBER;}
                    )              { _exprDecision += _input.LT(-1).getText(); 
                                     _attribTerm = _input.LT(-1).getText();
                                     _term = atualizaTipoTermo(_attribTerm, _term, type, op);
                                     _term = null;
                                     op = null;}
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
                   		  ID    {  op = null;
                                   type = -1;
                                   _exprDecision = _input.LT(-1).getText();
                                   useVariavel(_input.LT(-1).getText());
                                   _term = atualizaTipoTermo(_exprDecision, _term, obtemTipoId(_exprDecision), op); }
                           OPREL { _exprDecision += _input.LT(-1).getText();
                                   op = _input.LT(-1).getText();
                                   if (op != null && !IsiOperator.isRelationalOperator(op)){
                            	     throw new IsiSemanticException("Expecting a relational operator, but got '"+op+"'");
                                   } 
                                  }
                           (ID   { useVariavel(_input.LT(-1).getText());
                                   type = obtemTipoId(_input.LT(-1).getText()); }
                           | NUMBER  { type = IsiTypes.NUMBER;}
                           
                           | SIGNEDNUMBER { type = IsiTypes.NUMBER;}
                           )              { _exprDecision += _input.LT(-1).getText(); 
                                            _attribTerm = _input.LT(-1).getText();
                                            _term = atualizaTipoTermo(_attribTerm, _term, type, op);
                                            _term = null;
                                            op = null;  }
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
                   		   ID    { op = null;
                                   type = -1;
                                   _exprDecision = _input.LT(-1).getText();
                                   useVariavel(_input.LT(-1).getText());
                                   _term = atualizaTipoTermo(_exprDecision, _term, obtemTipoId(_exprDecision), op); }
                           OPREL { _exprDecision += _input.LT(-1).getText();
                                   op = _input.LT(-1).getText();
                                   if (op != null && !IsiOperator.isRelationalOperator(op)){
                            	     throw new IsiSemanticException("Expecting a relational operator, but got '"+op+"'");
                                   }  
                                  }
                           (ID   { useVariavel(_input.LT(-1).getText());
                                   type = obtemTipoId(_input.LT(-1).getText());}
                           | NUMBER  { type = IsiTypes.NUMBER;}
                           | SIGNEDNUMBER { type = IsiTypes.NUMBER;}
                            )             { _exprDecision += _input.LT(-1).getText(); 
                                            _attribTerm = _input.LT(-1).getText();
                                            _term = atualizaTipoTermo(_attribTerm, _term, type, op);
                                            _term = null;
                                            op = null;}
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
                               }) 
                               
               ( (OP  { op = _input.LT(-1).getText();
	                    _exprContent += op;}
	           termo)
	            
	           | SIGNEDNUMBER { _attribTerm = _input.LT(-1).getText();
              	                _exprContent += _attribTerm;
              	                op = "+";
              	       
              	                /* VerificaÃ§Ã£o de tipo*/
	               	            _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
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
	               _term = atualizaTipoTermo(_attribTerm, _term, obtemTipoId(_attribTerm), op);
	               
                 } 
            | 
              NUMBER { _attribTerm = _input.LT(-1).getText();
              	       _exprContent += _attribTerm;
              	       
              	       /* VerificaÃ§Ã£o de tipo*/
	               	   _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
                      }
            | 
              SIGNEDNUMBER { _attribTerm = _input.LT(-1).getText();
              	             if (op != null && op.contentEquals("+") && _attribTerm.substring(0, 1).contentEquals("+")){
              	             	_attribTerm = _attribTerm.substring(1);
              	             }
              	             _exprContent += _attribTerm;
              	             /* Verificação de tipo*/
	               		     _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.NUMBER, op);
                            }
               
            | 
              TEXTO { _attribTerm = _input.LT(-1).getText();
              	      _exprContent += _attribTerm;
              	       
              	      /* Verificação de tipo*/
	               	  _term = atualizaTipoTermo(_attribTerm, _term, IsiTypes.TEXT, op);
                    }
            | '(' {_exprContent += _input.LT(-1).getText(); } 
                expr 
               ')' { _exprContent += _input.LT(-1).getText(); }
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

