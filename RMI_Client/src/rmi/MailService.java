package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;
 
/**
 * The Interface MailService.
 */
public interface MailService extends Remote {
	
	  /**
  	 * Send message - method to send a message to the mail server.
  	 *
  	 * @param sender_Id the sender_ id
  	 * @param receiver_id the receiver_id
  	 * @param sender_name the sender_name
  	 * @param receiver_name the receiver_name
  	 * @param message the message
  	 * @return the message
  	 * @throws RemoteException the remote exception
  	 */
	  public Message sendMessage(UUID sender_Id, UUID receiver_id,
			  String sender_name, String receiver_name, String message)
	          throws RemoteException;  
	  
	  /**
  	 * Show messages - method to see the messages.
  	 *
  	 * @param id the id
  	 * @return the string
  	 * @throws RemoteException the remote exception
  	 */
	  public String showMessages(UUID id)
	          throws RemoteException;

}