package abstractTreeNodes;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import symTable.AbstractDescr;
import symTable.SymTable;

public class ListNode extends AbstractNode {

	private static final long serialVersionUID = 1L;
	private List <AbstractNode> list = null;
	
	public ListNode() {
		list = new ArrayList<AbstractNode>();	
	}
	
	public ListNode(List<AbstractNode> list) {
		this.list = list;
	}
	
	public void addNode(AbstractNode node) {		
		list.add(node);
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

		System.out.println("ListNode::Compile");
		for(int i = 0; i < list.size(); i++) {
			list.get(i).Compile();
		}
	}

	@Override
	public AbstractDescr Compile(SymTable env) {
		
		System.out.println("ListNode::Compile2");
		for(int i = 0; i < list.size(); i++) {
			list.get(i).Compile(env);
		}
		
		return null;
	}
	
	

}