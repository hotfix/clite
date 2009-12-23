package symTable;

import java.io.Serializable;

public class AbstractDescr implements Serializable {

	private static final long serialVersionUID = 1L;

	protected int op;
	private int size;

	public AbstractDescr() {
		op = 0;
		size = 0;
	};

	public int GetOp() {
		return op;
	}

	public int GetSize() {
		return size;
	}
}
