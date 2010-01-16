package instructions;

import interpreter.Interpreter;

public class UnInstr extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public UnInstr(int fop) {
		SetOp(fop);
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret UnInstr " + GetOp());
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret UnInstr " + GetOp());
		Interpreter.progcnt++;
	};
}
