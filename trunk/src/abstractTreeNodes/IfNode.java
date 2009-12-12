package abstractTreeNodes;

import java.util.List;

import codeGen.*;
import instructions.*;

public class IfNode extends AbstractNode {

	private static final long serialVersionUID = 1L;

	AbstractNode e;
	// Listen mit Statements, st1 = then Statements, st2 else- Statements
	List<AbstractNode> st1, st2; 

	public IfNode() {
		op = Ops.ifop;
		e = null;
		st1 = null;
		st2 = null;
	};

	public IfNode(AbstractNode fe, AbstractNode fst1, AbstractNode fst2) {
		op = Ops.ifop;
		e = fe;
//		st1 = fst1;
//		st2 = fst2;
	};
	
	public IfNode(AbstractNode fe, List<AbstractNode> lst1, List<AbstractNode> lst2) {
		op = Ops.ifop;
		e = fe;
		st1 = lst1;
		st2 = lst2;
	};


	public void SetE(AbstractNode fe) {
		e = fe;
	};

	public void SetSt1(List<AbstractNode> fst1) {
		st1 = fst1;
	};

	public void SetSt2(List<AbstractNode> fst2) {
		st2 = fst2;
	};

	public AbstractNode GetE() {
		return e;
	};

	public List<AbstractNode> GetSt1() {
		return st1;
	};

	public List<AbstractNode> GetSt2() {
		return st2;
	};
	
	public void print(int indentation) {
		
		for(int i = 0; i < indentation; i++) System.out.print(' ');
		System.out.println("IfNode ");
		GetE().print(indentation+2);
		for (int i = 0; i < st1.size(); i++) {
			GetSt1().get(i).print(indentation+2);
		}
		for (int i = 0; i < st2.size(); i++) {
			GetSt2().get(i).print(indentation+2);
		}
	}
	
	public String toString(){
		String s = new String("IfNode: ");
			s += e.toString();
		//if (st1 == null){
			for (int i = 0; i < st2.size(); i++) {
				s += "\nTHEN \n" + st2.get(i).toString();				
			}
		//} else{
			for (int i = 0; i < st1.size(); i++) {
				s += "\n ELSE\n" + st1.get(i).toString();				
			}
		//}
		
		return s;
	}

	public void Compile() {
		int l1, l2;

		System.out.println("IfNode");
		l1 = CodeGen.NewLabel();
		l2 = CodeGen.NewLabel();
		e.Compile();
		CodeGen.OutInstr(new BranchInstr(Ops.brfop, l1));
		for (int i = 0; i < st1.size(); i++) {
			st1.get(i).Compile();
		}
		
		CodeGen.OutInstr(new BranchInstr(Ops.jmpop, l2));
		CodeGen.DefLabel(l1);
		
		for (int i = 0; i < st2.size(); i++) {
			st2.get(i).Compile();
		}
		CodeGen.DefLabel(l2);
	};
}