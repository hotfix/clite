package abstractTreeNodes;

import instructions.PrintInstr;
import codeGen.CodeGen;

public class PrintNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	private AbstractNode param;
	
	public PrintNode(AbstractNode param) {
		
		this.param = param;
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("PrintNode");
		param.print(indentation+2);
	}	
	
	public String toString() {
		return new String("PrintNode"  + 
				"\n  " + param.toString());
	} 
	
	@Override
	public void Compile() {
		param.Compile();
		CodeGen.OutInstr(new PrintInstr());
	}
}
