package abstractTreeNodes;
import java.util.List;

public class ListNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List <AbstractNode> list;
	public ListNode(List<AbstractNode> list) {
		this.list = list;
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("ListNode");
		for(int i = 0; i < list.size(); i++) {
			list.get(i).print(indentation+2);
		}
	}
	
	public String toString(){
		String s = new String("ListNode: ");
		for (int i = 0; i < list.size(); i++) {
			s += "\n  " + list.get(i).toString();
		}
		return s;
	}

	@Override
	public void Compile() {
		// TODO Auto-generated method stub
		super.Compile();
	}

}