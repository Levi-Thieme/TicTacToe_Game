package views;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/*
 * This class is a JPanel which hold 9 JTiles in a 3x3 configuration.
 */
public class JTilesPane extends JPanel{
	private JTile[][] tiles = new JTile[3][3];
	private String XImagePath;
	private String OImagePath;
	
	public JTilesPane(){
		
		setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(30, 144, 255)));
		setPreferredSize(new Dimension(600, 600));
		setLayout(new GridLayout(3, 3, 3, 3));
		
		
		for(int row = 0; row < tiles.length; row ++)
			for(int col = 0; col < tiles[row].length; col++){
				tiles[row][col] = new JTile();
				tiles[row][col].setSelected(false);
				add(tiles[row][col]);
				tiles[row][col].setRow(row);
				tiles[row][col].setCol(col);
			}
	}
	
	//Clears the board of all X's and O's
	public void clearBoard(){
		for(int row = 0; row < tiles.length; row ++)
			for(int col = 0; col < tiles[row].length; col++){
				tiles[row][col].setIcon(null);
				tiles[row][col].repaint();
				tiles[row][col].setSelected(false);
			}
	}
	
	/*
	 * Sets the adjacent tiles field for all tiles
	 */
	public void addAdjacents(){
		tiles[0][0].addAdjacents(tiles[1][0]);
		tiles[0][0].addAdjacents(tiles[2][0]);
		
		tiles[1][0].addAdjacents(tiles[1][1]);
		tiles[1][0].addAdjacents(tiles[0][2]);
		tiles[1][0].addAdjacents(tiles[0][0]);
		
		tiles[2][0].addAdjacents(tiles[1][0]);
		tiles[2][0].addAdjacents(tiles[1][1]);
		tiles[2][0].addAdjacents(tiles[2][1]);
		
		tiles[0][1].addAdjacents(tiles[0][0]);
		tiles[0][1].addAdjacents(tiles[1][1]);
		tiles[0][1].addAdjacents(tiles[0][2]);
		
		tiles[1][1].addAdjacents(tiles[0][1]);
		tiles[1][1].addAdjacents(tiles[1][0]);
		tiles[1][1].addAdjacents(tiles[2][1]);
		tiles[1][1].addAdjacents(tiles[1][2]);
		
		tiles[2][1].addAdjacents(tiles[2][0]);
		tiles[2][1].addAdjacents(tiles[2][2]);
		tiles[2][1].addAdjacents(tiles[1][1]);
		
		tiles[0][2].addAdjacents(tiles[0][1]);
		tiles[0][2].addAdjacents(tiles[1][2]);
		tiles[0][2].addAdjacents(tiles[1][1]);
		
		tiles[1][2].addAdjacents(tiles[1][1]);
		tiles[1][2].addAdjacents(tiles[1][1]);
		tiles[1][2].addAdjacents(tiles[2][2]);
		
		tiles[2][2].addAdjacents(tiles[1][2]);
		tiles[2][2].addAdjacents(tiles[2][1]);
		tiles[2][2].addAdjacents(tiles[1][1]);
	}
	
	
	//Returns a reference to the tiles array
	public JTile[][] getTiles(){
		return tiles;
	}
	//Sets the ImagePath of X and O to the first theme
	public void setPath(){
		XImagePath = "/icons/iconX.png";
		OImagePath = "/icons/iconO.png";
	}
	//Sets the ImagePath of X and O to the second theme
	public void setPath2(){
		XImagePath = "/icons/icon2X.png";
		OImagePath = "/icons/icon2O.jpg";
	}
	//Returns a reference to the X Image's path
	public String getXPath(){
		return XImagePath;
	}
	
	//Returns a refernce to the O Image's path
	public String getOPath(){
		return OImagePath;
	}
}
