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
	};

	public BinNode(int fop) {
		op = fop;
		l = null;
		r = null;
	};

	public BinNode(int fop, AbstractNode fl, AbstractNode fr) {
		op = fop;
		l = fl;
		r = fr;
	};

	public void SetL(AbstractNode fl) {
		l = fl;
	};

	public void SetR(AbstractNode fr) {
		r = fr;
	};

	public AbstractNode GetL() {
		return l;
	};

	public AbstractNode GetR() {
		return r;
	};

	public void Compile() {
		System.out.println("BinNode " + op);
		l.Compile();
		r.Compile();
		CodeGen.OutInstr(new BinInstr(op));
	};
	
	public String toString() {
		return new String("BinNode: " + op + 
				"\n  " + GetL().toString() +
				"\n  " + GetR().toString());
	}
}
