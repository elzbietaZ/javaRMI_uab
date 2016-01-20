package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.UUID;

import rmi.IDService;
import rmi.MailService;
import rmi.Message;
 
 
/**
 * The Class Client.
 */
public class Client  implements Runnable {
 
    /** The Constant HOST_IDSERVER. */
    private static final String HOST_IDSERVER = "54.152.4.116";
    
    /** The Constant PORT_IDSERVER. */
    private static final int PORT_IDSERVER = 1201;
    
    /** The Constant HOST_MAILSERVER. */
    private static final String HOST_MAILSERVER = "54.152.4.116";
    
    /** The Constant PORT_MAILSERVER. */
    private static final int PORT_MAILSERVER = 1202;
    
    /** The registry_idserver. */
    private static Registry registry_idserver;
    
    /** The registry_mailserver. */
    private static Registry registry_mailserver;
    
    /** The user_ name. */
    private static String user_Name = ""; 
	
	/** The user_ id. */
	private static UUID user_Id = null;
 
    /**
     * The main method.
     *
     * @param args the arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
    	new Client().run();
    }

	@Override
	public void run() {
		// initializing the registry for id server
        try {
			registry_idserver = LocateRegistry.getRegistry(HOST_IDSERVER, PORT_IDSERVER);
			System.out.println(registry_idserver.toString());
        } catch (RemoteException e) {
			e.printStackTrace();
		}
		// initializing the registry for mail server
		try {
			registry_mailserver = LocateRegistry.
					getRegistry(HOST_MAILSERVER, PORT_MAILSERVER);
			System.out.println(registry_mailserver.toString());
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}	
        
        IDService service_id = null;
        MailService service_mail = null;

        //bounding idserver to the specified name in this registry
		try {
			service_id = (IDService) registry_idserver
			        .lookup(IDService.class.getSimpleName());
		} catch (RemoteException | NotBoundException e1) {
			e1.printStackTrace();
		}
		
        //bounding mailserver to the specified name in this registry
		try {
			service_mail = (MailService) registry_mailserver
			        .lookup(MailService.class.getSimpleName());
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
        boolean end = false;
          
        Scanner input = new Scanner(System.in);
        
        // showing options to the user
        while (!end) {
        	
        	if (!user_Name.equals("")) {
        		end = show_logged_options(input, service_id, service_mail);	
        	} else {
        		end = show_not_logged_options(input, service_id);
        	}        	
        }
        
        input.close();		
	}
	
	/**
	 * Show options for the logged user.
	 *
	 * @param input the input
	 * @param service_id the service_id
	 * @param service_mail the service_mail
	 * @return true, if successful
	 */
	private boolean show_logged_options(Scanner input, IDService service_id,
			MailService service_mail) {
        String UsrIn = "";
        boolean end = false;
		System.out.println("You are logged as: " + user_Name);
    	System.out.println("Choose an option:\n1.Close Session.\n"
    			+ "2.Send Message.\n3.Show Messages."
    			+ "\n4.Close Application.");
        UsrIn = input.nextLine();
        switch (UsrIn) {
            case "1":  close_session();
                     break;
            case "2":  send_message(input, service_id, service_mail);
   		 			 break;
            case "3":  show_messages(service_mail, user_Id);
   		 			 break;
            case "4":  System.out.println("Bye!");
   		 			 end = true;
   		 			 break;
            default: System.out.println("Invalid Option.");
                     break;
        }
        
        System.out.println("-------------------------------------------------");
        return end;
	}
	
	/**
	 * Show options for not logged user.
	 *
	 * @param input the input
	 * @param service the service
	 * @return true, if successful
	 */
	
	private boolean show_not_logged_options(Scanner input, IDService service) {
		String UsrIn = "";
        boolean end = false;
    	System.out.println("Choose an option:\n1.Login/Register.\n"
    			+ "2.Close Application.");
        UsrIn = input.nextLine();
        switch (UsrIn) {
            case "1":  login_register(input, service);
                     break;
            case "2":  System.out.println("Bye!");
            		 end = true;
                     break;
            default: System.out.println("Invalid Option.");
                     break;
        }
        
        System.out.println("-------------------------------------------------");
        return end;
	}
	
	/**
	 * Registration.
	 *
	 * @param input the input
	 * @param service the service
	 */
	private void login_register(Scanner input, IDService service) {
		UUID generated_id = null;
		String name = "";

		System.out.println("Write your Name (Min. 3 letters):");
        name = input.nextLine();
        
        if (name.length() < 3) {
       	 System.out.println("The name must be at least of length 3.");
        } else {
			 try {							
			 	generated_id = service.generateID(name);
			 } catch (RemoteException e) {
			 	e.printStackTrace();
			 }

			 user_Name = name; 
			 user_Id = generated_id;
             System.out.println("Registered/Logged with ID: " + generated_id);
		 }
	}
	
	/**
	 * Close_session.
	 */
	private void close_session() {
		 user_Name = ""; 
		 user_Id = null;
	}

	/**
	 * Send_message.
	 *
	 * @param input the input
	 * @param service_id the service_id
	 * @param service_mail the service_mail
	 */
	private void send_message(Scanner input, IDService service_id,
			MailService service_mail) {
		
		UUID id = null;
		String name = "";
		String message = "";
		Message m = null;

		System.out.println("Write the Name of the Receiver (Min. 3 letters):");
        name = input.nextLine();
        
        if (name.length() < 3) {
       	 System.out.println("The name must be at least of length 3.");
        } else {
			 try {							
			 	id = service_id.requestID(name);
			 } catch (RemoteException e) {
			 	e.printStackTrace();
			 }
			 
			 if (id == null) {
		       	 System.out.println("There isn't any user with that name.");
		     } else {
		    	 System.out.println("Write the message (and Press Enter to send):");
		         message = input.nextLine();
		         try {
					 m = service_mail.sendMessage(user_Id, id, user_Name, name, message);
				 } catch (RemoteException e) {

					 e.printStackTrace();
				 }
		         
		         System.out.println(m);
		     }
		 }
	}
	
	/**
	 * Show messages.
	 * Lets to see messages in inbox and outbox.
	 *
	 * @param service the service
	 * @param id the id
	 */
	private void show_messages(MailService service, UUID id) {
		
		String messages = "";
		
        try {
			 messages = service.showMessages(id);
		} catch (RemoteException e) {
			 e.printStackTrace();
		}
        
        if (messages.equals("")) {
        	System.out.println("No Messages Sent or Received.");
        } else {
        	System.out.println(messages);
        }
	}
}
