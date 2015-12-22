package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;
 
public interface IDService extends Remote {
  
  // Method to generate an ID for the user. 
  public UUID generateID(String name)
          throws RemoteException;
  
  // Method to request the ID for of an user. 
  public UUID requestID(String name)
          throws RemoteException;
}