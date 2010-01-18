package abstractTreeNodes;

import symTable.AbstractDescr;
import symTable.SymTable;
import instructions.BinInstr;
import codeGen.CodeGen;

public class BinNode extends AbstractNode {

	private static final long serialVersionUID = 1L;

	private AbstractNode l;
	private AbstractNode r;

	public BinNode() {
		op = 0;
		l = null;
		r = null;
	}

	public BinNode(int fop) {
		op = fop;
		l = null;
		r = null;
	}

	public BinNode(int fop, AbstractNode fl, AbstractNode fr) {
		op = fop;
		l = fl;
		r = fr;
	}

	public void SetL(AbstractNode fl) {
		l = fl;
	}

	public void SetR(AbstractNode fr) {
		r = fr;
	}

	public AbstractNode GetL() {
		return l;
	}

	public AbstractNode GetR() {
		return r;
	}
	
	@Override
	public AbstractDescr Compile(SymTable env) {
		System.out.println("BinNode " + op);
		r.Compile(env);
		l.Compile(env);		
		CodeGen.OutInstr(new BinInstr(op));
		return null;
	}

	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("BinNode " + op);
		GetL().print(indentation+2);
		GetR().print(indentation+2);
	}
	
	public String toString() {
		return new String("BinNode: " + op + 
				"\n  " + GetL().toString() +
				"\n  " + GetR().toString());
	}
}
