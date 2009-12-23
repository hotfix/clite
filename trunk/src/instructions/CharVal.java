package instructions;

import interpreter.Interpreter;
import abstractTreeNodes.Ops;

public class CharVal extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	char c;;

	public CharVal(char fc) {
		op = Ops.charop;
		c = fc;
	};

	public char GetC() {
		return c;
	};

	public void Setc(char fc) {
		c = fc;
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret CharVal " + c);
		Interpreter.valuestack.push(this);
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret CharVal " + c);
		Interpreter.progcnt++;
	};
}
