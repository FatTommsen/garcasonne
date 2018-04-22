package garcasonne;



import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;


public class StackButton extends CustomButton{
	private static final DataFormat customFormat = new DataFormat("helloword.custom");
	
	double xPos;
	double yPos;
	boolean bDrop = false;
	
	public StackButton( Card card) {
		super(card, 100);
		loadImg();
		setEHandler();
	}
	
	private void setEHandler() {
		// to turn button
		this.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle( KeyEvent event ) {
				String input = event.getText();				
				if( input.equals( "t") || input.equals("T") ) {
					turnRight();
					event.consume();
				}
			}
		});

		// to move button
		final StackButton tmp = this;
		this.setOnMouseEntered( new EventHandler<MouseEvent>() {
			@Override
			public void handle( MouseEvent event ) {
				tmp.setCursor( Cursor.HAND);
			}
		});
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tmp.setCursor(Cursor.CLOSED_HAND);
                xPos = mouseEvent.getSceneX();
                yPos = mouseEvent.getSceneY();
            }
        });
        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
            public void handle(MouseEvent event) {
				if( EHandler.get().test(event.getSceneX(), event.getSceneY(), card )) {
					tmp.setVisible(false);
				}
                tmp.setCursor(Cursor.HAND);
                tmp.setTranslateX(0);
                tmp.setTranslateY(0);
            }
        });
        this.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	//event.setDragDetect(false);
            	tmp.setTranslateX(event.getSceneX() - xPos);
                tmp.setTranslateY(event.getSceneY() - yPos);
            }
        });
        
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tmp.setCursor(Cursor.HAND);
            }
        });
        
   /*    this.setOnDragDetected( new EventHandler<MouseEvent>() {
        	@Override
        	public void handle(MouseEvent event) {
        		Dragboard db = tmp.startDragAndDrop(TransferMode.ANY);
        		ClipboardContent content = new ClipboardContent();
        		content.putString("test");
        		db.setContent(content);
        		EHandler.get().setClipboard( card );
        		event.consume();
        	}
        });*/

              
        this.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
            	
                if (event.getTransferMode() == TransferMode.MOVE) {
                    tmp.setVisible(false);
                }
                else {
                    tmp.setCursor(Cursor.HAND);
                    tmp.setTranslateX(0);
                    tmp.setTranslateY(0);
                }
                event.consume();
            }
        });
		
	}
	
	public void turnRight() {
		card.calcRotation(90);
		iView.setRotate( card.getRotation() );
		card.turnRight();
	}
}
