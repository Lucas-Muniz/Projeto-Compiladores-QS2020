grammar IsiLang;

@header{
	import isilanguage.datastructures.IsiSymbol;
	import isilanguage.datastructures.IsiVariable;
	import isilanguage.datastructures.IsiSymbolTable;
	import isilanguage.exceptions.IsiSemanticException;
	import isilanguage.ast.IsiProgram;
	import isilanguage.ast.AbstractCommand;
	import isilanguage.ast.CommandLeitura;
	import isilanguage.ast.CommandEscrita;
	import isilanguage.ast.CommandAtribuicao;
	import isilanguage.ast.CommandDecisao;
	import isilanguage.ast.CommandRepeticao;
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
	private String _exprID;
	private String _exprContent;
	private String _exprDecision;
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
	
	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}
	
	public void generateCode(){
		program.generateTarget();
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
               }
			;
			
			
cmdselecao  :  'se' AP
                    ID    { _exprDecision = _input.LT(-1).getText();
                            useVariavel(_input.LT(-1).getText()); }
                    OPREL { _exprDecision += _input.LT(-1).getText(); }
                    (ID   { useVariavel(_input.LT(-1).getText());}
                    | NUMBER) {_exprDecision += _input.LT(-1).getText(); }
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
                   		CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
                   		stack.peek().add(cmd);
                   	}
                   )?
            ;
            
cmdrepeticao : 'enquanto' AP
                   		  ID    { _exprDecision = _input.LT(-1).getText();
                   		          useVariavel(_input.LT(-1).getText()); }
                    	  OPREL { _exprDecision += _input.LT(-1).getText(); }
                    	  (ID   { useVariavel(_input.LT(-1).getText());}
                    	  | NUMBER) {_exprDecision += _input.LT(-1).getText(); }
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
                   			CommandRepeticao cmd = new CommandRepeticao(_exprDecision, listaTrue);
                   			stack.peek().add(cmd);
                   	}
             ; 
			
expr		:  termo ( 
	             OP  { _exprContent += _input.LT(-1).getText();}
	            termo
	            )*
			;
			
termo		: ID { verificaID(_input.LT(-1).getText());
	               _exprContent += _input.LT(-1).getText();
	               useVariavel(_input.LT(-1).getText());
	               /* Verifica se uma variável usada foi atribuída */
	               verificaAtribuicao(_input.LT(-1).getText());
                 } 
            | 
              NUMBER
              {
              	_exprContent += _input.LT(-1).getText();
              }
            | '(' expr ')'
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
		
TEXTO : ('"' | '“') ([a-z] | [A-Z] | [0-9] | ' ' | '\n' )* ( '”' | '"')
      ;
		
WS	: (' ' | '\t' | '\n' | '\r') -> skip;