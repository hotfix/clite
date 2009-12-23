package instructions;

import interpreter.Interpreter;

public class GetFP extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public GetFP() {
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret GetFP " + Interpreter.FP);
		Interpreter.valuestack.push(new IntVal(Interpreter.FP));
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret GetFP ");
		Interpreter.progcnt++;
	};
}