package abstractTreeNodes;

import symTable.VarEntry;
import instructions.ContInstr;
import instructions.IntVal;
import codeGen.CodeGen;

public class ContNode extends AbstractNode {

	private static final long serialVersionUID = 1L;
	
	private AbstractNode child;	
	public ContNode(AbstractNode child) {
		
		this.child = child;
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("ContNode");
		child.print(indentation+2);
	}	
	
	public String toString() {
		return new String("ContNode" + 
				"\n  " + child.toString() );				
	}
	
	@Override
	public void Compile() {
		System.out.println("ContNode::Compile");
		
		child.Compile();
		CodeGen.OutInstr(new ContInstr());

	}
}
