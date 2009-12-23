package instructions;

import interpreter.Interpreter;

public class InitStack extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	int length;

	public InitStack(int fl) {
		length = fl;
	};

	public void Interpret() {
		int i = 0;
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret InitStack");
		while (i < length) {
			Interpreter.storage.add(new IntVal(0));
			i++;
		}
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret InitStack " + length);
		Interpreter.progcnt++;
	};
}
