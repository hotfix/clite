package instructions;

import interpreter.Interpreter;

public class SetSL extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public SetSL() {
	};

	public void Interpret() {
		int level, wert;
		level = ((IntVal) Interpreter.valuestack.pop()).GetI();
		wert = ((IntVal) Interpreter.valuestack.pop()).GetI();
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret SetSL" + level + " " + wert);
		Interpreter.SL[level] = wert;
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret SetSL");
		Interpreter.progcnt++;
	};
}
