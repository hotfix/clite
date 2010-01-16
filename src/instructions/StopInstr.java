package instructions;

import interpreter.Interpreter;
import abstractTreeNodes.Ops;

public class StopInstr extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public StopInstr() {
		SetOp(Ops.stpop);
	};

	public void Interpret()

	{
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret StopInstr");
		Interpreter.progcnt++;
	};

	public void Print()

	{
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret StopInstr");
		Interpreter.progcnt++;
	};
}
