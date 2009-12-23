package instructions;

import interpreter.Interpreter;

public class GetSP extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public GetSP() {
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret GetSP " + Interpreter.SP);
		Interpreter.valuestack.push(new IntVal(Interpreter.SP));
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret GetSP");
		Interpreter.progcnt++;
	};
}