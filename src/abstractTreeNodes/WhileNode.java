package abstractTreeNodes;

public class WhileNode extends AbstractNode {

	private static final long serialVersionUID = 1L;

	private AbstractNode	expr;
	private ListNode 		st;

	public WhileNode() {
		op		= Ops.whileop;
		expr	= null;
		st		= null;
	};

	public WhileNode(AbstractNode expr, ListNode st) {
		op	= Ops.ifop;
		this.expr= expr;
		this.st = st;
	};

	public void SetExpr(AbstractNode expr) {
		this.expr = expr;
	};

	public void SetSt(ListNode st) {
		this.st = st;
	};

	public AbstractNode GetExpr() {
		return expr;
	};

	public AbstractNode GetSt() {
		return st;
	};
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("WhileNode");
		expr.print(indentation+2);
		st.print(indentation+2);
	}	
	
	public String toString() {
		
		return new String("WhileNode" + 
				"\n  " + expr.toString() +
				"\n  " + st.toString());
	}

}
