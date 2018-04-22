package garcasonne;

public class Player {
	
	private int m_iPoints;
	private int m_iNumber;
	private String m_strName;
	private int m_iPawn;
	
	public Player( String strName )	{
		this.m_strName = strName;
		// Hier könnte Name abgefragt werden und ähnliches.
	}
	
	void addPoints( int iPoints )
	{
		m_iPoints += iPoints;
	}
	
	void setName( String _strName )
	{
		m_strName = _strName;
	}
	
}	
	
