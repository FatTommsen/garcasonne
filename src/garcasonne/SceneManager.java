package garcasonne;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
	private final int iWidth = 1200;
	private final int iHeigth = 900;
	private final int iPuffer = 200;

	public enum Scenes{
		MENU,
		SETTINGS,
		BOARD
	}
	
	private Stage stage;
	
	private SceneMenu scMenu;
	private SceneGame scGame;
	private SceneSettings scSettings;
	
	//Scenes:	
	private Scene Menu;
	private Scene Settings;
	private Scene Board;
	
	
	public SceneManager( Stage stage ) {
		this.stage = stage;
		EHandler.get().registerScManger(this);
	}
	
	public void init() {
		scMenu = new SceneMenu();
		Menu = new Scene( scMenu.getParent(iWidth, iHeigth, iPuffer));
		scGame = new SceneGame();
		Board = new Scene( scGame.getParent(iWidth, iHeigth, iPuffer));
		scSettings = new SceneSettings();
		Settings = new Scene( scSettings.getParent(iWidth, iHeigth, iPuffer));
		stage.setTitle( "Carcasonne" );
		switchTo( Scenes.MENU );
	}
	
	public void switchTo ( Scenes sc ) {
		Scene scene = null;
		switch( sc ){
			case MENU:
				scene = Menu;
				break;
			case SETTINGS:
				scene = Settings;
				break;
			case BOARD:
				scene = Board;
				break;			
		}
		stage.setScene(scene);
		stage.show();
	}
	
	public void refresh() {
		stage.show();
	}
	
	public SceneMenu getMenu() {
		return scMenu;
	}
	
	public SceneSettings getSettings() {
		return scSettings;
	}

	public SceneGame getGame() {
		return scGame;
	}


	
	
	


}
