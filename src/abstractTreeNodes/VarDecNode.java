package abstractTreeNodes;

import java.util.ArrayList;

public class VarDecNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<AssNode> varDec;
	
	public VarDecNode(ArrayList<AssNode> varDec) {

		this.varDec = varDec;
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("VarDecNode");
		for(int i = 0; i < varDec.size(); i++) {
			varDec.get(i).print(indentation+2); 
		}
	}
	
	public String toString() {
		
		String s = new String("VarDecNode");
		for(int i = 0; i < varDec.size(); i++) {
			s += "\n  " + varDec.get(i).toString(); 
		}		
		return s;
	}
	
	@Override
	public void Compile() {

	}

}
