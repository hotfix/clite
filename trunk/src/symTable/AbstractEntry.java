package symTable;

import java.io.Serializable;

public class AbstractEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	int op;

	public AbstractEntry() {
		op = 0;
	};

	public int GetOp() {
		return op;
	}

}
