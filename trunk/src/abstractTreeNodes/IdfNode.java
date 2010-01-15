package abstractTreeNodes;

import instructions.IntVal;
import symTable.AbstractDescr;
import symTable.SimpleTypeDescr;
import symTable.SymTable;
import symTable.VarEntry;
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
		//Address
		CodeGen.OutInstr(new IntVal( ((VarEntry)CodeGen.Search(name).GetE()).GetAddr() ));
		//Size
		CodeGen.OutInstr(new IntVal( ((VarEntry)CodeGen.Search(name).GetE()).GetTyp().GetSize() ));
		
		return null;
	}	
	
	public AbstractDescr getDescriptor() {

		return new SimpleTypeDescr(name, 1);
	}

	public String GetS() {
		return name;
	} 
}
