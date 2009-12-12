package abstractTreeNodes;

public class StructDecNode extends AbstractNode {
	
	AbstractNode structNode;
	String name;

	public StructDecNode(String structName, AbstractNode fieldlist) {
		structNode = fieldlist;
		name = structName;
	}


	public StructDecNode(AbstractNode fieldlist) {
		structNode = fieldlist;
	}

	public String toString(){		
		return "StructDec: \n" + structNode.toString();
	}

}
