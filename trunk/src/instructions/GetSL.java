package instructions;

import interpreter.Interpreter;

public class GetSL extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public GetSL() {
	};

	public void Interpret() {
		int level, wert;
		level = ((IntVal) Interpreter.valuestack.pop()).GetI();
		wert = Interpreter.SL[level];
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret GetSL" + level + " " + wert);
		Interpreter.valuestack.push(new IntVal(wert));
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret GetSL");
		Interpreter.progcnt++;
	};
}
