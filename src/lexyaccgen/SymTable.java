package lexyaccgen;

import java.util.HashMap;



public class SymTable {

	private HashMap symTable;
	
	public SymTable() {
		this.symTable = new HashMap<Integer, SymTableEntry>();
	}
	
	public String getTableAsString() {
		//return symTable.values().toString();
		String values = new String();
		for(int i = 0; i < symTable.size(); i++) {
			values+=( (new Integer(i)).toString()+ ": " + symTable.get(i) + "\n" );
		}
		return values;
	}

	public int addSymbol(String lexem, int token, int typ, String wert) {
		
		int index = indexof(lexem), id = -1;
		if(index == -1) {
			SymTableEntry se = new SymTableEntry(lexem, token, typ, wert);		
			id = symTable.size();
			symTable.put(id, se);
		}
		return id;
	}

	private int indexof(String lexem) {
		int index;
		
		// Expert code:
		/*
		for(int i = 0; i < symTable.size(); i++) if (((SymTableEntry)symTable.get(i)).lexem.equals(lexem)) return i;
		*/
		
		// Lamer code:
		SymTableEntry s;
		for(int i = 0; i < symTable.size(); i++) {			
			s = (SymTableEntry) symTable.get(i);
			if(s.lexem.equals(lexem)){
				return i;
			}
		}		
		return -1;
	}
}


