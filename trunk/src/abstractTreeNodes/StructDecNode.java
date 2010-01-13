package abstractTreeNodes;

import java.util.HashMap;
import java.util.List;

import symTable.AbstractDescr;
import symTable.AbstractEntry;
import symTable.StructDescr;

public class StructDecNode extends AbstractNode {

	private static final long serialVersionUID = 1L;

	AbstractNode structNode;
	IdfNode name;
	AbstractNode idlist;

	public StructDecNode(IdfNode structName, AbstractNode fieldlist) {
		structNode = fieldlist;
		name = structName;
		idlist = null;
	}

	public StructDecNode(AbstractNode fieldlist) {
		structNode = fieldlist;
		name = null;
	}

	public IdfNode getName() {
		return name;
	}

	public void setName(IdfNode name) {
		this.name = name;
	}

	public AbstractNode getStructNode() {
		return structNode;
	}

	public void setStructNode(AbstractNode structNode) {
		this.structNode = structNode;
	}

	public void print(int indentation) {

		for (int i = 0; i < indentation; i++)
			System.out.print(' ');
		System.out.println("StructDecNode");
		getName().print(indentation + 2);
		getStructNode().print(indentation + 2);
	}

	@Override
	public void Compile() {
		name.Compile();
		structNode.Compile();
		// idlist.Compile();
	}

	public String toString() {
		return "StructDecNode: \n" + structNode.toString();
	}

	public void Compile(StructDescr sd, HashMap<String, AbstractEntry> env) {
		
	}
}
