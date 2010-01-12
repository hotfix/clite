package abstractTreeNodes;

public class FunctionCallNode extends AbstractNode {

	private static final long serialVersionUID = 1L;
	private String	 name;
	private ListNode params;
	
	public FunctionCallNode(String name, ListNode params) {
		
		this.name = name;
		this.params = params;
	}
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("FunctionCallNode: " + name);
		params.print(indentation+2);
	}	
	
	public String toString() {
		return new String("FunctionCallNode: " + name +
				"\n  " + params.toString() );				
	}
	
	@Override
	public void Compile() {

	}
}
