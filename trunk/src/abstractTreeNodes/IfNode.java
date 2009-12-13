package abstractTreeNodes;

import java.util.List;

import codeGen.*;
import instructions.*;

public class IfNode extends AbstractNode {

	private static final long serialVersionUID = 1L;

	AbstractNode e;
	// Listen mit Statements, st1 = then Statements, st2 else- Statements
	AbstractNode st1, st2; 

	public IfNode() {
		op = Ops.ifop;
		e = null;
		st1 = null;
		st2 = null;
	};

	public IfNode(AbstractNode fe, AbstractNode fst1, AbstractNode fst2) {
		op = Ops.ifop;
		e = fe;
		st1 = fst1;
		st2 = fst2;
	};
	
//	public IfNode(AbstractNode fe, List<AbstractNode> lst1, List<AbstractNode> lst2) {
//		op = Ops.ifop;
//		e = fe;
//		st1 = lst1;
//		st2 = lst2;
//	};


	public void SetE(AbstractNode fe) {
		e = fe;
	};

	public void SetSt1(AbstractNode fst1) {
		st1 = fst1;
	};

	public void SetSt2(AbstractNode fst2) {
		st2 = fst2;
	};

	public AbstractNode GetE() {
		return e;
	};

	public AbstractNode GetSt1() {
		return st1;
	};

	public AbstractNode GetSt2() {
		return st2;
	};
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("IfNode ");
		GetE().print(indentation+2);
		GetSt1().print(indentation+2);
		GetSt2().print(indentation+2);
	}
	
	public String toString(){
		String s = new String("IfNode: ");
		s += e.toString();
	
		s += "\n" + st2.toString();				
		s += "\n" + st1.toString();				
		return s;
	}

	public void Compile() {
		int l1, l2;

		System.out.println("IfNode");
		l1 = CodeGen.NewLabel();
		l2 = CodeGen.NewLabel();
		e.Compile();
		CodeGen.OutInstr(new BranchInstr(Ops.brfop, l1));
		st1.Compile();
		CodeGen.OutInstr(new BranchInstr(Ops.jmpop, l2));
		CodeGen.DefLabel(l1);
		st2.Compile();
		CodeGen.DefLabel(l2);
	};
}