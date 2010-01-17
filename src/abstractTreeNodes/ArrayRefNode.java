package abstractTreeNodes;

import instructions.BinInstr;
import instructions.IntVal;
import codeGen.CodeGen;
import symTable.AbstractDescr;
import symTable.ArrayDescr;
import symTable.SymTable;
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
		
	
	@Override
	public AbstractDescr Compile(SymTable env) {
		
		ArrayRefNode node = this;
		int depth = 0;
		while(node.GetL().op == Ops.arrayref) {
			node = (ArrayRefNode)node.GetL();
			depth++;
		}

		VarEntry entry = env.getVariable( ((IdfNode)node.GetL()).GetS() );
		CodeGen.OutInstr(new IntVal(entry.GetAddr())); //array base address

		node = this;
		
		AbstractDescr descr = entry.GetTyp();
		for(int i = 0; i <= depth; i++) {
		
			node.GetR().Compile(env); //index
			if(node.GetL().op == Ops.arrayref)
				node = (ArrayRefNode) node.GetL();
			descr = ((ArrayDescr)descr).getType();
			CodeGen.OutInstr(new IntVal(descr.GetSize())); //size of array
			CodeGen.OutInstr(new BinInstr(Ops.mulop));			
			CodeGen.OutInstr(new BinInstr(Ops.addop));			
		}
		
		//Size
		CodeGen.OutInstr(new IntVal(1));
		
		return null;
	}
}
