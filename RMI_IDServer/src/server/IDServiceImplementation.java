package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import rmi.IDService;
 
/**
 * The Class IDServiceImplementation.
 */
public class IDServiceImplementation extends UnicastRemoteObject implements IDService {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The map of the users. */
	private static Map<String,UUID> users = new HashMap<String,UUID>();
	
	/**
	 * Instantiates a new ID service implementation.
	 *
	 * @throws RemoteException the remote exception
	 */
	public IDServiceImplementation() throws RemoteException {
	super();
	}

	/**
	 Generates id for the new user or returns existing id of the user for the given name
	 */
	public UUID generateID(String name) throws RemoteException {
		UUID id = null;
		if (users.containsKey(name)) {
			id = requestID(name);
		} else {
			id = UUID.randomUUID();
			users.put(name,id);
		}
		return id;
	}

	public UUID requestID(String name) throws RemoteException {
		UUID id = users.get(name);
		return id;
	}
}
