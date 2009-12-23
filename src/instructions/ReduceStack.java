package instructions;

import interpreter.Interpreter;

public class ReduceStack extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	int length;

	public ReduceStack(int fl) {
		length = fl;
	};

	public void Interpret() {
		int i = 0, last;
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret ReduceStack " + length);
		last = Interpreter.storage.size();
		while (i < length) {
			Interpreter.storage.remove(last - 1 - i);
			i++;
		}
		Interpreter.progcnt++;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret ReduceStack " + length);
		Interpreter.progcnt++;
	};
}