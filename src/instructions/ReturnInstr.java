package instructions;

import interpreter.Interpreter;

public class ReturnInstr extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public ReturnInstr() {
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret ReturnInstr " + Interpreter.RK);
		Interpreter.progcnt = Interpreter.RK;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret ReturnInstr ");
		Interpreter.progcnt++;
	};
}
