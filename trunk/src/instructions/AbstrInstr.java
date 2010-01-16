package instructions;

import java.io.Serializable;

import interpreter.Interpreter;

public class AbstrInstr implements Serializable {

	private static final long serialVersionUID = 1L;

	private int op;

	public int GetOp() {
		return op;
	}

	public void SetOp(int fop) {
		op = fop;
	}

	public void Interpret() {
		System.out.println("Interpret AbstInstr");
	}
	
	public void Print() {
		System.out.println(Interpreter.progcnt + " Interpret AbstInstr");
	}
}
