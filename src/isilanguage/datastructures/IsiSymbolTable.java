package isilanguage.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import isilanguage.exceptions.IsiSemanticException;

public class IsiSymbolTable {
	
	private HashMap<String, IsiSymbol> map;
	
	public IsiSymbolTable() {
		map = new HashMap<String, IsiSymbol>();
		
	}
	
	public void add(IsiSymbol symbol) {
		map.put(symbol.getName(), symbol);
	}
	
	public boolean exists(String symbolName) {
		return map.get(symbolName) != null;
	}
	
	public IsiSymbol get(String symbolName) {
		return map.get(symbolName);
	}
	
	public ArrayList<IsiSymbol> getAll(){
		ArrayList<IsiSymbol> lista = new ArrayList<IsiSymbol>();
		for (IsiSymbol symbol : map.values()) {
			lista.add(symbol);
		}
		return lista;
	}

	public void verifyVariables() {
		for (IsiSymbol symbol: this.getAll()) {
			if (symbol instanceof IsiVariable) {
				boolean isUsed = ((IsiVariable) symbol).wasUsed();
				boolean isAttributed = ((IsiVariable) symbol).wasAttributed();
				if (isUsed && !isAttributed) {
					throw new IsiSemanticException("WARNING: Symbol "+symbol.getName()+" is used but not attributed");
				} else if (isAttributed && !isUsed) {
					System.out.println("WARNING: Variable "+symbol.getName()+" is attributed but not used.");
				}else if (!isAttributed && !isUsed) {
					System.out.println("WARNING: Variable "+symbol.getName()+" is not attributed and not used.");
				}
			}
		}
	}
	
}
