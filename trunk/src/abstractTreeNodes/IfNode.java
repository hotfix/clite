package abstractTreeNodes;

import codeGen.*;
import instructions.*;

public class IfNode extends AbstractNode {

	private static final long serialVersionUID = 1L;

	AbstractNode e;
	AbstractNode st1, st2;

	public IfNode() {
		op = Ops.ifop;
		e = null;
		st1 = null;
		st2 = null;
	};

	public IfNode(AbstractNode fe, AbstractNode fst1, AbstractNode fst2) {
		op = Ops.ifop;
		e = fe;
		st1 = fst1;
		st2 = fst2;
	};

	public void SetE(AbstractNode fe) {
		e = fe;
	};

	public void SetSt1(AbstractNode fst1) {
		st1 = fst1;
	};

	public void SetSt2(AbstractNode fst2) {
		st2 = fst2;
	};

	public AbstractNode GetE() {
		return e;
	};

	public AbstractNode GetSt1() {
		return st1;
	};

	public AbstractNode GetSt2() {
		return st2;
	};

	public void Compile() {
		int l1, l2;

		System.out.println("IfNode");
		l1 = CodeGen.NewLabel();
		l2 = CodeGen.NewLabel();
		e.Compile();
		CodeGen.OutInstr(new BranchInstr(Ops.brfop, l1));
		st1.Compile();
		CodeGen.OutInstr(new BranchInstr(Ops.jmpop, l2));
		CodeGen.DefLabel(l1);
		st2.Compile();
		CodeGen.DefLabel(l2);
	};
}