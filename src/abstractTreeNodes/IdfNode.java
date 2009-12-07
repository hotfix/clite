package abstractTreeNodes;

public class IdfNode extends AbstractNode {

	private String name;
	
	public IdfNode(String lexem) {

		name = new String(lexem);
	}
	
	public String toString() {
		return new String("IdfNode " + name);
	}

	@Override
	public void Compile() {
	} 
}
