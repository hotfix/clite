package instructions;

import interpreter.Interpreter;
import abstractTreeNodes.Ops;

public class BinInstr extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public BinInstr(int fop) {
		op = fop;
	};

	public void Interpret() {
		AbstrInstr op1, op2;
		int i1, i2;

		System.out.print(Interpreter.progcnt + " Interpret BinInstr ");
		switch (op) {
		case Ops.addop: {
			op2 = Interpreter.valuestack.pop();
			op1 = Interpreter.valuestack.pop();
			Interpreter.valuestack.push(new IntInstr(((IntInstr) op1).GetI()
					+ ((IntInstr) op2).GetI()));
			break;
		}
		}
		;
		Interpreter.progcnt++;
	};
}
