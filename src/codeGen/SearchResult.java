package codeGen;

import symTable.VarEntry;

public class SearchResult {
	int level;
	VarEntry e;

	public SearchResult(int fl, VarEntry fe) {
		level = fl;
		e = fe;
	}

	public int GetLevel() {
		return level;
	}

	public VarEntry GetE() {
		return e;
	}
}
