package abstractTreeNodes;

import java.util.List;

public class StructDecNode extends AbstractNode {
	
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
		idlist = null;
	}
	
	

	public StructDecNode(IdfNode structName, AbstractNode fieldlist,
			AbstractNode identList) {
		structNode = fieldlist;
		name = structName;
		idlist = identList;
		
	}



	public AbstractNode getIdlist() {
		return idlist;
	}


	public void setIdlist(AbstractNode idlist) {
		this.idlist = idlist;
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
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("StructDecNode");
		getName().print(indentation +2);
		getStructNode().print(indentation+2);
		getIdlist().print(indentation+2);
	}

	



	public String toString(){		
		return "StructDecNode: \n" + structNode.toString();
	}

}
