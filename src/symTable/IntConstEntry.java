package symTable;

import abstractTreeNodes.Ops;

public class IntConstEntry extends AbstractEntry{

	private static final long serialVersionUID = 1L;

	int i;

	public IntConstEntry()	
	{
		op = Ops.intop; 
		i = 0;
	}
	
	public IntConstEntry(int fi)
	{
		op = Ops.intop; 
		i = fi;
	}
	
	public void SetI(int fi)
	{
		i = fi;
	}
	
	public int GetI()
	{
		return i;
	}
}
