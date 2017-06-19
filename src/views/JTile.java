package views;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/*
 * This class is a custom JButton. It contains methods for changing the icon of the button when it's clicked.
 */
public class JTile extends JButton {
	private boolean selected;
	private boolean playerTile;
	private int x;
	private int y;
	private static String X = "/icons/icon2X.png";
	private ArrayList<JTile> adjacents = new ArrayList<>();
	
	
	public JTile(){
		setBorder(new LineBorder(Color.BLACK, 3));
		setVisible(true);
		
	}
	
	//Adds a tile to the adjacent tiles Array
	public void addAdjacents(JTile tile){
		adjacents.add(tile);
	}
	
	public ArrayList getAdjacents(){
		return adjacents;
	}
	
	/*Sets the button's icon to the specified Image*/
	public void setBtnImg(String imgPath){
		ImageIcon imageIcon = new ImageIcon(TicTacToeFrame.class.getResource(imgPath)); 
		Image image = imageIcon.getImage();
		if(imgPath.equals("/icons/iconX.png") || imgPath.equals("/icons/icon2X.png"))
			image = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); 
		else if(imgPath.equals("/icons/iconO.png"))
			image = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); 
		else if(imgPath.equals("/icons/icon2O.jpg"))
			image = image.getScaledInstance(120, 110,  java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(image);
		setIcon(imageIcon);
	}
	
	public boolean getSelected(){
		return selected;
	}
	public void setSelected(boolean b){
		selected = b;
	}
	public void setPlayerTile(boolean b){
		playerTile = b;
	}
	public boolean getPlayerTile(){
		return playerTile;
	}
	public void setRow(int x){
		this.x = x;
	}
	public void setCol(int y){
		this.y = y;
	}
	public int getRow(){
		return x;
	}
	public int getCol(){
		return y;
	}

	
}
