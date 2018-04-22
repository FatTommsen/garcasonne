package garcasonne;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CardStack 
{
	//private int m_iTileSize;
	private int iTotal;
	
	private ArrayList<Integer> cardAmount = new ArrayList<>();
	private ArrayList<int[][]>templates = new ArrayList<>();
	private ArrayList<String>imgs = new ArrayList<>();


	public CardStack(  ){
	}
	

	public void init(){
		File cardFile = new File("res/Cards.txt");
		try (Scanner sc = new Scanner(new FileReader(cardFile))){
			String strLine = null;
			while (sc.hasNext() ) {
				strLine = sc.nextLine();
				parseLine( strLine );			
			}
		}
		catch (Exception e) {}	
	}

	private void parseLine(String strLine) {
		try {
			String[] strLineSplit = strLine.split(";");
			int iRow = Integer.parseInt( strLineSplit[0] );
			//control row number
			if( iRow != templates.size() ) {
				throw new IllegalArgumentException("Invalid card file.");
			}
			//save amount of this card type
			int iAmount = Integer.parseInt(strLineSplit[1]);
			cardAmount.add( iAmount );
			iTotal += iAmount;
			//parse and save landscape
			templates.add( new int[5][5]);
			for( int y = 2; y < strLineSplit.length - 1; y++ ) {
				char[] cArLandLine = strLineSplit[y].toCharArray();
				for (int i = 0; i < 5; i++) {
					switch (cArLandLine[i]) {
					//Town
					case 't':
						templates.get(iRow)[y-2][i]= 1;
						break;
					//Lawn
					case 'l':
						templates.get(iRow)[y-2][i]= 2;
						break;
					//Road
					case 'r':
						templates.get(iRow)[y-2][i]= 3;
						break;
					//Cloister
					case 'c':
						templates.get(iRow)[y-2][i]= 4;
						break;
					//BREAKER	
					case 'x':
						templates.get(iRow)[y-2][i]= 5;
						break;
					}
				}
			}
			imgs.add( strLineSplit[strLineSplit.length-1]);
		}
		catch( Exception e) {
			// ExceptionHandling?
		}
			
	}
	
	
	public int getICardAmountTotal()
	{
		return iTotal;
	}
	
	//Returns a random card according to the specific amount.
	public Card giveRandomCard() {
		Card card = null;
		int rndNum = (int) (Math.random() * (iTotal-1)) + 1;
		int iCardNumber = 0;
		for( int i = 0; i < cardAmount.size(); i++ ) {
			if( iCardNumber + cardAmount.get(i) < rndNum ) {
				iCardNumber += cardAmount.get(i);
			}
			else {
				cardAmount.set(i, cardAmount.get(i) - 1);
				iTotal -= 1;
				card = new Card(i, templates.get(i), imgs.get(i) );
				break;
			}
		}
		return card;
	}
	
	public Card getRootCard() {
		Card card = new Card( 0, templates.get(0), imgs.get(0));
		return card;
	}

}