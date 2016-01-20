package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rmi.MailService;
import rmi.Message;
 
/**
 * The Class MailServiceImplementation.
 */
public class MailServiceImplementation extends UnicastRemoteObject implements MailService {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The messages. */
	private static List<Message> messages = new ArrayList<Message>();
	
	/**
	 * Instantiates a new mail service implementation.
	 *
	 * @throws RemoteException the remote exception
	 */
	public MailServiceImplementation() throws RemoteException {
	super();
	}

	/* (non-Javadoc)
	 * @see rmi.MailService#sendMessage(java.util.UUID, java.util.UUID, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Message sendMessage(UUID sender_Id, UUID receiver_id,
			  String sender_name, String receiver_name, 
			  String message) throws RemoteException {
		Message m = new Message(sender_Id, receiver_id,
				  sender_name, receiver_name, message);
		messages.add(m);
		return m;
	}

	/* (non-Javadoc)
	 * @see rmi.MailService#showMessages(java.util.UUID)
	 */
	public String showMessages(UUID id) throws RemoteException {
		String messages_of_id_sent = ""; 
		String messages_of_id_received = "";

		//SENT
		for (Message m : messages) {
			if (m.getId_sender().equals(id)) {
				messages_of_id_sent = m.toString() + messages_of_id_sent;
			}
		}
		
		if (!messages_of_id_sent.equals("")) {
			messages_of_id_sent = "-------------------------------------------------\n"
					+ "SENT\n-------------------------------------------------\n"
					+ messages_of_id_sent;
		}
		
		//RECEIVED
		for (Message m : messages) {
			if (m.getId_receiver().equals(id)) {
				messages_of_id_received = m.toString() + messages_of_id_received;
			}
		}
		
		if (!messages_of_id_received.equals("")) {
			messages_of_id_received = "-------------------------------------------------\n"
					+ "RECEIVED\n-------------------------------------------------\n"
					+ messages_of_id_received;
		}
		
		if (!messages_of_id_sent.equals("") && !messages_of_id_received.equals("")) {
			messages_of_id_received = messages_of_id_received + "\n";
		}
		
		return messages_of_id_received + messages_of_id_sent;
	}

}
