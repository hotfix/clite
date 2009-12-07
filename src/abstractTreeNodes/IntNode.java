package abstractTreeNodes;

public class IntNode extends AbstractNode {
	
	private Integer num;
	
	public IntNode(String i) {
		
		num = new Integer(i);
	}
	
	public String toString() {
		return new String("IntNode " + num.toString());
	} 
	
	@Override
	public void Compile() {
	}
}
