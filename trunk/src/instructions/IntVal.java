package instructions;

import interpreter.Interpreter;
import abstractTreeNodes.Ops;

public class IntVal extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	int i;

	public IntVal(int fi) {
		SetOp(Ops.intop);
		i = fi;
	};

	public int GetI() {
		return i;
	};

	public void SetI(int fi) {
		i = fi;
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret IntVal " + i);
		Interpreter.valuestack.push(this);
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret IntVal " + i);
		Interpreter.progcnt++;
	};
}
