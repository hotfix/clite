package instructions;

import java.io.Serializable;

public class AbstrInstr implements Serializable{

 private static final long serialVersionUID = 1L;

 int op;

 public int GetOp(){return op;};
 
 public void SetOp(int fop){op = fop;};
 
 public void Interpret()
 {System.out.println("Interpret AbstInstr");};	
}
