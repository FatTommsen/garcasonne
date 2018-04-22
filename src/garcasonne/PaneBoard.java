package garcasonne;

import garcasonne.CustomButton.Type;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;


public class PaneBoard {
	final double SCALE_DELTA = 1.1;
	final private int iOffset;
	final private int MIN_TILE_SIZE = 40;
	final private int MAX_TILE_SIZE = 500;
	int curTileSize = 100;
	
	
	private ScrollPane scPane = new ScrollPane();
	private GridPane board = new GridPane();
	private Group group = new Group();
	private BoardButton root;
	
	private BoardButton[][] buttons;
	
	public PaneBoard( int fieldMaxSize ) {
		iOffset = fieldMaxSize/2;
		buttons = new BoardButton[iOffset*2][iOffset*2];
		//group.getChildren().add(board);
		// Hier dann die wirkliche Anfangskarte reinpacken.
		initScPane();
	}
	
	public Parent getParent() {
		return scPane;
	}
	
	
	public void setCard( int externX, int externY, Card c ) {
		int internX = iOffset + externX;
		int internY = iOffset + externY;
		buildPath( internX, internY );
		getB( internX, internY ).toCard(c);
		setSurrounding( internX, internY);
	}
	
	private void setSurrounding( int x, int y ) {
		if( getB( x, y+1 ).isInvisible() ) {
			getB( x, y+1 ).toDummy();
		}
		if( getB( x, y-1 ).isInvisible() ) {
			getB( x, y-1 ).toDummy();
		}
		if( getB( x+1, y ).isInvisible() ) {
			getB( x+1, y ).toDummy();
		}
		if( getB( x-1, y ).isInvisible() ) {
			getB( x-1, y ).toDummy();
		}
	}
	
	private void buildPath( int x, int y ) {
		buildPathImpl( x, y+1 );
		buildPathImpl( x, y-1 );
		buildPathImpl( x-1, y );
		buildPathImpl( x+1, y );
		buildPathImpl( x, y );
	}
	
	private void buildPathImpl( int x, int y ) {
		int xStart = iOffset < x ? iOffset : x;
		int xEnd = iOffset < x ? x : iOffset;
		int yStart = iOffset < y ? iOffset : y;
		int yEnd = iOffset < y ? y : iOffset;

		for( int i = xStart; i <= xEnd; i++ ) {
			if( getB( i, iOffset ) == null ) {
				BoardButton b = new BoardButton( Type.Invisible, i - iOffset, 0, curTileSize );
				group.getChildren().add(b);
				buttons[i][iOffset] = b;
				board.add(b, i, iOffset);
			}
		}
		for( int j = yStart; j <= yEnd; j++ ) {
			if( getB( x, j ) == null ) {
				BoardButton b = new BoardButton( Type.Invisible, x-iOffset, j - iOffset, curTileSize );
				group.getChildren().add(b);
				buttons[x][j] = b;
				board.add(b, x, 2*iOffset-j);
			}
		}
	}
	
	public BoardButton getB( int x, int y ) {
		if( x < 0 || x >= iOffset*2 || y < 0 || y >= iOffset*2 ) {
			throw new PaneBoardException("Invalid intern coordinates.");
		}
		return buttons[x][y];
	}
	
	public void setRoot( Card c ) {
		setCard( 0, 0, c );
		root = getB( iOffset,iOffset);
	}

	
	public void initScPane() {
		scPane.setContent(board);
		scPane.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>(){
			@Override
			public void handle(ScrollEvent event) {
				event.consume();
			   
				if (event.getDeltaY() == 0) {
				      return;
				    }

				    double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1/SCALE_DELTA;
				    if( curTileSize * scaleFactor > MAX_TILE_SIZE ) {
				    	if( curTileSize == MAX_TILE_SIZE ) {
				    		return;
				    	}
				    	else {
				    		curTileSize = MAX_TILE_SIZE;
				    	}
				    }
				    else if( curTileSize * scaleFactor < MIN_TILE_SIZE ) {
				    	if( curTileSize == MIN_TILE_SIZE ) {
			    			return;
			    		}
			    		else {
			    			curTileSize = MIN_TILE_SIZE;
			    		}
				    }
				    else {
				    	curTileSize *= scaleFactor;
				    }
				    
				    for( BoardButton[] rows : buttons ) {
				    	for( BoardButton b : rows ) {
				    		if( b != null ) {
				    			b.resize(curTileSize, curTileSize);
				    		}
				    	}
				    }
			}
		});
	}
	
	public boolean dragAndDropCard( double sceneX, double sceneY, Card card ) {
		Node node = (Node)board;
		Point2D p = node.sceneToLocal(sceneX, sceneY, true);
		
		if( !node.contains(p)) {
			return false;
		}
		BoardButton picked = null;		
		for( BoardButton[] rows : buttons ) {
			for( BoardButton b : rows ) {
				if( b != null ) {
					Node tmp = (Node)b;
					p = tmp.sceneToLocal( sceneX, sceneY, true);
					if (tmp.isVisible() && !tmp.isMouseTransparent() && tmp.contains(p) && b.isDummy() ) {
		                picked = b;
		                break;
		            }
				}
			}
		}
		if( picked == null ) {
			return false;
		}
		else {
			// Logik ob die Karten nebeneinander passen.
			setCard( picked.getXpos(), picked.getYpos(), card );
			return true;
		}		
	}

	

}