package codeGen;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import symTable.SymTable;
import symTable.VarEntry;

import instructions.*;
import abstractTreeNodes.*;

public class CodeGen {

	FileInputStream in;
	ObjectInputStream s;

	AbstractNode root;

	// Program Speicher (Prog)
	public static ArrayList<AbstrInstr> progst = new ArrayList<AbstrInstr>();

	public static ArrayList<SymTable> envs = new ArrayList<SymTable>();

	public static int[] labels = new int[10000];
	public static int progcnt = 0;
	public static int labcnt = 0;
	public static int level = 0;

	public static void OutInstr(AbstrInstr instr) {

		System.out.println("OutInstr " + (progcnt));
		progst.add(instr);
		progcnt++;
	}

	public CodeGen(String filename) throws IOException, ClassNotFoundException {

		in = new FileInputStream(filename);
		s = new ObjectInputStream(in);

		System.out.println("\n== CodeGen abstract tree wieder einlesen ==");
		root = (AbstractNode) s.readObject();
	}

	public void start(String instrfile) throws IOException {

		FileOutputStream f = new FileOutputStream(instrfile);
		ObjectOutputStream os = new ObjectOutputStream(f);

		System.out.println("\n== CodeGen auf gehts ==");

		CodeGen.envs.add(new SymTable());
		((/* ProgNode */AbstractNode) root).Compile(envs.get(0));
		FixUp(progst.size());
		System.out.println("\n== CodeGen code für Interpretierer rausschreiben ==");
		os.writeObject(progst);
		os.flush();
	}

	public static void FixUp(int storagelength) {
		AbstrInstr instr;
		int i = 0;
		IntVal label;
		System.out.println("+++ FixUp program length = " + progst.size()
				+ " storagelength = " + storagelength);
//		progst.set(1, new IntVal(storagelength));
//		progst.set(2, new IntVal(progstart));
		while (i < progst.size()) {
			instr = progst.get(i);
			if (instr != null)
				if ((instr.GetOp() == Ops.brfop)
						|| (instr.GetOp() == Ops.brtop)
						|| (instr.GetOp() == Ops.jmpop)) {
					label = (IntVal) progst.get(i - 1);
					label.SetI(CodeGen.labels[label.GetI()]);
					System.out.println(instr.GetOp() + " " + label.GetI());
				}
			i++;
		}
		System.out.println("+++ Ende Fixup");
	}

	public static int NewLabel() {
		return labcnt++;
	}

	public static void DefLabel(int flab) {
		labels[flab] = CodeGen.progcnt;
	}

	public static SearchResult Search(String s) {
		int level;
		VarEntry e = null;
		SymTable lenv;
		level = CodeGen.level;
		while ((level >= 0) && (e == null)) {
			lenv = CodeGen.envs.get(level);
			e = lenv.getVariable(s);
			if (e == null)
				level--;
		}
		return new SearchResult(level, e);
	}

	public static void printEnvs() {

		System.out.println("\n== Symboltabellen ausgabe ==");
		for (int i = 0; i < envs.size(); i++) {
			System.out.println("  Symboltabelle " + i + ":");
			envs.get(i).Print();
		}
	}
}
