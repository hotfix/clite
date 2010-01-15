package abstractTreeNodes;

import java.util.List;

public class FunctionDecNode extends AbstractNode {
	private static final long serialVersionUID = 1L;

	AbstractNode returnType;
	String functionName;
	AbstractNode formalParams;
	List<AbstractNode> statements;

	public FunctionDecNode(AbstractNode returnType, String functionName,
			AbstractNode formalParams, List<AbstractNode> statements) {
		this.returnType = returnType;
		this.functionName = functionName;
		this.formalParams = formalParams;
		this.statements = statements;
	}

	public AbstractNode getFormalParams() {
		return formalParams;
	}

	public void setFormalParams(AbstractNode formalParams) {
		this.formalParams = formalParams;
	}

	public List<AbstractNode> getStatements() {
		return statements;
	}

	public void setStatements(List<AbstractNode> statements) {
		this.statements = statements;
	}

	public void print(int indentation) {

		for (int i = 0; i < indentation; i++)
			System.out.print(' ');
		System.out.println("FunctionDecNode: " + functionName);
		if (returnType != null) returnType.print(indentation + 2);
		getFormalParams().print(indentation + 2);
		for (int i = 0; i < getStatements().size(); i++) {
			getStatements().get(i).print(indentation + 2);
		}
	}

	public String toString() {
		String s = "BinNode: " + op;

		s += "\n  " + getFormalParams().toString();

		for (int i = 0; i < getStatements().size(); i++) {
			s += "\n  " + getStatements().get(i).toString();
		}
		return s;
	}

	public void Compile() {
		System.out.println("FunctionDecNode ");
		if (formalParams != null){
			formalParams.Compile();
			//CodeGen.OutInstr(null);
		}
		
		for (int i = 0; i < statements.size(); i++) {
			statements.get(i).Compile();
		}
		
	}

}
																																																																					