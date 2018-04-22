package garcasonne;

import garcasonne.SceneManager.Scenes;
import javafx.application.Platform;

public class EHandler {
	
	private static EHandler e;
	
	private SceneManager scManager;
	private Game game;
	private CardStack caStack;
	private Card clipboard;	
	
	private EHandler() {
		EHandler.e = this;
	}
	
	public static EHandler get() {
		return EHandler.e;
	}
	
	public static void init() {
		if( EHandler.e == null ) {
			EHandler.e = new EHandler();
		}
	}
	
	public void registerGame( Game game) {
		this.game = game;
	}
	
	public void registerScManger( SceneManager scManager ) {
		this.scManager = scManager;
	}

	
	
	
	public void exitGame() {
		Platform.exit();
	}
	
	public void startGame( int playerAmount ) {
		caStack = new CardStack();
		caStack.init();
		scManager.getGame().getBoard().setRoot( caStack.getRootCard() );
		// Other initializations
		//game.initPlayers( playerAmount );

		
		scManager.switchTo( Scenes.BOARD );
		
	}
	
	public void backToMenu() {
		scManager.switchTo( Scenes.MENU );
	}
	
	public void makeMove() {
		Card c = caStack.giveRandomCard();
		scManager.getGame().addCard(c);
	}
	
	public void setCard( int x, int y ) {
		scManager.getGame().getBoard().setCard(x, y, null);
	}
	
	public void setClipboard( Card card ) {
		this.clipboard = card;
	}
	
	public void setClipboardOnBoard( int x, int y) {
		scManager.getGame().getBoard().setCard(x, y, clipboard);
	}
	
	public boolean test(double sceneX, double sceneY, Card card ) {
		return scManager.getGame().getBoard().dragAndDropCard(sceneX, sceneY, card);
	}
	
	
	
	
}
















