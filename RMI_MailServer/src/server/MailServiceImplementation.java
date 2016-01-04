package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rmi.MailService;
import rmi.Message;
 
public class MailServiceImplementation extends UnicastRemoteObject implements MailService {
	
	private static final long serialVersionUID = 1L;
	
	private static List<Message> messages = new ArrayList<Message>();
	
	public MailServiceImplementation() throws RemoteException {
	super();
	}

	public Message sendMessage(UUID sender_Id, UUID receiver_id,
			  String sender_name, String receiver_name, 
			  String message) throws RemoteException {
		Message m = new Message(sender_Id, receiver_id,
				  sender_name, receiver_name, message);
		messages.add(m);
		return m;
	}

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
