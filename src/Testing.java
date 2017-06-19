import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import player.Player;
import player.User;

public class Testing {

	public static void main(String[] args) {
		Player[] users = new Player[3];
		users[0] = new User("James");
		users[1] = new User("Billy");
		users[2] = new User("Anderson");
		
		FileOutputStream fileOut;
		ObjectOutputStream objectOut;
		
		try {
			fileOut = new FileOutputStream("UserData//users.con");
			objectOut = new ObjectOutputStream(fileOut);
			
			for(int i = 0; i < users.length; ++i){
				Player user = users[i];
				objectOut.writeObject(user);
				String name = user.toString();
				System.out.println("Saving: " + i + "  " + name);
			}
			
			objectOut.close();
			fileOut.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	
		boolean eof = false;
		
		ArrayList<User> usersList = new ArrayList<>();
		
		try {
			FileInputStream fInput = new FileInputStream("UserData//users.con");
			ObjectInputStream objectInp = new ObjectInputStream(fInput);
			
			while(!eof){
				User user = ((User) objectInp.readObject());
				
				if(user != null){
					System.out.println("Loading: " + user.toString());
					usersList.add(user);
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
}
