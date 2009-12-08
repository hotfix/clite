package abstractTreeNodes;

public class NegationNode extends AbstractNode {
	
	private AbstractNode child;	
	
	public NegationNode(AbstractNode child) {
		this.child = child;
	}

	@Override
	public void Compile() {
		// TODO Auto-generated method stub
		super.Compile();
	}

	public String toString() {
		return new String("NegationNode " + "\n  " + child.toString());
	}
}
