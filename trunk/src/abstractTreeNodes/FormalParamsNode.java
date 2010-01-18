package abstractTreeNodes;

import java.util.Iterator;
import java.util.Map;

import symTable.AbstractDescr;
import symTable.SymTable;

public class FormalParamsNode extends AbstractNode {

	private static final long serialVersionUID = 1L;
	
	Map<AbstractNode, AbstractNode> params;

	public FormalParamsNode(Map<AbstractNode, AbstractNode> params2) {
		this.params = params2;
	}

	public void print(int indentation) {
		Iterator<AbstractNode> it = params.keySet().iterator();
		// wenn es Formale Parameter gibt
		if (!params.isEmpty()) {
			for (int i = 0; i < indentation; i++)
				System.out.print(' ');

			System.out.println("FormalParamsNode ");

			while (it.hasNext()) {
				IdfNode key = (IdfNode) it.next();
				key.print(indentation + 2);
				params.get(key).print(indentation +2);
			}
		}
	}

	public String toString() {
		Iterator<AbstractNode> it = params.keySet().iterator();

		String s = "FormalParamsNode: " + "\n  ";
		while (it.hasNext()) {
			IdfNode key = (IdfNode) it.next();
			s += key.toString() + " = " + params.get(key).toString();
		}
		return s;
	}
	
	public AbstractDescr Compile(SymTable env) {
		
		System.out.println("FormalParamsNode::Compile");
		
		Iterator<AbstractNode> it = params.keySet().iterator();	
		
		while (it.hasNext()) {
			IdfNode key = (IdfNode) it.next();
			// Variable
			key.Compile(env);
			//CodeGen.OutInstr(new IntVal(-1));
			// type
			params.get(key).Compile(env);
			//CodeGen.OutInstr(new IntVal(-1));
		}
		return null;
	}

}
