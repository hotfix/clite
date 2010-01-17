package instructions;

import interpreter.Interpreter;

public class PopInstr extends AbstrInstr {
	private static final long serialVersionUID = 1L;

	public PopInstr() {
	}

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret PopInstr ");
		Interpreter.valuestack.pop();
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret UnInstr ");
		Interpreter.progcnt++;
	};
}
