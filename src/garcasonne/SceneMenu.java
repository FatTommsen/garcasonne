package garcasonne;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SceneMenu {
	private Parent parent;
	
	public SceneMenu() {
	}
	
	public Parent getParent( int iWidth, int iHeigth, int iPuffer ) {
		BorderPane mainPane = new BorderPane();
		VBox vBox = new VBox();
		mainPane.setPrefSize( (iWidth + iPuffer)/2, (iHeigth)/2);

		ComboBox<Integer> combo = new ComboBox<>();
		combo.getItems().addAll( 
				1,2,3,4
		);
		combo.getSelectionModel().selectFirst();
		combo.setLayoutX(iWidth/8);
		combo.setLayoutY(iHeigth/8);
		
		Button startgame = new Button("START");
//		startgame.setScaleX(4);
//		startgame.setScaleY(4);
		startgame.setOnAction(e -> EHandler.get().startGame( combo.getValue() ) );
		
		Button exit = new Button("EXIT");
//		exit.setScaleX(4);
//		exit.setScaleY(4);
		exit.setOnAction( e -> EHandler.get().exitGame() );
		//exit.setLayoutX(600);
		
		vBox.getChildren().addAll(combo,startgame,exit);
		mainPane.setCenter( vBox );
		parent = mainPane; 
		return mainPane;
	}

}
