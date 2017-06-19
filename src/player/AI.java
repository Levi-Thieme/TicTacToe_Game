package player;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import views.JTile;
import views.JTilesPane;

public class AI extends Player {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4232291851905055087L;
	
	JTile board[][];
	static JTilesPane pane;
	

	public AI(JTile[][] tiles, JTilesPane pane) {
		super();
		board = tiles;
		this.pane = pane;
	}
	
	public static void select(JTile tile){
		tile.setBtnImg(pane.getXPath());
		tile.repaint();
		tile.setSelected(true);
		tile.setPlayerTile(false);
		
	}
	
	/*
	 * This method makes a move for the AI
	 * Its selection is based on the tile the user chooses, and whether or not
	 * the tiles adjacent to that tile are available for selection
	 */
	public void makeMove(int x, int y){
		// loop through adjacent tiles and select an available one
		
		ArrayList<JTile> adjacents = board[x][y].getAdjacents();
		
		for(int i = 0; i < adjacents.size(); i++){
			JTile tile = adjacents.get(i);
			if(!tile.getSelected()){
				tile.setBtnImg(pane.getXPath());
				tile.repaint();
				tile.setSelected(true);
				tile.setPlayerTile(false);
				return;
			}	
		}
		
		/* If no adjacent tiles are available select 
		 * an adjacent tile to one of the adjacent tiles */
		for(int i = 0; i < adjacents.size(); i++){
			adjacents = adjacents.get(i).getAdjacents();
			
			for(int j = 0; i < adjacents.size(); i++){
				JTile tile = adjacents.get(i);
				if(!tile.getSelected()){
					tile.setBtnImg(pane.getXPath());
					tile.repaint();
					tile.setSelected(true);
					tile.setPlayerTile(false);
					return;
				}	
			}
		}
			
		/*
		 * If no adjacent tiles or adjacent tiles of the adjacent tiles
		 * are available to select, then select a free tile.
		 */
		for(int row = 0; row < board.length; row++)
			for(int col = 0; col < board[row].length; col++){
				if(!board[row][col].getSelected()){
					JTile tile = board[row][col];
					tile.setBtnImg(pane.getXPath());
					tile.repaint();
					tile.setSelected(true);
					tile.setPlayerTile(false);
					return;
				}		
			}		
	}
	
	

	@Override
	public void addWin() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addLoss() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addTie() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void clearStats() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void showStats() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void saveStats() throws IOException {
		// TODO Auto-generated method stub
		
	}
}
