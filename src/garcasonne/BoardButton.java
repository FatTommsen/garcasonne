package garcasonne;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;

public class BoardButton extends CustomButton {
	
	private int xPos;
	private int yPos;
	
	private CustomButton.Type type;

	
	public BoardButton( Type type, Card card, int x, int y, int size) {
		super( card, size );

		this.xPos = x;
		this.yPos = y;
		switch( type ){
			case Dummy:
				toDummy();
				break;
			case Invisible:
				toInvisible();
				break;
			case Card:
				toCard( card );
				break;
		}
	}
	
	public BoardButton( Type t, int x, int y, int size ) {
		this( t, null, x, y, size);
	}
		
	public BoardButton(int x, int y, int size) {
		this(Type.Invisible, null, x, y, size);
	}
	
	public void toCard(Card card ) {
		this.card = card;
		this.type = Type.Card;
		loadImg();
		setVisible(true);
	}
	
	public void toInvisible() {
		this.type = Type.Invisible;
		setVisible(false);
	}
	
	public void toDummy() {
		this.type = Type.Dummy;
		setVisible(true);
		//setOnAction( e -> EHandler.get().setCard(xPos, yPos));
	}
	
	public boolean isInvisible() {
		return !isVisible();
	}
	
	public void resize( int xSize, int ySize ) {
		if( type == Type.Card ) {
			iView.setFitHeight( xSize );
			iView.setFitWidth( ySize );
		}
			TILE_SIZE = xSize;
			setPrefSize(xSize, ySize);
	}
	
	public int getXpos() {
		return xPos;
	}
	
	public int getYpos() {
		return yPos;
	}
	
	public boolean isDummy() {
		return type == Type.Dummy;
	}
}
