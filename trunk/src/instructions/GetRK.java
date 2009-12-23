package instructions;

import interpreter.Interpreter;

public class GetRK extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public GetRK() {
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret GetRK " + Interpreter.RK);
		Interpreter.valuestack.push(new IntVal(Interpreter.RK));
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret GetRK ");
		Interpreter.progcnt++;
	}
}
