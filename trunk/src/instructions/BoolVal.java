package instructions;

import interpreter.Interpreter;
import abstractTreeNodes.Ops;

public class BoolVal extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	boolean b;

	public BoolVal(boolean fb) {
		op = Ops.boolop;
		b = fb;
	};

	public boolean GetB() {
		return b;
	};

	public void SetB(boolean fb) {
		b = fb;
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret BoolVal " + b);
		Interpreter.valuestack.push(this);
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret BoolVal " + b);
		Interpreter.progcnt++;
	};
}
