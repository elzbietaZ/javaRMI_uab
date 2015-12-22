package server;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.MailService;
 
public class Server implements Runnable{
   private static final int PORT = 1202;
   private static Registry registry;
 
   public static void startRegistry() throws RemoteException {
       // Create server registry
       registry =  LocateRegistry.createRegistry(PORT);
   }
 
   public static void registerObject(String name, Remote remoteObj)
           throws RemoteException, AlreadyBoundException {
 
       // Bind the object in the registry.
       // It is bind with certain name.
       // Client will lookup on the registration of the name to get object.        
       registry.bind(name, remoteObj);
       System.out.println("Registered: " + name + " -> "
               + remoteObj.getClass().getName() + "[" + remoteObj + "]");
   }
 
   public static void main(String[] args) throws Exception {
       new Server().run();
   }

public void run() {
	System.out.println("Server starting...");
    try {

    	startRegistry();
    	registerObject(MailService.class.getSimpleName(), new MailServiceImplementation());

    // Server was the start, and was listening to the request from the client.
    System.out.println("Server started! Click Enter to close it!");
	} catch (RemoteException e) {
		e.printStackTrace();
	} catch (AlreadyBoundException e) {
		e.printStackTrace();
	}
    
    try {
		System.in.read();
	} catch (IOException e) {
		e.printStackTrace();
	}
    System.out.println("Bye!");
    System.exit(0);
}
}
