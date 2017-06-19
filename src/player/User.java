package player;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import views.UserSelection;

/*
 * This class represents a human user. It extends the Player Class.
 * It contains methods for loading a user, creating a new user, and 
 * updating the statistics of a user.
 */
public class User extends Player implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3946354134586018832L;


	public User(String username){
		super();
		
		setName(username);
		setWins(0);
		setLosses(0);
		setTies(0);
	}
	
	
	/*
	 * This method displays the user's wins, losses, and ties in a JOptionPane
	 */
	@Override
	public void showStats(){
		JOptionPane.showMessageDialog(null, "User: " + getName() + "\nWins: "
				+ getWins() + "\nLosses: " + getLosses() + "\nTies: "
				+ getTies());
	}
	
	//Adds a win to the win count of a user
	@Override
	public void addWin() {
		setWins(getWins() + 1);
	}
	//Adds a loss to the loss count of a user
	@Override
	public void addLoss() {
		setLosses(getLosses() + 1);
	}
	//Adds a tie to the tie count of a user
	@Override
	public void addTie() {
		setTies(getTies() + 1);
	}
	@Override
	//Clears a user's statistics and sets them to 0
	public void clearStats() {
		setWins(0);
		setLosses(0);
		setTies(0);
	}
	
	@Override
	public String toString(){
		return super.toString();
	}


	@Override
	public void saveStats() throws IOException {
		// TODO Auto-generated method stub
		
	}
}
