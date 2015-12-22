package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import rmi.IDService;
 
public class IDServiceImplementation extends UnicastRemoteObject implements IDService {
	
	private static final long serialVersionUID = 1L;
	
	private static Map<String,UUID> users = new HashMap<String,UUID>();
	
	public IDServiceImplementation() throws RemoteException {
	super();
	}

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
