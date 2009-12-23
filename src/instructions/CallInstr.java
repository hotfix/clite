package instructions;

import interpreter.Interpreter;

public class CallInstr extends AbstrInstr {

	private static final long serialVersionUID = 1L;

	int addr;

	public CallInstr(int fa) {
		addr = fa;
	};

	public int GetAddr() {
		return addr;
	};

	public void SetAddr(int fa) {
		addr = fa;
	};

	public void Interpret() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret Call " + addr);
		Interpreter.RK = Interpreter.progcnt + 1;
		Interpreter.progcnt = addr;
	};

	public void Print() {
		System.out.print(Interpreter.progcnt + " ");
		System.out.println("Interpret Call " + addr);
		Interpreter.progcnt++;
	};
}
