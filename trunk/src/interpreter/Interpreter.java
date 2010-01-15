package interpreter;

import instructions.AbstrInstr;
import instructions.IntVal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Stack;

import symTable.AbstractDescr;
import symTable.ArrayDescr;
import symTable.SimpleTypeDescr;
import symTable.VarEntry;
import abstractTreeNodes.Ops;
import codeGen.CodeGen;

public class Interpreter {

	public static int progcnt;
	public static ArrayList<AbstrInstr> progstg;
	public static Stack<AbstrInstr> valuestack;

	public static ArrayList<AbstrInstr> storage;

	public static int RK = 0, SP = 0, FP = 0;
	static final int MAXLEVEL = 5;
	public static int[] SL = new int[MAXLEVEL + 1];

	// für Testausgabe der Variablenbelegung geht bei Prozeduren schief!!
	public static ArrayList<String> l = new ArrayList<String>();

	FileInputStream in;
	ObjectInputStream s;

	public Interpreter(String filename) throws IOException, ClassNotFoundException {
		
		in = new FileInputStream(filename);
		s = new ObjectInputStream(in);

		System.out.println("== code für Interpretierer wieder einlesen ==");
		progstg = (ArrayList<AbstrInstr>) s.readObject();
		progcnt = 0;

		storage = new ArrayList<AbstrInstr>();
		valuestack = new Stack<AbstrInstr>();
	};

	public void Printvalues() {
		int i;
		AbstractDescr d = null;
		String name = "";
		VarEntry entry = null;
		int addr = 0;
		int arraysize = 0;

		i = 0;
		while (i < l.size()) {
			name = l.get(i);
			entry = (VarEntry) ((CodeGen.envs.get(CodeGen.level)).getVariable(name));
			addr = entry.GetAddr();
			d = entry.GetTyp();
			if (d.GetOp() == Ops.strop) {
				name = ((SimpleTypeDescr) d).GetName();
				addr = entry.GetAddr();
				System.out.println(name + "(" + addr + ") = "
						+ ((IntVal) (storage.get(addr))).GetI());
			} else if (d.GetOp() == Ops.arraytyp) {
				int j = 0;
				arraysize = ((ArrayDescr) d).GetSize();
				while (j < arraysize) {
					System.out.println(name + "(" + addr + j + ") = "
							+ ((IntVal) (storage.get(addr + j))).GetI());
					j++;
				}
			} else {
				System.out.println("record");
			}
			i++;
		}

	}

	public void start() {
		int i, maxstorage;

		// Instruktionen ausgeben
		System.out.println("*** dump instructions begin");
		i = progstg.size();
		progcnt = 0;
		while (progcnt < i) {
			progstg.get(progcnt).Print();
		}
		System.out.println("*** dump instructions end");

		// Speicher initialisieren;
		
		
		//maxstorage = ((IntVal) (progstg.get(1))).GetI();
		//SP = maxstorage;
		
		
		SL[0] = 0;
		SL[1] = 0;
		SL[2] = 0;
		SL[3] = 0;
		SL[4] = 0;
		SL[5] = 0;

		SP = CodeGen.envs.get(0).getSize();
		for (i = 0; i < SP; i++) {
			Interpreter.storage.add(new IntVal(0));
		}
		

		// Instruktionen interpretieren
		i = progstg.size();
		//progcnt = ((IntVal) progstg.get(0)).GetI();
		progcnt = 0;
		while (progcnt < i) {
			progstg.get(progcnt).Interpret();
		}

		/*
		 * am Ende Speicherbelegung ausgeben geht bei Prozedurn schief!
		 * System.out.println("Und das ist die Variablenbelegung:");
		 * Printvalues();
		 */

	}
}
