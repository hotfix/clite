package symTable;

import abstractTreeNodes.Ops;

public class VarEntry extends AbstractEntry{

	private static final long serialVersionUID = 1L;

	int adr;
	AbstractDescr typ;
	
	public VarEntry()
	{
		op = Ops.varop; 
		adr = 0; 
		//typ = "";
	}
	
	public VarEntry(int fa, AbstractDescr ftyp)
	{
		op = Ops.varop; 
		adr = fa; 
		typ = ftyp;
	}
	
	public int GetAddr()
	{
		return adr;
	}
	
	public AbstractDescr GetTyp()
	{
		return typ;
	}
	
	public String toString() {
		
		return(new String(" Addr= " + adr + ", Typ= " + typ.toString()));
	}
}
