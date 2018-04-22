package garcasonne;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application 
{
	@Override
	public void start(Stage stage) throws Exception
	{
		Game game = new Game();
		EHandler.init();
		SceneManager scManager = new SceneManager( stage );
		scManager.init();
	}
	
	public static void main(String[] args)
	{		
		launch(args);
	}
}
