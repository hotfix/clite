package abstractTreeNodes;

import java.util.HashMap;

import codeGen.CodeGen;

import symTable.AbstractDescr;
import symTable.AbstractEntry;
import symTable.ArrayDescr;
import symTable.SimpleTypeDescr;

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


	//(c)Völler
	@Override
	public ArrayDescr Compile(HashMap<String, AbstractEntry> env) {
		
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
		return new ArrayDescr(size, basedescr.GetSize() * size, basedescr);
	}
}
