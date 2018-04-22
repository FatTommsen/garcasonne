package garcasonne;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class SceneSettings {
	private Parent parent;
	
	public SceneSettings() {
	}
	
	public Parent getParent( int iWidth, int iHeigth, int iPuffer ) {
		BorderPane mainPane = new BorderPane();
		mainPane.setPrefSize( (iWidth + iPuffer)/2, (iHeigth)/2);
		Button back = new Button("Zurück");
		back.setOnAction(e -> EHandler.get().backToMenu() );
		Button move = new Button("Move");
		move.setOnAction(e -> EHandler.get().backToMenu() );
		mainPane.setBottom(back);
		mainPane.setRight(move);
		parent = mainPane;
		return mainPane;
	}

}
