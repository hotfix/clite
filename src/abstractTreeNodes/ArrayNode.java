package abstractTreeNodes;

public class ArrayNode extends AbstractNode {

	private static final long serialVersionUID = 1L;

	private int size;
	private AbstractNode type;
	
	public ArrayNode () {}
	
	public ArrayNode(int size) {
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

	public String toString() {
		return new String("ArrayNode : " + size +
				"\n  " + GetType().toString());				
	}
	
	@Override
	public void Compile() {

	}
}
