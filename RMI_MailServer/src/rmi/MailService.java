package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;
 
public interface MailService extends Remote {
	
	  // Method to send a message to the mail server. 
	  public Message sendMessage(UUID sender_Id, UUID receiver_id,
			  String sender_name, String receiver_name, String message)
	          throws RemoteException;  
	  
	  // Method to see the messages.
	  public String showMessages(UUID id)
	          throws RemoteException;

}