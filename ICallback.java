import java.rmi.*;
public interface ICallback extends Remote {
public void doCallback(String message) throws RemoteException;}