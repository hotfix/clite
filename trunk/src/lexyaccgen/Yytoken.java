package lexyaccgen;

public class Yytoken {

	private int		m_token;
	private String	m_lexem;
	private int		m_length;
	
	
	public Yytoken(int token, String lexem) {
			
		m_token = token;
		m_lexem = new String(lexem);
		m_length = lexem.length();
	}
	
	public int getTokenType() {		
		return m_token;
	}
	
	public String getLexem() {		
		return m_lexem;
	}
	
	public int length() {
		return m_length;
	}
	
	@Override
	public String toString() {		
		return (new String(m_token + ": " + m_lexem));
	}

}
