package symTable;

import java.io.Serializable;

public class SymTableEntry implements Serializable {
	
	public String 	lexem;
	public int		token;
	public int 		typ;
	public String	wert;
	
	public String toString() {
		
		return (lexem + " " + token + " " + typ + " " + wert);
	}
	
	public SymTableEntry(String lexem, int token, int typ, String wert) {
		this.lexem 	= new String(lexem);
		this.token 	= token;
		this.typ 	= typ;
		this.wert 	= new String(wert);
	}
}
