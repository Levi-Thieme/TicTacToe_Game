package views;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import player.*;

/*
 * This class is the controller for the tic tac toe game.
 * It handles action events and updates the game accordingly.
 */
public class TicTacToeController {
	private UserSelection selector;
	private TicTacToeFrame gameView;
	private Player loadedUser;
	private AI AI;
	private JTilesPane tilesPane;
	private boolean userLoaded = false;
	private JTile[][] tiles;
	private boolean gameStarted = false;
	
	/*
	 * This constructor creates the user selection JFrame,
	 * the Tic Tac Toe game's JFrame, and an AI object. It adds ActionListeners to 
	 * all the buttons on both JFrames. 
	 */
	public TicTacToeController() throws IOException, InterruptedException{
	
		selector = new UserSelection(this);
		
		selector.getLoadBtn().addActionListener(new SelectorListener());
		selector.getCreateBtn().addActionListener(new SelectorListener());
		selector.getDeleteBtn().addActionListener(new SelectorListener());
		
		
		selector.setVisible(true);
		
		gameView = new TicTacToeFrame();

		gameView.getStatistics().addActionListener(new MenuListener());
		gameView.getNewGame().addActionListener(new MenuListener());
		gameView.getSaveAndExit().addActionListener(new MenuListener());
		gameView.getMntmTheme1().addActionListener(new MenuListener());
		gameView.getMntmTheme2().addActionListener(new MenuListener());
		gameView.getClearStats().addActionListener(new MenuListener());
		
		tilesPane = gameView.getTilesPane();
		tilesPane.setPath();
		tilesPane.addAdjacents();
		
		tiles = gameView.getTilesPane().getTiles();
		
		for(int row = 0; row < tiles.length; row++){
			for(int col = 0; col < tiles[row].length; col++){
				tiles[row][col].addMouseListener(new TileListener());
			}
		}
		
		AI = new AI(tiles, tilesPane);
		
		while(!userLoaded){
			gameView.setVisible(false);
		}
		
		gameView.setVisible(true);
	}
	
	/*
	 * This class uses a mouseListener to change the icon of a tile when it
	 * is clicked. After a tile has been selected by the user, the mouseClicked
	 * method checks for a winning board state. If a winning state does not exist,
	 * then the AI makes a move and the board is checked for a winnning state.
	 */
	private class TileListener implements MouseListener{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			gameStarted = true;
			selector.setVisible(false);
			
			JTile button = (JTile) e.getComponent();
			int x = button.getCol();
			int y = button.getRow();
			
			if(!(button).getSelected()){
				button.setBtnImg(tilesPane.getOPath());
				button.repaint();
				button.setSelected(true);
				button.setPlayerTile(true);
				
				if(checkBoardState()){
					JOptionPane.showMessageDialog(null, "You Win!");
					loadedUser.addWin();
					newGame();
				}
				else if(checkTie()){
					JOptionPane.showMessageDialog(null, "You tied!");
					loadedUser.addTie();
					newGame();
				}
				else{
					AI.makeMove(x, y);
					if(checkAIBoardState()){
						JOptionPane.showMessageDialog(null, "You Lose!");
						loadedUser.addLoss();
						newGame();
					}
					else if(checkTie()){
						JOptionPane.showMessageDialog(null, "You tied!");
						loadedUser.addTie();
						newGame();
					}
				}
			}
			else{
				System.out.println("The button has already been chosen.");
			}
		}
		
		/*
		 * Checks the board for a winning state of the user or a tie	
		 */
		public boolean checkBoardState(){
			if(checkHorizontal() == 0)
				return true;
			if(checkVertical() == 0)
				return true;
			if(checkDiagonal() == 0)
				return true;
					
			return false;
		}
		
		/*
		 * Checks the board for a winning state of the AI or a tie
		 */
		public boolean checkAIBoardState(){
			if(checkHorizontal() == 1)
				return true;
			if(checkVertical() == 1)
				return true;
			if(checkDiagonal() == 1)
				return true;
					
			return false;
			
		}
		
		/*
		 * Checks for Horizontal win condition
		 * Returns true if win condition exists, else return false
		 */
		public int checkHorizontal(){
			
			for(int row = 0; row < tiles.length; row++){
					if(tiles[row][0].getSelected() && tiles[row][0].getPlayerTile()
						&& tiles[row][1].getSelected() && tiles[row][1].getPlayerTile() 
						&& tiles[row][2].getSelected() && tiles[row][2].getPlayerTile()){
						return 0;
					}	
					else if(tiles[row][0].getSelected() && !tiles[row][0].getPlayerTile()
							&& tiles[row][1].getSelected() && !tiles[row][1].getPlayerTile() 
							&& tiles[row][2].getSelected() && !tiles[row][2].getPlayerTile()){
							return 1;
						}	
						
			}
			return 2;
		}
		
		/*
		 * Checks for Vertical win Condition
		 * Returns true if win condition exists, else returns false
		 */
		public int checkVertical(){
			
			for(int col = 0; col < tiles.length; col++){
					if(tiles[0][col].getSelected() && tiles[0][col].getPlayerTile()
						&& tiles[1][col].getSelected() && tiles[1][col].getPlayerTile() 
						&& tiles[2][col].getSelected() && tiles[2][col].getPlayerTile()){
						return 0;
					}
					else if (tiles[0][col].getSelected() && !tiles[0][col].getPlayerTile()
							&& tiles[1][col].getSelected() && !tiles[1][col].getPlayerTile() 
							&& tiles[2][col].getSelected() && !tiles[2][col].getPlayerTile())
							return 1;
			}
			return 2;
		}
		
		/*
		 * Checks for a diagonal win condition
		 * Returns true if it exists, else returns false
		 */
		public int checkDiagonal(){
			if(tiles[0][0].getSelected() && tiles[0][0].getPlayerTile()
					&& tiles[1][1].getSelected() && tiles[1][1].getPlayerTile() 
					&& tiles[2][2].getSelected() && tiles[2][2].getPlayerTile()){
					return 0;
				}
			if(tiles[0][2].getSelected() && tiles[0][2].getPlayerTile()
					&& tiles[1][1].getSelected() && tiles[1][1].getPlayerTile() 
					&& tiles[2][0].getSelected() && tiles[2][0].getPlayerTile()){
					return 0;
				}
			if(tiles[0][0].getSelected() && !tiles[0][0].getPlayerTile()
					&& tiles[1][1].getSelected() && !tiles[1][1].getPlayerTile() 
					&& tiles[2][2].getSelected() && !tiles[2][2].getPlayerTile()){
					return 1;
				}
			if(tiles[0][2].getSelected() && !tiles[0][2].getPlayerTile()
					&& tiles[1][1].getSelected() && !tiles[1][1].getPlayerTile() 
					&& tiles[2][0].getSelected() && !tiles[2][0].getPlayerTile()){
					return 1;
				}
			
			return 2;
		}
	
		public boolean checkTie(){
			for(int row = 0; row < tiles.length; row++){
				for(int col = 0; col < tiles[row].length; col++){
					if(!tiles[row][col].getSelected())
						return false;
				}
			}
			return true;
		}
		
		//Increases the width of the border when the mouse enters the button
		@Override
		public void mouseEntered(MouseEvent e) {
			JButton button = (JButton)e.getComponent();
			button.setBorder(new LineBorder(Color.BLACK, 6));
			
		}
		//Sets the border width of the button back to the default value when the mouse exits the button
		@Override
		public void mouseExited(MouseEvent e) {
			JButton button = (JButton)e.getComponent();
			button.setBorder(new LineBorder(Color.black, 3));	
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	/*
	 * This class is a listener for the UserSelection JFframe
	 * It allows the user to load, delete, and create usernames.
	 * A user cannot be deleted if it is loaded.
	 */
	private class SelectorListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == selector.getLoadBtn()){	
				loadedUser = selector.loadUser();
				userLoaded = true;
			}
			
			if(e.getSource() == selector.getDeleteBtn()){
				try {
					selector.deleteUser(loadedUser);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(e.getSource() == selector.getCreateBtn()){
				selector.addUser();
				userLoaded = true;
			}
		}
	}
	
	/*
	 * This class is a listener for the menu items.
	 * It enables the user to see statistics, start a new game,
	 * clear stats, save and exit, and change the theme.
	 */
	private class MenuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == gameView.getStatistics()){
				loadedUser.showStats();
			}
			if(e.getSource() == gameView.getNewGame()){
				newGame();
			}
			if(e.getSource() == gameView.getSaveAndExit()){
				
				selector.saveUsers();
				
				System.exit(0);
			}
				
			
			if(e.getSource() == gameView.getMntmTheme1()){
				if(gameStarted)
						JOptionPane.showMessageDialog(null, "You cannot change the theme after a game has been started.");
				else{
				tilesPane.setPath();
				gameView.setFrameBG(Color.black);
				}
			}
			if(e.getSource() == gameView.getMntmTheme2()){
				if(gameStarted)
					JOptionPane.showMessageDialog(null, "You cannot change the theme after a game has been started.");
				else{
				tilesPane.setPath2();
				gameView.setFrameBG(Color.DARK_GRAY);
				}
			}
			
			
			if(e.getSource() == gameView.getClearStats()){
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to clear your stats?", null, JOptionPane.YES_NO_OPTION);
				
				if(response == JOptionPane.YES_OPTION){
					
					loadedUser.clearStats();
				
					JOptionPane.showMessageDialog(null, "Stats Cleared");
				}
			}
		}
	}
	
	
	/*
	 * This method clears the board of all X's and O's, 
	 * updates the selectors list and model,
	 * and makes the load menu visible.
	 */
	public void newGame(){
		gameStarted = false;
		tilesPane.clearBoard();
		//selector.loadList();
		selector.setVisible(true);
	}
	
	public void setLoadedUser(Player p){
		loadedUser = p;
	}
	
		
}
