package codeGen;

import symTable.AbstractEntry;

public class SearchResult {
	int level;
	AbstractEntry e;

	public SearchResult(int fl, AbstractEntry fe) {
		level = fl;
		e = fe;
	}

	public int GetLevel() {
		return level;
	}

	public AbstractEntry GetE() {
		return e;
	}
}
