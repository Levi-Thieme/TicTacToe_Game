package player;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

import javax.swing.JOptionPane;

public abstract class Player implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7155712919793047621L;
	private String name;
	private int wins;
	private int losses;
	private int ties;
		
	// Constructor for Player object
	public Player() {
	}
	
	public abstract void addWin();
	public abstract void addLoss();		
	public abstract void addTie();
	public abstract void clearStats();
	public abstract void showStats();
	public abstract void saveStats() throws IOException;

	//Returns the win count
	public int getWins() {
		return wins;
	}
	//Returns the losses count
	public int getLosses() {
		return losses;
	}
	//Returns the ties count
	public int getTies() {
		return ties;
	}
	@Override
	public String toString(){
		return getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public void setTies(int ties) {
		this.ties = ties;
	}
	
	
}
