package codeGen;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import symTable.VarEntry;

import com.sun.org.apache.xerces.internal.util.SymbolTable;

import instructions.*;
import abstractTreeNodes.*;

public class CodeGen {

	FileInputStream in;
	ObjectInputStream s;

	AbstractNode root;

	public static ArrayList<AbstrInstr> progst = new ArrayList<AbstrInstr>();
	public static int progcnt = 0;
	public static int labcnt = 0;
	public static int[] labels = new int[10000];
	public static ArrayList<HashMap<Integer, VarEntry>> envs = new ArrayList<HashMap<Integer, VarEntry>>();
	//public static Map<Integer, VarEntry> envs = new HashMap<Integer, VarEntry>();
	public static int level = 0;

	public static void OutInstr(AbstrInstr instr) {
		
		System.out.println("OutInstr " + (progcnt));
		progst.add(instr);
		progcnt++;
	}

	public CodeGen(String filename) throws IOException, ClassNotFoundException {
		
		in = new FileInputStream(filename);
		s = new ObjectInputStream(in);

		System.out.println("== CodeGen abstract tree wieder einlesen ==");
		root = (AbstractNode) s.readObject();
	};

	public void start(String instrfile) throws IOException {

		FileOutputStream f = new FileOutputStream(instrfile);
		ObjectOutputStream os = new ObjectOutputStream(f);

		System.out.println("== CodeGen auf gehts ==");

		((ProgNode) root).Compile();

		System.out.println("== CodeGen code für Interpretierer rausschreiben ==");
		os.writeObject(progst);
		os.flush();
	}

	public static int NewLabel() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void DefLabel(int l1) {
		// TODO Auto-generated method stub

	};

}
