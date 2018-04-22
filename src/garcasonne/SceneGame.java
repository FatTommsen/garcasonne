package garcasonne;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class SceneGame {
	private Parent parent;
	private PaneBoard board;
	
	private int fieldMaxSize = 200;
	
	public SceneGame( ) {
	}
	
	public Parent getParent( int iWidth, int iHeigth, int iPuffer ) {
		BorderPane mainPane = new BorderPane();
		mainPane.setPrefSize( (iWidth + iPuffer)/2, (iHeigth)/2);
		Button back = new Button("Zurück");
		back.setOnAction(e -> EHandler.get().backToMenu() );
		Button move = new Button("Move");
		move.setOnAction(e -> EHandler.get().makeMove() );
		mainPane.setBottom(back);
		mainPane.setRight(move);
		parent = mainPane;
		board = new PaneBoard( fieldMaxSize );
		mainPane.setCenter(board.getParent());
		return mainPane;
	}
	
	public void addCard( Card c ) {
		StackButton card = new StackButton( c );
		((BorderPane)parent).setRight(card);
	}
	
	public PaneBoard getBoard() {
		return board;
	}
}
