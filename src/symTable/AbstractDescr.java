package symTable;

import java.io.Serializable;

public class AbstractDescr implements Serializable {

	private static final long serialVersionUID = 1L;

	protected int op;
	protected int size;
	private boolean isconst = false;

	public AbstractDescr() {
		op = 0;
		size = 0;
	}
	
	public boolean isConst() {
		return isconst;
	}
	
	public void setConst(boolean c) {
		isconst = c;
	}

	public int GetOp() {
		return op;
	}

	public int GetSize() {
		return size;
	}
}
