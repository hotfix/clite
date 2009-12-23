package instructions;

import interpreter.Interpreter;

public class SetFP extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public SetFP() {
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret SetFP");
		Interpreter.FP = ((IntVal) Interpreter.valuestack.pop()).GetI();
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret SetFP");
		Interpreter.progcnt++;
	};
}
