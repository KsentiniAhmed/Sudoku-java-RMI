import java.rmi.*;
import java.rmi.server.*;
public class Callback extends UnicastRemoteObject implements ICallback

{ public Callback() throws RemoteException
  { super() ; }

  public void doCallback(String message) throws RemoteException
  { System.out.println(message) ; }

}
