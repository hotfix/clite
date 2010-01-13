package abstractTreeNodes;

import java.util.HashMap;
import java.util.List;

import symTable.AbstractDescr;
import symTable.AbstractEntry;
import symTable.StructDescr;
import symTable.SymTable;

public class StructDecNode extends AbstractNode {

	private static final long serialVersionUID = 1L;

	private AbstractNode fieldlist;
	private IdfNode name;

	public StructDecNode(IdfNode structName, AbstractNode fieldlist) {
		this.fieldlist = fieldlist;
		name = structName;
	}

	public StructDecNode(AbstractNode fieldlist) {
		this.fieldlist = fieldlist;
		name = null;
	}

	public void print(int indentation) {

		for (int i = 0; i < indentation; i++)
			System.out.print(' ');
		System.out.println("StructDecNode");
		name.print(indentation + 2);
		fieldlist.print(indentation + 2);
	}

	@Override
	public void Compile() {
		
		SymTable env = new SymTable();		
		fieldlist.Compile(env);
		// idlist.Compile();
	}

	public String toString() {
		return "StructDecNode: \n" + fieldlist.toString();
	}

//	public AbstractDescr Compile(StructDescr sd, HashMap<String, AbstractEntry> env) {	
//		
//	}
}
