package abstractTreeNodes;

import instructions.IntVal;
import codeGen.CodeGen;
import symTable.AbstractDescr;
import symTable.ArrayDescr;
import symTable.VarEntry;

public class ArrayRefNode extends BinNode {

	private static final long serialVersionUID = 1L;
	
	private static AbstractDescr descr = null;
	
	public ArrayRefNode() {
		op = Ops.arrayref;
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("ArrayRefNode");
		GetL().print(indentation+2);
		GetR().print(indentation+2);
	}	
	
	public String toString() {
		return new String("ArrayRefNode" + 
				"\n  " + GetL().toString() +
				"\n  " + GetR().toString());
	}	
	
	public int CompileArr() {
	
		int addr=0;
		if(GetL().op == Ops.arrayref) 
			addr = ((ArrayRefNode)GetL()).CompileArr();
		else {
			VarEntry entry = CodeGen.Search(((IdfNode)GetL()).GetS()).GetE();
			descr = entry.GetTyp();
			addr += entry.GetAddr();
		}		
			
		if (descr.GetOp() == Ops.arraytyp) descr = ((ArrayDescr)descr).getType();			
		return addr + ((IntNode)GetR()).getI()*descr.GetSize();		
	}	
	
	@Override
	public void Compile() {	
		
		//Addr
		CodeGen.OutInstr(new IntVal(CompileArr()));
		//Size
		CodeGen.OutInstr(new IntVal(1));		
	}
}
