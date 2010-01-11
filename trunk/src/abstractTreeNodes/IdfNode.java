package abstractTreeNodes;

import symTable.VarEntry;
import instructions.IntVal;
import codeGen.CodeGen;

public class IdfNode extends AbstractNode {

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
	public void Compile() {
		//Address
		CodeGen.OutInstr(new IntVal( ((VarEntry)CodeGen.Search(name).GetE()).GetAddr() ));
		//Size
		CodeGen.OutInstr(new IntVal( ((VarEntry)CodeGen.Search(name).GetE()).GetTyp().GetSize() ));
	} 
}
