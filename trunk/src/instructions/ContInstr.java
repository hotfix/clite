package instructions;

import interpreter.Interpreter;
import abstractTreeNodes.Ops;

public class ContInstr extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	public ContInstr() {
		SetOp(Ops.contop);
	}

	public void Interpret()	{
		IntVal i;

		int addr, size, j = 0;

		size = ((IntVal) (Interpreter.valuestack.pop())).GetI();
		addr = ((IntVal) (Interpreter.valuestack.pop())).GetI();
		while (j < size) {
			i = (IntVal) Interpreter.storage.get(addr + j);
			Interpreter.valuestack.push(i);
			System.out.print(Interpreter.progcnt + " ");
			System.out.println("Interpret ContInstr Addr = "
					+ (addr+j) + " value = " + i.GetI());
			j++;
		}
		Interpreter.progcnt++;
	}

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret ContInstr");
		Interpreter.progcnt++;
	}
}
