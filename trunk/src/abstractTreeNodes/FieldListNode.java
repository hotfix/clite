package abstractTreeNodes;
import java.util.List;

public class FieldListNode extends AbstractNode {

	List <AbstractNode> varDecNode;
	public FieldListNode(List<AbstractNode> varDecNode) {
		this.varDecNode = varDecNode;
	}
	
	public String toString(){
		String s = new String("FieldList: ");
		for (int i = 0; i < varDecNode.size(); i++) {
			s += "\n  " + varDecNode.get(i).toString();
		}
		return s;
	}

	@Override
	public void Compile() {
		// TODO Auto-generated method stub
		super.Compile();
	}

}