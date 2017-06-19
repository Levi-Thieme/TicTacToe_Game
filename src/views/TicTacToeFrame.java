package views;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.MenuItem;

import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

/*
 * This Class is a JFrame for the Tic Tac Toe game.
 * It contains the panels and menu for the application.
 */
public class TicTacToeFrame extends JFrame {
	private JMenuItem mntmStatistics;
	private JMenuItem mntmNewGame;
	private JTilesPane tilePane;
	private JMenuItem mntmSaveAndExit;
	private JMenuItem mntmTheme1;
	private JMenuItem mntmTheme2;
	private JMenuItem mntmClear;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	
	
	public TicTacToeFrame() {
		setBounds(300, 300, 600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(100, 10));
		getContentPane().add(panel, BorderLayout.WEST);
		
		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 100));
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(100, 10));
		getContentPane().add(panel_2, BorderLayout.EAST);
		
		panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(10, 100));
		getContentPane().add(panel_3, BorderLayout.SOUTH);
		
		JTilesPane tilesPane = new JTilesPane();
		getContentPane().add(tilesPane, BorderLayout.CENTER);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnFile);
		
		mntmNewGame = new JMenuItem("New Game");
		mntmNewGame.setMnemonic(KeyEvent.VK_N);
		mnFile.add(mntmNewGame);
		
		mntmStatistics = new JMenuItem("Statistics");
		mntmStatistics.setMnemonic(KeyEvent.VK_S);
		mnFile.add(mntmStatistics);
		
		mntmClear = new JMenuItem("Clear Statistics");
		mntmClear.setMnemonic(KeyEvent.VK_C);
		mnFile.add(mntmClear);
		
		mntmSaveAndExit = new JMenuItem("Save and Exit");
		mntmSaveAndExit.setMnemonic(KeyEvent.VK_E);
		mnFile.add(mntmSaveAndExit);
		
		JMenu mnTheme = new JMenu("Theme");
		mnTheme.setMnemonic(KeyEvent.VK_T);
		menuBar.add(mnTheme);
		
		mntmTheme1 = new JMenuItem("Theme 1");
		mnTheme.add(mntmTheme1);
		
		mntmTheme2 = new JMenuItem("Theme 2");
		mnTheme.add(mntmTheme2);
		
		tilePane = tilesPane;
		tilePane.setPath();
		this.setFrameBG(Color.black);
		
	}
	
	//Returns a reference to the theme1 menu item
	public JMenuItem getMntmTheme1() {
		return mntmTheme1;
	}
	//Returns a reference to the them2 menu item
	public JMenuItem getMntmTheme2() {
		return mntmTheme2;
	}
	//Returns a reference to the statistics menu item
	public JMenuItem getStatistics(){
		return mntmStatistics;
	}
	//Returns a reference to the new game menu item
	public JMenuItem getNewGame(){
		return mntmNewGame;
	}
	//Returns a reference to the SaveAndExit menu item
	public JMenuItem getSaveAndExit(){
		return mntmSaveAndExit;
	}
	//Returns a reference to the tilesPane
	public JTilesPane getTilesPane(){
		return tilePane;
	}
	//Returns a reference to the ClearStats menu item
	public JMenuItem getClearStats(){
		return mntmClear;
	}
	
	/* Sets the background color of the outside panels
	 * to the color paramater */
	public void setFrameBG(Color color){
		panel.setBackground(color);
		panel_1.setBackground(color);
		panel_2.setBackground(color);
		panel_3.setBackground(color);
	}
	
	

}
