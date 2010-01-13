package abstractTreeNodes;

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

	public void Compile() {
		System.out.println("BinNode " + op);
		r.Compile();
		l.Compile();		
		CodeGen.OutInstr(new BinInstr(op));
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
