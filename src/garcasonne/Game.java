package garcasonne;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Game 
{
	private final int iWidth = 1200;
	private final int iHeigth = 900;
	private final int iPuffer = 200;
	
	private static final int tileSize = 10;
	
	private static final int iXCards = 120;
	private static final int iYCards = 90;
	
	
	private ArrayList<Player> players = new ArrayList<>();
	

	public void initPlayers( int iAmount ) {
		for( int i = 0; i < iAmount; i++ ) {
			String strName = JOptionPane.showInputDialog("Player " + (i + 1) + " , please enter your Name:");
			players.add( new Player( strName ) );
		}		
	}
	

	
	

	

	

}
