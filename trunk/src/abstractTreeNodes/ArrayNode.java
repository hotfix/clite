package abstractTreeNodes;

public class ArrayNode extends AbstractNode {

	private static final long serialVersionUID = 1L;	
	
	private int size;
	private AbstractNode type;
	
	public ArrayNode () {
		nodeName = "ArrayNode";
	}
	
	public ArrayNode(int size) {
		
		nodeName = "ArrayNode";
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
		if (type.nodeName.equals("ArrayNode") == true) return(size*((ArrayNode)type).getStorageSize());
		else return size;
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("ArrayNode " + size);
		GetType().print(indentation+2);
	}	

	public String toString() {
		return new String("ArrayNode : " + size +
				"\n  " + GetType().toString());				
	}
	
	@Override
	public void Compile() {

	}
}
