package symTable;

import java.util.HashMap;
import java.util.Iterator;


public class SymTable {

	private HashMap<String, VarEntry> symTable;
	
	public SymTable() {
		this.symTable = new HashMap<String, VarEntry>();
	}
	
	// returns the actual size of the SymTyble
	public int getSize() {
		int size = 0;
		Iterator<String> it = symTable.keySet().iterator();
		while(it.hasNext()) {
			String varname = it.next();
			size+=symTable.get(varname).GetTyp().GetSize();
		}
		return size;	
	}

	public void addVariable(String name, AbstractDescr descr) {	
		
		symTable.put(name, new VarEntry(getSize(), descr));
	}
	
	public void Print() {
		
		Iterator<String> it = symTable.keySet().iterator();
		while(it.hasNext()) {
			String varname = it.next();
			System.out.println(varname + " --> " + symTable.get(varname).toString());
		}
	}
	
	public String toString() {
		
		String str = new String();
		Iterator<String> it = symTable.keySet().iterator();
		while(it.hasNext()) {
			String varname = it.next();
			str += ("\n" + varname + " --> " + symTable.get(varname).toString());
		}		
		return str;		
	}

	public VarEntry getVariable(String s) {
		return symTable.get(s);
	}
	
}


