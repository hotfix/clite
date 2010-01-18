package abstractTreeNodes;

import symTable.AbstractDescr;
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

	public String toString() {
		return "StructDecNode: \n" + fieldlist.toString();
	}

	@Override
	public AbstractDescr getDescriptor(SymTable env) {
		SymTable struct_env = new SymTable();	
		fieldlist.Compile(struct_env);
		return new StructDescr(struct_env.getSize(), struct_env);
	}
}
