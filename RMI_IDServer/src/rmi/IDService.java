package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;
 
/**
 * The Interface IDService.
 */
public interface IDService extends Remote {
  
  /**
   * Generate id - method to generate an ID for the user. 
   *
   * @param name the name
   * @return the uuid
   * @throws RemoteException the remote exception
   */
  public UUID generateID(String name)
          throws RemoteException;

  /**
   * Request id - method to request the ID for of an user.
   *
   * @param name the name
   * @return the uuid
   * @throws RemoteException the remote exception
   */
  public UUID requestID(String name)
          throws RemoteException;
}