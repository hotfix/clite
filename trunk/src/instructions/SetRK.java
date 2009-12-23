package instructions;

import interpreter.Interpreter;

public class SetRK extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public SetRK() {
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret SetRK");
		Interpreter.RK = ((IntVal) Interpreter.valuestack.pop()).GetI();
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret SetRK");
		Interpreter.progcnt++;
	};
}
