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
			System.out.println(((IntVal) op1).GetI() + " + "
					+ ((IntVal) op2).GetI());
			Interpreter.valuestack.push(new IntVal(((IntVal) op1).GetI()
					+ ((IntVal) op2).GetI()));
			break;
		}
		case Ops.subop: {
			op2 = Interpreter.valuestack.pop();
			op1 = Interpreter.valuestack.pop();
			System.out.println(((IntVal) op1).GetI() + " - "
					+ ((IntVal) op2).GetI());
			Interpreter.valuestack.push(new IntVal(((IntVal) op1).GetI()
					- ((IntVal) op2).GetI()));
			break;
		}
		case Ops.mulop: {
			op2 = Interpreter.valuestack.pop();
			op1 = Interpreter.valuestack.pop();
			System.out.println(((IntVal) op1).GetI() + " * "
					+ ((IntVal) op2).GetI());
			Interpreter.valuestack.push(new IntVal(((IntVal) op1).GetI()
					* ((IntVal) op2).GetI()));
			break;
		}
		case Ops.divop: {
			op2 = Interpreter.valuestack.pop();
			op1 = Interpreter.valuestack.pop();
			System.out.println(((IntVal) op1).GetI() + " / "
					+ ((IntVal) op2).GetI());
			Interpreter.valuestack.push(new IntVal(((IntVal) op1).GetI()
					/ ((IntVal) op2).GetI()));
			break;
		}
		case Ops.assop: {
			int j = 0, size;
			size = ((IntVal) Interpreter.valuestack.pop()).GetI();
			op1 = Interpreter.valuestack.pop(); // intinstr
			i1 = ((IntVal) op1).GetI();
			while (j < size) {
				op2 = Interpreter.valuestack.pop(); // intinstr
				i2 = ((IntVal) op2).GetI();
				System.out.println("addr(" + i1 + ") := " + i2);
				Interpreter.storage.set(i1 + size - (j + 1), new IntVal(i2));
				j++;
			}
			break;
		}
		case Ops.eqop: {
			op2 = Interpreter.valuestack.pop(); // intinstr
			op1 = Interpreter.valuestack.pop(); // intinstr
			i1 = ((IntVal) op1).GetI();
			i2 = ((IntVal) op2).GetI();
			System.out.println(i1 + " == " + i2);
			if (i1 == i2)
				Interpreter.valuestack.push(new IntVal(0));
			else
				Interpreter.valuestack.push(new IntVal(1));
			break;
		}
		case Ops.neqop: {
			op2 = Interpreter.valuestack.pop(); // intinstr
			op1 = Interpreter.valuestack.pop(); // intinstr
			i1 = ((IntVal) op1).GetI();
			i2 = ((IntVal) op2).GetI();
			System.out.println(i1 + " <> " + i2);
			if (i1 != i2)
				Interpreter.valuestack.push(new IntVal(0));
			else
				Interpreter.valuestack.push(new IntVal(1));
			break;
		}
		case Ops.ltop: {
			op2 = Interpreter.valuestack.pop(); // intinstr
			op1 = Interpreter.valuestack.pop(); // intinstr
			i1 = ((IntVal) op1).GetI();
			i2 = ((IntVal) op2).GetI();
			System.out.println(i1 + " < " + i2);
			if (i1 < i2)
				Interpreter.valuestack.push(new IntVal(0));
			else
				Interpreter.valuestack.push(new IntVal(1));
			break;
		}
		case Ops.leop: {
			op2 = Interpreter.valuestack.pop(); // intinstr
			op1 = Interpreter.valuestack.pop(); // intinstr
			i1 = ((IntVal) op1).GetI();
			i2 = ((IntVal) op2).GetI();
			System.out.println(i1 + " <= " + i2);
			if (i1 <= i2)
				Interpreter.valuestack.push(new IntVal(0));
			else
				Interpreter.valuestack.push(new IntVal(1));
			break;
		}
		case Ops.geop: {
			op2 = Interpreter.valuestack.pop(); // intinstr
			op1 = Interpreter.valuestack.pop(); // intinstr
			i1 = ((IntVal) op1).GetI();
			i2 = ((IntVal) op2).GetI();
			System.out.println(i1 + " >= " + i2);
			if (i1 >= i2)
				Interpreter.valuestack.push(new IntVal(0));
			else
				Interpreter.valuestack.push(new IntVal(1));
			break;
		}
		case Ops.gtop: {
			op2 = Interpreter.valuestack.pop(); // intinstr
			op1 = Interpreter.valuestack.pop(); // intinstr
			i1 = ((IntVal) op1).GetI();
			i2 = ((IntVal) op2).GetI();
			System.out.println(i1 + " > " + i2);
			if (i1 > i2)
				Interpreter.valuestack.push(new IntVal(0));
			else
				Interpreter.valuestack.push(new IntVal(1));
			break;
		}
		}
		;
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.println(Interpreter.progcnt + " Interpret BinInstr " + op);
		Interpreter.progcnt++;
	};
}
