package abstractTreeNodes;

import symTable.AbstractDescr;
import symTable.SymTable;
import instructions.ContInstr;
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
		System.out.println("ContNode::Compile2");
		
		child.Compile();
		CodeGen.OutInstr(new ContInstr());

	}

	@Override
	public AbstractDescr Compile(SymTable env) {
		System.out.println("ContNode::Compile");
	
		child.Compile(env);
		CodeGen.OutInstr(new ContInstr());
		return null;
	}
}
