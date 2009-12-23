package symTable;

import abstractTreeNodes.Ops;

public class ArrayDescr extends AbstractDescr {
	
	private static final long serialVersionUID = 1L;
	
	private int numberelems;
	private int size;
	private AbstractDescr basetype;

	public ArrayDescr() {
		op = Ops.arraytyp;
		numberelems = 0;
		size = 0;
		basetype = null;
	}

	public ArrayDescr(int fn, int fs, AbstractDescr fb) {
		op = Ops.arraytyp;
		numberelems = fn;
		size = fs;
		basetype = fb;
	}
}
