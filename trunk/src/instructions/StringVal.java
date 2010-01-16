package instructions;

import interpreter.Interpreter;
import abstractTreeNodes.Ops;

public class StringVal extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	String s;

	public StringVal(String fs) {
		SetOp(Ops.strop);
		s = fs;
	};

	public String GetS() {
		return s;
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret StrInstr " + s);
		Interpreter.valuestack.push(this);
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret StrInstr " + s);
		Interpreter.progcnt++;
	};
}
