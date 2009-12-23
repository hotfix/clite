package instructions;

import interpreter.Interpreter;

public class SetSP extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public SetSP() {
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret SetSP");
		Interpreter.SP = ((IntVal) Interpreter.valuestack.pop()).GetI();
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret SetSP");
		Interpreter.progcnt++;
	};
}
