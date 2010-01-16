package instructions;

import interpreter.Interpreter;
import abstractTreeNodes.Ops;

public class BranchInstr extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public BranchInstr(int fop) {
		// System.out.println(fop);
		SetOp(fop);
	};

	public void Interpret() {
		IntVal op1;
		int label;

		System.out.print(Interpreter.progcnt + " Interpret BranchInstr ");
		label = ((IntVal) Interpreter.valuestack.pop()).GetI();
		switch (GetOp()) {
		case Ops.brtop: {
			System.out.println("BT " + label);
			op1 = (IntVal) Interpreter.valuestack.pop();
			if (op1.GetI() == 0)
				Interpreter.progcnt = label;
			else
				Interpreter.progcnt++;
			break;
		}
		case Ops.brfop: {
			System.out.println("BF " + label);
			op1 = (IntVal) Interpreter.valuestack.pop();
			if (op1.GetI() != 0)
				Interpreter.progcnt = label;
			else
				Interpreter.progcnt++;
			break;
		}
		case Ops.jmpop: {
			System.out.println("JMP " + label);
			Interpreter.progcnt = label;
			break;
		}
		}
		;
	}

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret BranchInstr " + GetOp());
		Interpreter.progcnt++;
	};
}
