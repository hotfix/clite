//import My


public class CToken {

	private int		m_tType;
	private String	m_lexem;	
	
	public CToken(int tType, String lexem) {
			
		m_tType = tType;
		m_lexem = lexem;
	}
	
	public int getTokenType() {
		
		return m_tType; 
	}
	
	public String getLexem() {
		
		return m_lexem;
	}

}
