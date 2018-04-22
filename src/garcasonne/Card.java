package garcasonne;

import java.io.File;
import java.io.FileInputStream;

import javafx.scene.image.Image;

public class Card
{
	private int number;
	private int[][] landscape;
	private String imgPath;
	private Image img;
	private int curRotation = 0;

	public Card( int number, int[][] landscape, String imgPath )
	{
		this.number = number;
		this.landscape = landscape;
		this.imgPath = imgPath;
	}
	
	private void loadImage() {
		File file = new File( "res/" + imgPath );
		try( FileInputStream stream = new FileInputStream( file ) ){
			img = new Image( stream );
		}
		catch (Exception e) {
			throw new RuntimeException("Failed to load Image");
		}		
	}
	
	public int[][] getLandscape()
	{
		return landscape;
	}
	
	public void turnRight()
	{
		int[][] turnedLandscape = new int[5][5];
		for( int i = 0; i < landscape.length; i++ )
		{
			for( int j = 0; j < landscape[0].length; j++ )
			{
				turnedLandscape[j][(landscape[0].length-i-1)] = landscape[i][j];
			}
		}
		landscape = turnedLandscape; 
	}
	
	public void turnLeft()
	{
		turnRight();
		turnRight();
		turnRight();
	}
	
	public void calcRotation( int degree ) {
		if( degree > 0 ) {
			if( curRotation + degree < 360 ) {
				curRotation += degree;
			}
			else {
				curRotation = 0;
			}
		}
		else {
			if( curRotation + degree >= 0 ) {
				curRotation += degree;
			}
			else {
				curRotation += 360;
				curRotation += degree;
			}
		}
	}
	
	public int getRotation() {
		return curRotation;
	}
	
	
	public Image getImage() {
		if( img == null ) {
			loadImage();
		}
		return img;
	}
	
	
	
	


}
