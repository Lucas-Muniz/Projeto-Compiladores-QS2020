grammar IsiLang;

@header {
	import isilanguage.datastructures.IsiSymbol;
	import isilanguage.datastructures.IsiVariable;
	import isilanguage.datastructures.IsiSymbolTable;
	import isilanguage.datastructures.IsiTerm;
	import isilanguage.datastructures.IsiTypes;
	import isilanguage.datastructures.IsiOperator;
	import isilanguage.datastructures.expressions.AbstractExpression;
	import isilanguage.datastructures.expressions.NumericExpressionBuilder;
	import isilanguage.datastructures.expressions.TextualExpressionBuilder;
	import isilanguage.datastructures.expressions.RelationalExpressionBuilder;
	import isilanguage.datastructures.expressions.BooleanExpressionBuilder;
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
	import isilanguage.ast.CommandEscolha;
	import java.util.ArrayList;
	import java.util.Stack;
}

@members {
	private int _tipo, _tipoCaso;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread, defaultCommandList;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private ArrayList<String> listaTexto;
	private ArrayList<ArrayList<AbstractCommand>> listaComandos;
	private String _readID;
	private String _writeID;
	private String _attribID;
	private String _attribTerm;
	private String _casos, _casoBase;
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
				throw new IsiSemanticException("Symbol "+id+" is used but not attributed");
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
	
	public String obterSimboloBooleano(String bool){
	    String symbol;
        if (bool.contentEquals("verdadeiro")){
        	return "true";
        } else if (bool.contentEquals("falso")) {
        	return "false";
        } else {
        	throw new IsiSemanticException("Boolean symbol '"+bool+"' is not valid.");
       }
	}
	
	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}
	
	public void generateCode(){
		program.generateTargetPrettyPrinter("GeneratedCode");
	}
}

prog
:
	'programa' decl bloco 'fimprog.'
	{  
		program.setVarTable(symbolTable);
        program.setComandos(stack.pop());
        symbolTable.verifyVariables();
    }

;

decl
:
	(
		declaravar
	)+
;

declaravar
:
	'declare' tipo ID
	{
		_varName = _input.LT(-1).getText();
	    _varValue = null;
	    symbol = new IsiVariable(_varName, _tipo, _varValue);
	    if (!symbolTable.exists(_varName)){
	    	symbolTable.add(symbol);	
	    } else {
	    	throw new IsiSemanticException("Symbol "+_varName+" already declared");
	    }
   }

	(
		ATTR
		{
			if(_tipo == IsiTypes.NUMBER){
				_expr = new NumericExpressionBuilder();
			} else if (_tipo == IsiTypes.BOOLEAN){
				_expr = new BooleanExpressionBuilder();
			} else if (_tipo == IsiTypes.TEXT){
				_expr = new TextualExpressionBuilder();
			}
		}

		(
			expr
			| condicao
			{
				_exprContent = _condition;
			}

		)
		{
			CommandAtribuicao cmd = new CommandAtribuicao(_varName,  _expr.getExpression());
			atribuiVariavel(_varName);
			stack.peek().add(cmd);
			_term = null;
			op = null;
		}

		|
		(
			VIR ID
			{
				_varName = _input.LT(-1).getText();
	            _varValue = null;
	            symbol = new IsiVariable(_varName, _tipo, _varValue);
	            if (!symbolTable.exists(_varName)){
	            	symbolTable.add(symbol);	
	            } else {
	            	throw new IsiSemanticException("Symbol "+_varName+" already declared");
	            }
            }

		)*
	) POINT
;

tipo
:
	'numero'
	{ 
		_tipo = IsiVariable.NUMBER;
	}

	| 'texto'
	{ 
		_tipo = IsiVariable.TEXT;
	}

	| 'booleano'
	{ 
		_tipo = IsiVariable.BOOLEAN;
	}

;

bloco
:
{ 
	curThread = new ArrayList<AbstractCommand>(); 
	stack.push(curThread);  
}

	(
		cmd
	)+
;

cmd
:
	cmdleitura
	| cmdescrita
	| cmdattrib
	| cmdselecao
	| cmdrepeticao
	| cmdopcao
	| comentarios
	| declaravar
;

cmdleitura
:
	'leia' AP ID
	{ 
		verificaID(_input.LT(-1).getText());
        _readID = _input.LT(-1).getText();
        atribuiVariavel(_readID);
    }

	FP POINT
	{
		IsiVariable var = (IsiVariable)symbolTable.get(_readID);
		CommandLeitura cmd = new CommandLeitura(_readID, var);
		stack.peek().add(cmd);
    }

;

cmdescrita
:
	'escreva' AP
	(
		TEXTO
		{
			_writeID = _input.LT(-1).getText();
		}

		| ID
		{ 
			verificaID(_input.LT(-1).getText());
	        _writeID = _input.LT(-1).getText();
	        useVariavel(_writeID);
        }

	) FP POINT
	{
		CommandEscrita cmd = new CommandEscrita(_writeID);
		stack.peek().add(cmd);
    }

;

cmdattrib
:
	ID
	{ 
		_exprID = _input.LT(-1).getText();
		verificaID(_exprID);
        _exprType = obtemTipoId(_exprID);
                    
        if(_exprType == IsiTypes.NUMBER){
        	_expr = new NumericExpressionBuilder();
        } else if (_exprType == IsiTypes.BOOLEAN){
        	_expr = new BooleanExpressionBuilder();
        } else if (_exprType == IsiTypes.TEXT){
        	_expr = new TextualExpressionBuilder();
        }
                    
    }

	ATTR
	{ 
		_exprContent = "";
	}

	(
		  expr { 
		  	_exprContent = _expr.getExpression();
		       }
		| condicao
		{
			_exprContent = _condition;
		}

	) POINT
	{ 
        CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
        atribuiVariavel(_exprID);
        stack.peek().add(cmd);
        _term = null;
        op = null;
    }

;

cmdselecao
:
	'se' AP condicao
	{
		_exprDecision = _condition;
	}

	FP 'entao' ACH
	{ 
		curThread = new ArrayList<AbstractCommand>(); 
        stack.push(curThread);
    }

	(
		cmd
	)+ FCH
	{
		listaTrue = stack.pop();	
        listaFalse = new ArrayList<AbstractCommand>();
        CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
    }

	(
		'senao' ACH
		{
			curThread = new ArrayList<AbstractCommand>();
            stack.push(curThread);
        }

		(
			cmd+
		) FCH
		{
			listaFalse = stack.pop();
             cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
        }

	)?
	{
		stack.peek().add(cmd);
    }

;

cmdrepeticao
:
	'enquanto' AP condicao
	{
		_exprDecision = _condition;
    }

	FP ACH
	{ 
		curThread = new ArrayList<AbstractCommand>(); 
        stack.push(curThread);
    }

	(
		cmd
	)+ FCH
	{
		commands = stack.pop();	
		CommandEnquanto cmd = new CommandEnquanto(_exprDecision, commands);
        stack.peek().add(cmd);
    }

	| 'faca' ACH
	{ 
		curThread = new ArrayList<AbstractCommand>(); 
        stack.push(curThread);
    }

	(
		cmd
	)+ FCH
	{
		commands = stack.pop();	
    }

	'enquanto' AP condicao
	{
		_exprDecision = _condition;
	}

	FP POINT
	{
		CommandFaca cmd = new CommandFaca(_exprDecision, commands);
        stack.peek().add(cmd);
    }

	| 'para' AP
	{
		_exprAttrib = "";
	}

	(
		(
			(
				tipo ID
				{
					_varName = _input.LT(-1).getText();
			        _varValue = null;
			        symbol = new IsiVariable(_varName, _tipo, _varValue);
			                              
			        if (!symbolTable.exists(_varName)){
			        	symbolTable.add(symbol);	
			        	_exprAttrib = _varName;
			        } else {
			        	throw new IsiSemanticException("Symbol "+_varName+" already declared");
			        }
			    }

				ATTR
				{ 
					_exprAttrib += _input.LT(-1).getText();
				}

				(
					ID
					{ 
						_varName = _input.LT(-1).getText();
						if (!symbolTable.exists(_varName)){
			            	throw new IsiSemanticException("Symbol "+_varName+" not declared");
			            }
					}

					|
					(
						NUMBER
						| SIGNEDNUMBER
					)
				)
				{
					_exprContent =_input.LT(-1).getText(); 
					_exprAttrib +=_exprContent;
				}

				{
					atribuiVariavel(_varName);
					_term = null;
					op = null;
				}

			)
			|
			(
				ID
				{ 
					_varName = _input.LT(-1).getText();
					if (!symbolTable.exists(_varName)){
			        	throw new IsiSemanticException("Symbol "+_varName+" not declared");
			        }
			        else{
			        _exprAttrib = _varName;
			        }
				}

			) ATTR
			{ 
				_exprAttrib += _input.LT(-1).getText();
			}

			(
				ID
				{ 
					_varName = _input.LT(-1).getText();
					if (!symbolTable.exists(_varName)){
			        	throw new IsiSemanticException("Symbol "+_varName+" not declared");
			        }
				}

				| NUMBER
			)
			{
				_exprAttrib +=_input.LT(-1).getText();
			}

		)
	)? POINT
	{_exprDecision = "";}

	(
		ID
		{
			_expr = new RelationalExpressionBuilder();
			_varName = _input.LT(-1).getText();
			if (!symbolTable.exists(_varName)){
				throw new IsiSemanticException("Symbol "+_varName+" not declared");
			} else {
				_exprDecision += _varName;
			}
			_expr.addElement(_varName, obtemTipoId(_varName));
		}

		OPREL
		{ ;
			_exprDecision += _input.LT(-1).getText();
			_expr.addOperator(_input.LT(-1).getText());
	    }

		expr
		| BOOL {
			_expr = new BooleanExpressionBuilder();
			bool = obterSimboloBooleano(_input.LT(-1).getText());
			_expr.addElement(bool, IsiTypes.BOOLEAN);
			
		}
	)
	{ 
		_exprStep = ""; 
		_exprDecision = _expr.getExpression();
	}

	POINT
	(
		ID
		{ 
			_varName = _input.LT(-1).getText();
			if (!symbolTable.exists(_varName)){
				throw new IsiSemanticException("Symbol "+_varName+" not declared");
			}
		}

		{ 
			_exprStep += _input.LT(-1).getText();
		}

		(
			OP_INC_DEC
			{ 
				_exprStep += _input.LT(-1).getText();
			}

			|
			(
				OP_INC_DEC_EQ
				{ 
					_exprStep += _input.LT(-1).getText();
				}

				(
					ID
					{ 
						_varName = _input.LT(-1).getText();
						if (!symbolTable.exists(_varName)){
				        	throw new IsiSemanticException("Symbol "+_varName+" not declared");
				        }
					}

					|
					(
						NUMBER
						| SIGNEDNUMBER
					)
				)
				{
					_exprStep +=_input.LT(-1).getText();
				}

			)
			|
			(
				ATTR
				{ 
					_exprStep += _input.LT(-1).getText();
				}

				ID
				{ 
					_varName = _input.LT(-1).getText();
					if (!symbolTable.exists(_varName)){
				    	throw new IsiSemanticException("Symbol "+_varName+" not declared");
				    }
				}

				{ 
					_exprStep += _varName;
				}

				OP
				{ 
					_exprStep += _input.LT(-1).getText();
				}

				(
					ID
					{ 
						_varName = _input.LT(-1).getText();
						if (!symbolTable.exists(_varName)){
				        	throw new IsiSemanticException("Symbol "+_varName+" not declared");
				        }
					}

					|
					(
						NUMBER
						| SIGNEDNUMBER
					)
				)
				{
					_exprStep +=_input.LT(-1).getText();
				}

			)
		)
	)? FP ACH
	{ 
		curThread = new ArrayList<AbstractCommand>(); 
		stack.push(curThread);
	}

	(
		cmd
	)+ FCH
	{
		commands = stack.pop();	
		CommandFor cmd = new CommandFor(_exprAttrib, _exprDecision, _exprStep, commands);
		stack.peek().add(cmd);
	}

;

expr
:
	(
		termo
		| SIGNEDNUMBER
		{ 
			_attribTerm = _input.LT(-1).getText();
            _expr.addElement(_attribTerm, IsiTypes.NUMBER);
        }

	)
	(
		(
			OP
			{ 
				op = _input.LT(-1).getText();
	            _expr.addOperator(op);
	        }

			termo
		)
		| SIGNEDNUMBER
		{ 
			_attribTerm = _input.LT(-1).getText();

            op = "+";
            _expr.addElement(_attribTerm, IsiTypes.NUMBER);
        }

	)*
;

termo
:
	ID
	{ 
		_attribTerm = _input.LT(-1).getText();
        verificaID(_attribTerm);
	    useVariavel(_attribTerm);
	    /* Verifica se uma variavel usada foi atribuida */
	    verificaAtribuicao(_attribTerm);
	    _expr.addElement(_attribTerm, obtemTipoId(_attribTerm));                
	               
     }

	| NUMBER
	{ 
		_attribTerm = _input.LT(-1).getText();
        _expr.addElement(_attribTerm, IsiTypes.NUMBER);
    }

	| SIGNEDNUMBER
	{ 
		_attribTerm = _input.LT(-1).getText();
        if (op != null && op.contentEquals("+") && _attribTerm.substring(0, 1).contentEquals("+")){
        	_attribTerm = _attribTerm.substring(1);
        }
	               		     
	  _expr.addElement(_attribTerm, IsiTypes.NUMBER);
    }

	| TEXTO
	{ 
		_attribTerm = _input.LT(-1).getText();       	  
	    _expr.addElement(_attribTerm, IsiTypes.TEXT);
    }

	| '('
	{
        _expr.openParentheses(); 
     }

	expr ')'
	{ 
        _expr.closeParentheses(); 
    }

;

condicao
:
{
    _expr = new BooleanExpressionBuilder();
}

	exprbooleana
	{ 
      _condition = _expr.getExpression();
    }

;

exprbooleana
:
	booleano
	(
		BOOLEANOBINARIO
		{
			bool = _input.LT(-1).getText();
            if (bool.contentEquals("ou")){
                bool = "||";
            } else if (bool.contentEquals("e")) {
                bool = "&&";
            } else {
            	throw new IsiSemanticException("Boolean symbol '"+bool+"' is not valid.");
            } 
            _expr.addOperator(bool);
       }

		booleano
	)*
;

booleano
:
{ 
		
	}

	(
		BOOLEANOUNARIO
		{
			bool = _input.LT(-1).getText();
            if (bool.contentEquals("nao")){
            	bool = "!";
            } else {
            	throw new IsiSemanticException("Boolean symbol '"+bool+"' is not valid.");
            }
            _expr.addOperator(bool);
        }

	)? '('
	{
        _expr.openParentheses();
    }

	exprbooleana ')'
	{ 
        _expr.closeParentheses();
    }

	|
	{ _term = null;}

	relacao
	{
		op = null;
        _exprContent = "";
    }

	| BOOL
	{
		bool = _input.LT(-1).getText();
        bool = obterSimboloBooleano(bool);
        _expr.addElement(bool, IsiTypes.BOOLEAN);
     }

	| expr
;

relacao
:
	expr OPREL
	{ 
		
        op = _input.LT(-1).getText();
        if (op != null && !IsiOperator.isRelationalOperator(op)){
         	throw new IsiSemanticException("Expecting a relational operator, but got '"+op+"'");
        }
        _expr.addOperator(op);
    }

	expr
;

operacao
:
	'('
	{ _booleanExpr +=  _input.LT(-1).getText();
                            _expr.openParentheses();}

	termonumerico OP
	{ 
        op = _input.LT(-1).getText();
        if (op != null && !IsiOperator.isNumericOperator(op)){
      	  	  throw new IsiSemanticException("Expecting a numeric operator, but got '"+op+"'");
        }
        _expr.addOperator(op);
    }

	termonumerico ')'
	{ 
      _expr.closeParentheses();}

;

termonumerico
:
	(
		ID
		{ 
		  _attribTerm = _input.LT(-1).getText();
          verificaID(_attribTerm);
	      useVariavel(_attribTerm);
	      /* Verifica se uma variavel usada foi atribuida */
	      verificaAtribuicao(_attribTerm);
	      _expr.addElement(_attribTerm, obtemTipoId(_attribTerm) );
	               
        }

		| NUMBER
		{ 
			_attribTerm = _input.LT(-1).getText();
            _exprContent += _attribTerm;
	        _expr.addElement(_attribTerm, IsiTypes.NUMBER);
         }

		| SIGNEDNUMBER
		{ 
			_attribTerm = _input.LT(-1).getText();		          
	        _expr.addElement(_attribTerm, IsiTypes.NUMBER);
        }

	)
;

cmdopcao : 'escolha' AP
					 ID { 
					 		_casoBase = _input.LT(-1).getText();
					 		verificaID(_casoBase);
					 		useVariavel(_casoBase);
					 		/* Verifica se uma variavel usada foi atribuida */
					 		verificaAtribuicao(_casoBase);
					 		listaTexto = new ArrayList<String>();
					 		listaComandos = new ArrayList<ArrayList<AbstractCommand>>();
					 		defaultCommandList = new ArrayList<AbstractCommand>();
					 		_tipo = obtemTipoId(_casoBase);
					 		if (_tipo == IsiTypes.BOOLEAN){
					 			throw new IsiSemanticException(" Incompatible type: boolean cannot be used in switch strucuture.");
					 		} else if (_tipo == IsiTypes.NUMBER){
					 			throw new IsiSemanticException(" Incompatible type: number cannot be used in switch strucuture.");
					 		}
					 		
					 		
					 	}
					 FP
					 ACH
					 ('caso'
					 (TEXTO {
					 	_tipoCaso = IsiTypes.TEXT;
					 	_casos = _input.LT(-1).getText();
					 }
					 )		{ 
					 			if (_tipoCaso != _tipo){
					 				throw new IsiSemanticException("Expecting a case with a "+IsiTerm.typeToString(_tipo)
					 					+" but got a case with a "+IsiTerm.typeToString(_tipoCaso)+".");
					 			}
					          	listaTexto.add(_casos);
					          	
					        }
					 POINTS
					 { 
					 	curThread = new ArrayList<AbstractCommand>();
					 	stack.push(curThread);
                   	 }
					 (cmd)+
			         'pare'
					 POINT
					 {
					 	listaComandos.add(stack.pop());
					 }
					 )+
					 ('padrao' {
					 	curThread = new ArrayList<AbstractCommand>();
					 	stack.push(curThread);
					 }
					 POINTS
					 (cmd)+
						 {
						 	defaultCommandList = stack.pop();
						 }
					 )?
					 
					 FCH 
					 {
	     	      	 	CommandEscolha cmd = new CommandEscolha(_casoBase, listaTexto, listaComandos, defaultCommandList);
	     	      		stack.peek().add(cmd);
			   	     }
					 
					 
          ;
          
POINTS : ':'
       ;

BOOLEANOUNARIO
:
	'nao'
;

BOOLEANOBINARIO
:
	'ou'
	| 'e'
;

BOOL
:
	'verdadeiro'
	| 'falso'
;

AP
:
	'('
;

FP
:
	')'
;

SC
:
	';'
;

POINT
:
	'.'
;

OP
:
	'+'
	| '-'
	| '*'
	| '/'
;

OP_INC_DEC
:
	'++'
	| '--'
;

OP_INC_DEC_EQ
:
	'+='
	| '-='
;

ATTR
:
	':='
;

VIR
:
	','
;

ACH
:
	'{'
;

FCH
:
	'}'
;

OPREL
:
	'>'
	| '<'
	| '>='
	| '<='
	| '=='
	| '!='
;

ID
:
	[a-z]
	(
		[a-z]
		| [A-Z]
		| [0-9]
	)*
;

NUMBER
:
	[0-9]+
	(
		'.' [0-9]+
	)?
;

SIGNEDNUMBER
:
	(
		'+'
		| '-'
	) [0-9]+
	(
		'.' [0-9]+
	)?
;

SIGN
:
	(
		'+'
		| '-'
	)
;

TEXTO
:
	(
		'"'
		| '“'
	)
	(
		[a-z]
		| [A-Z]
		| [0-9]
		| ' '
		| '\n'
	)*
	(
		'”'
		| '"'
	)
;

COMENTARIO
:
	'/*' .*? '*/'
;

comentarios
:
	(
		COMENTARIO
		{   _textComment = _input.LT(-1).getText();
        }

	)
	{
		CommandComentario cmd = new CommandComentario(_textComment);
		stack.peek().add(cmd);
	}

;

WS
:
	(
		' '
		| '\t'
		| '\n'
		| '\r'
	) -> skip
;

