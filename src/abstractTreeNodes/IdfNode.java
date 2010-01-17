package abstractTreeNodes;

import symTable.AbstractDescr;
import symTable.SimpleTypeDescr;
import symTable.SymTable;
import instructions.IntVal;
import codeGen.CodeGen;

public class IdfNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	public IdfNode(String lexem) {

		name = new String(lexem);
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("IdfNode " + name);
	}	
	
	public String toString() {
		return new String("IdfNode " + name);
	}

	@Override
	public AbstractDescr Compile(SymTable env) {

		int addr = 0;
		if(CodeGen.envs.get(0) != env) addr = CodeGen.envs.get(0).getSize();
		
		//Address
		CodeGen.OutInstr(new IntVal( env.getVariable(name).GetAddr() + addr ));
		//Size
		CodeGen.OutInstr(new IntVal( env.getVariable(name).GetTyp().GetSize() ));

		return null;
	}	
	
	@Override
	public AbstractDescr getDescriptor(SymTable env) {

		return new SimpleTypeDescr(name, 1);
	}

	public String GetS() {
		return name;
	} 
}
