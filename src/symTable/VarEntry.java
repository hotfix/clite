package symTable;

import abstractTreeNodes.Ops;

public class VarEntry extends AbstractEntry{

	private static final long serialVersionUID = 1L;

	int adr;
	String typ;
	
	public VarEntry()
	{
		op = Ops.varop; 
		adr = 0; 
		typ = "";
	}
	
	public VarEntry(int fa, String ftyp)
	{
		op = Ops.varop; 
		adr = fa; 
		typ = ftyp;
	}
	
	public int GetAddr()
	{
		return adr;
	}
	
	public String GetTyp()
	{
		return typ;
	}
}
