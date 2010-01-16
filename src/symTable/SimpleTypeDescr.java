package symTable;

public class SimpleTypeDescr extends AbstractDescr {	

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int size;
	
	
	public SimpleTypeDescr(){
		name = "";
		size = 0;		
	}
	
	public SimpleTypeDescr(String name, int size){
		this.name = name;
		this.size = size;		
	}
	
	public String GetName() {	
		return name;
	}
	
	public int GetSize() {
		return size;
	}
	
	@Override
	public String toString() {
		return "SimpleTypeDescr [name=" + name + ", size=" + size + ", const=" + isConst() + "]";
	}
}
