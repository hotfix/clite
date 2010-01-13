package symTable;

import abstractTreeNodes.Ops;

public class StructDescr extends AbstractDescr{

	private static final long serialVersionUID = 1L;
	
	public int getAddr() {
		return addr;
	}

	public void setAddr(int addr) {
		this.addr = addr;
	}

	public SymTable getRecenv() {
		return recenv;
	}

	public void setRecenv(SymTable recenv) {
		this.recenv = recenv;
	}

	private int addr; // start address of first field
	private SymTable recenv;

	public StructDescr() {
		op = Ops.structtyp;
		size = 0;
		addr = 0;
		recenv = new SymTable();
	}

	public StructDescr(int fs, SymTable sym) {
		op = Ops.structtyp;
		size = fs;
		recenv = sym;
		addr = 0;
	}
}