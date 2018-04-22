package garcasonne;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public abstract class CustomButton extends Button {
	final static protected int TILE_SIZE_DEFAULT = 100;
	protected int TILE_SIZE;
	protected Card card;
	protected ImageView iView;
	
	public enum Type{
		Dummy,
		Invisible,
		Card
	};
	
	public CustomButton( Card card, int tile_size ) {
		this.card = card;
		this.TILE_SIZE = tile_size;
		setPrefSize(TILE_SIZE,TILE_SIZE);
		setPadding(Insets.EMPTY);
	}
	
	public CustomButton( Card card ) {
		this( card, TILE_SIZE_DEFAULT );
	}
	
	public void loadImg() {
		iView = new ImageView( card.getImage() );
		iView.setFitHeight( TILE_SIZE );
		iView.setFitWidth( TILE_SIZE );
		setGraphic(iView);
		iView.setRotate(card.getRotation());
	}
}
