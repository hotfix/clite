package abstractTreeNodes;

import instructions.BinInstr;
import instructions.IntVal;
import instructions.PopInstr;
import codeGen.CodeGen;
import symTable.AbstractDescr;
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
		SymTable env2 = ((StructDescr)struct.GetTyp()).getRecenv();
		int struct_addr = struct.GetAddr();
		
		
		if (GetR().op == Ops.arrayref) {
			CodeGen.OutInstr(new IntVal(struct.GetAddr()));
			GetR().Compile(env2);	
			CodeGen.OutInstr(new PopInstr());
			CodeGen.OutInstr(new BinInstr(Ops.addop));
			CodeGen.OutInstr(new IntVal(1));
		}
		else {
			
			VarEntry structfield = env2.getVariable(((IdfNode)GetR()).GetS());			
			//Addr
			CodeGen.OutInstr(new IntVal( structfield.GetAddr() +  struct_addr));
			//Size
			CodeGen.OutInstr(new IntVal(1));
		}
		return null;
	}	

}
