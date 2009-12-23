package instructions;

import interpreter.Interpreter;
import abstractTreeNodes.Ops;

public class PrintInstr extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public PrintInstr() {
		op = Ops.printop;
	};

	public void Interpret()

	{
		AbstrInstr i;

		i = Interpreter.valuestack.pop();
		if (i.GetOp() == Ops.intop)
			System.out.println(((IntVal) i).GetI());
		else if (i.GetOp() == Ops.strop)
			System.out.println(((StringVal) i).GetS());
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret PrintInstr");
		Interpreter.progcnt++;
	};
}