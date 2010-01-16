package abstractTreeNodes;

import instructions.IntVal;
import codeGen.CodeGen;
import symTable.AbstractDescr;
import symTable.AbstractEntry;
import symTable.StructDescr;
import symTable.SymTable;
import symTable.VarEntry;

public class StructRefNode extends BinNode {	

	private static final long serialVersionUID = 1L;

	public StructRefNode() {
		op = Ops.structref;
	}
	
	public void print(int indentation) {
		
		op = Ops.structref;
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("StructRefNode");
		GetL().print(indentation+2);
		GetR().print(indentation+2);
	}	
	
	public String toString() {
		return new String("StructRefNode" + 
				"\n  " + GetL().toString() +
				"\n  " + GetR().toString());
	}

	@Override
	public AbstractDescr Compile(SymTable env) {
		
		VarEntry struct  = env.getVariable(((IdfNode)GetL()).GetS());
		int struct_addr = struct.GetAddr();
		
		VarEntry structfield = ((StructDescr)struct.GetTyp()).getRecenv().getVariable(((IdfNode)GetR()).GetS());
		
		if (structfield.GetTyp().GetOp() == Ops.arraytyp) 
			System.out.println("StructRefNode::Compile -> Error: Reference at array fields not yet implemented!");
		else {
			//Addr
			CodeGen.OutInstr(new IntVal( structfield.GetAddr() +  struct_addr));
			//Size
			CodeGen.OutInstr(new IntVal(1));
		}
		return null;
	}	

}
