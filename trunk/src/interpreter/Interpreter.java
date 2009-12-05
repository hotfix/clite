package interpreter;

import instructions.AbstrInstr;
import instructions.IntInstr;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Stack;

import parser.Parser;
import symTable.VarEntry;

public class Interpreter {

	public static int progcnt;
	public static ArrayList<AbstrInstr> progstg;
	public static Stack<AbstrInstr> valuestack;

	public static int curraddr;
	public static ArrayList<AbstrInstr> storage;

	// f�r Testausgabe der Variablenbelegung
	public static ArrayList<String> l = new ArrayList<String>();

	FileInputStream in;
	ObjectInputStream s;

	public Interpreter(String filename) throws IOException,
			ClassNotFoundException {
		in = new FileInputStream(filename);
		s = new ObjectInputStream(in);

		System.out.println("== code f�r Interpretierer wieder einlesen ==");
		progstg = (ArrayList<AbstrInstr>) s.readObject();
		progcnt = 0;

		storage = new ArrayList<AbstrInstr>();
		curraddr = 0;
		valuestack = new Stack<AbstrInstr>();
	};

	public void start() {
		int i, maxstorage;

		// Speicher initialisieren;
		maxstorage = ((IntInstr) (progstg.get(1))).GetI();
		i = 0;
		while (i < maxstorage) {
			Interpreter.storage.add(new IntInstr(0));
			i++;
		}
		;

		// Instruktionen interpretieren
		i = progstg.size();
		progcnt = 2;
		while (progcnt < i) {
			progstg.get(progcnt).Interpret();
		}

		// am Ende Speicherbelegung ausgeben
		System.out.println("Und das ist die Variablenbelegung:");
		i = 0;
		while (i < l.size()) {
			System.out.println(l.get(i)
					+ "("
					+ ((VarEntry) (Parser.env.get(l.get(i)))).GetAddr()
					+ ") = "
					+ ((IntInstr) (storage.get(((VarEntry) (Parser.env.get(l
							.get(i)))).GetAddr()))).GetI());
			i++;
		}
	}
}
