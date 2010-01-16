package abstractTreeNodes;

import instructions.BranchInstr;
import instructions.IntVal;
import codeGen.CodeGen;
import symTable.AbstractDescr;
import symTable.SymTable;

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

	/* (non-Javadoc)
	 * @see abstractTreeNodes.AbstractNode#Compile()
	 */
	@Override
	public void Compile() {
		int l1, l2;

		System.out.println("WhileNode");
		l1 = CodeGen.NewLabel();
		l2 = CodeGen.NewLabel();
		
		expr.Compile();
		CodeGen.OutInstr(new IntVal(l1));
		CodeGen.OutInstr(new BranchInstr(Ops.brfop));
		
		st.Compile();
		CodeGen.OutInstr(new IntVal(l2));
		CodeGen.OutInstr(new BranchInstr(Ops.jmpop));
		CodeGen.DefLabel(l1);
		CodeGen.DefLabel(l2);
	}

	/* (non-Javadoc)
	 * @see abstractTreeNodes.AbstractNode#Compile(symTable.SymTable)
	 */
	@Override
	public AbstractDescr Compile(SymTable env) {
		int l1, l2;

		System.out.println("WhileNode");
		l1 = CodeGen.NewLabel();
		l2 = CodeGen.NewLabel();
		CodeGen.DefLabel(l2);
		expr.Compile(env);
		CodeGen.OutInstr(new IntVal(l1));
		CodeGen.OutInstr(new BranchInstr(Ops.brfop));
		
		st.Compile(env);
		CodeGen.OutInstr(new IntVal(l2));
		CodeGen.OutInstr(new BranchInstr(Ops.jmpop));
		
		CodeGen.DefLabel(l1);
		CodeGen.DefLabel(l2);
		return null;
	}

}
