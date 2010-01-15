package abstractTreeNodes;

import instructions.IntVal;

import codeGen.CodeGen;

import symTable.AbstractDescr;
import symTable.ArrayDescr;
import symTable.SimpleTypeDescr;
import symTable.SymTable;

public class ArrayNode extends AbstractNode {

	private static final long serialVersionUID = 1L;

	private int size;
	private AbstractNode type;

	public ArrayNode() {
		
		op = Ops.arraytyp;
	}

	public ArrayNode(int size) {

		op = Ops.arraytyp;
		this.size = size;
	}

	public void SetType(AbstractNode type) {
		this.type = type;
	}

	public AbstractNode GetType() {
		return type;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public int getStorageSize() {
		if (type.GetOp() == Ops.arraytyp)
			return (size * ((ArrayNode) type).getStorageSize());
		else
			return size;
	}

	public void print(int indentation) {

		for (int i = 0; i < indentation; i++)
			System.out.print(' ');
		System.out.println("ArrayNode " + size);
		GetType().print(indentation + 2);
	}

	public String toString() {
		return new String("ArrayNode : " + size + "\n  " + GetType().toString());
	}

	
	//TODO: getdescriptor

	@Override
	public ArrayDescr Compile(SymTable env) {
		
		System.out.println("ArrayNode::Compile");
		
		AbstractDescr basedescr = null;
		if (type.GetOp() == Ops.arraytyp) {
			basedescr = ((ArrayNode) type).Compile(env);
		} 
		else if (type.GetOp() == Ops.structtyp) {
			basedescr = ((StructDecNode) type).Compile(env);
		} 
		else {
			basedescr = new SimpleTypeDescr(((IdfNode) type).GetS(), 1);
		}
		int actual_size = basedescr.GetSize() * size;
		
		//write default values (0) to init the array by declaration
		for(int i = 0; i < actual_size; i++) CodeGen.OutInstr(new IntVal(0));
		
		return new ArrayDescr(size, actual_size, basedescr);
	}
}
