package views;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import player.Player;
import player.User;

import javax.swing.ListSelectionModel;


/*
 * This class is a JFrame that allows the user to load a 
 * saved username, create a new username, or delete a username.
 */
public class UserSelection extends JFrame {
	private JButton createBtn;
	private JTextField userInp;
	private JButton deleteButton;
	private JButton loadButton;
	
	private JList<Player> list;
	private DefaultListModel<Player> model;
	private File usersFile = new File("UserData//users.con");
	private TicTacToeController controller;
	
	
	public UserSelection(TicTacToeController controller) throws FileNotFoundException{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.controller = controller;
		
		setBounds(950, 300, 550, 400);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblSelectAUser = new JLabel("Select A User or Create a New User");
		lblSelectAUser.setBorder(new MatteBorder(2, 2, 0, 2, (Color) new Color(0, 0, 0)));
		lblSelectAUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAUser.setPreferredSize(new Dimension(400, 50));
		lblSelectAUser.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		getContentPane().add(lblSelectAUser, BorderLayout.NORTH);
		
		JPanel loadPanel = new JPanel();
		loadPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		getContentPane().add(loadPanel, BorderLayout.CENTER);
		loadPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblSelectAUser_1 = new JLabel("Select a User to Load");
		lblSelectAUser_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSelectAUser_1.setHorizontalAlignment(SwingConstants.CENTER);
		loadPanel.add(lblSelectAUser_1, BorderLayout.NORTH);
		
		model = new DefaultListModel<>();
		
		
	     
		list = new JList<>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		loadList();
			
		loadPanel.add(list, BorderLayout.CENTER);
		
		deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		loadPanel.add(deleteButton, BorderLayout.SOUTH);
		
		loadButton = new JButton("Load");
		loadButton.setPreferredSize(new Dimension(65, 25));
		loadButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		loadButton.setFont(new Font("Times New Roman", Font.BOLD, 24));
		loadPanel.add(loadButton, BorderLayout.WEST);
		
		JPanel createPanel = new JPanel();
		createPanel.setBorder(new MatteBorder(0, 2, 2, 2, (Color) new Color(0, 0, 0)));
		getContentPane().add(createPanel, BorderLayout.SOUTH);
		createPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblEnterYouUsername = new JLabel("Enter Your Username Here:");
		lblEnterYouUsername.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		createPanel.add(lblEnterYouUsername);
		lblEnterYouUsername.setPreferredSize(new Dimension(170, 50));
		
		userInp = new JTextField();
		userInp.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		createPanel.add(userInp);
		userInp.setColumns(10);
		
		createBtn = new JButton("Create");
		createBtn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		createPanel.add(createBtn);			
	}
	
	
	/*
	 * This method creates a user.
	 * It does not allow duplicate usernames to be created.
	 */
	public void addUser(){
		String username = userInp.getText();
		
		User newUser = new User(username);
		
		if(model.size() == 0)
			model.addElement(newUser);
		else{
			for(int i = 0; i < model.size(); i++){
				if(!username.equals(model.get(i).toString())){
					model.addElement(newUser);
					break;
				}
				break;
			}
		}
		
		controller.setLoadedUser(newUser);
		userInp.setText("");
	}
	
	/*
	 * This method deletes a user.
	 * You cannot delete the currently loaded user.
	 */
	public void deleteUser(){
		User listUser = (User) list.getSelectedValue();
		String username = listUser.toString();
		
		for(int i = 0; i < model.size(); i ++){
			User selectedUser = (User) model.get(i);
			if(selectedUser.toString().equals(username))
				model.remove(i);
		}
		
		saveUsers();
	}
	
	/*
	 * This method loads a user
	 */
	public Player loadUser(){
		String username = list.getSelectedValue().toString();
		
		for(int i = 0; i < model.size(); i ++){
			if(model.get(i).toString().equals(username))
				return model.get(i);
		}
		return null;
	}
	
	/*
	 * This method loads the JList and the JList's model with
	 * the saved usernames from the users.con file. 
	 */
	public void loadList(){
		
		boolean eof = false;
		
		try {
			FileInputStream fInput = new FileInputStream(usersFile);
			ObjectInputStream objectInp = new ObjectInputStream(fInput);
			
			while(!eof){
				User user = ((User) objectInp.readObject());
				
				if(user != null){
					//System.out.println("Loading: " + user.toString());
					model.addElement(user);
				}
			}
			
			objectInp.close();
			fInput.close();
			
		} 
		  catch (EOFException e){
			eof = true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/*
	 * Writes the userList to the users file
	 */
	public void saveUsers(){
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		
		try {
			fileOut = new FileOutputStream("UserData//users.con", false);
			objectOut = new ObjectOutputStream(fileOut);
			
			for(int i = 0; i < model.size(); i++){
				Player user = model.get(i);
				objectOut.writeObject(user);
				//System.out.println("Saving: " + user.toString());
			}
			
			objectOut.close();
			fileOut.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	/*
	 * Deletes a user after a user has been loaded
	 */
	public void deleteUser(Player user) throws FileNotFoundException{
		
		Player selectedUser = list.getSelectedValue();
		
		if(user != null && selectedUser.toString().equals(user.toString())){
			
			JOptionPane.showMessageDialog(null, "You cannot delete a user that is loaded.\n"
					+ " Load another user to delete this user.");
		}
		else{
		
			String name = JOptionPane.showInputDialog(null, "Confirm the username you wish to delete by entering it.");
			
			if(name == null)
				return;
			
			if(name.equals(selectedUser.toString())){
			
				for(int i = 0; i < model.size(); i++){
					if(model.get(i).toString().equals(name)){
						model.remove(i);
					}
				}
			}
		}
	}
	
	//Returns a reference to the create button
	public JButton getCreateBtn() {
		return createBtn;
	}
	//Returns a reference to the load button
	public JButton getLoadBtn() {
		return loadButton;
	}
	//Returns a reference to the delete button
	public JButton getDeleteBtn(){
		return deleteButton;
	}
	//Returns a reference to the text field
	public JTextField getUserInp() {
		return userInp;
	}
	//Returns a reference to the username list
	public JList getList() {
		return list;
	}
	//Returns a reference to the username list's model
	public DefaultListModel getModel(){
		return model;
	}
}