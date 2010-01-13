package symTable;

import abstractTreeNodes.Ops;

public class ArrayDescr extends AbstractDescr {
	
	private static final long serialVersionUID = 1L;
	
	private int numberelems;
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

	public int getNumberOfElements() {
		return numberelems;
	}
	
	public AbstractDescr getType() {
		return basetype;
	}
	
	@Override
	public String toString() {		
		return "ArrayDescr [numberelems=" + numberelems + ", size=" + size + ", basetype=" + basetype.toString() + "]";
	}	
}
