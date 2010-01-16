package instructions;

import interpreter.Interpreter;
import abstractTreeNodes.Ops;

public class RealVal extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	float r;

	public RealVal(float fr) {
		SetOp(Ops.realop);
		r = fr;
	};

	public float GetR() {
		return r;
	};

	public void SetR(float fr) {
		r = fr;
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret RealVal " + r);
		Interpreter.valuestack.push(this);
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret RealVal " + r);
		Interpreter.progcnt++;
	};
}
