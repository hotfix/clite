package instructions;

import interpreter.Interpreter;

public class UnInstr extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public UnInstr(int fop) {
		op = fop;
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret UnInstr " + op);
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret UnInstr " + op);
		Interpreter.progcnt++;
	};
}
