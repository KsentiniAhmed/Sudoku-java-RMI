import java.rmi.* ;
public interface FabSudokuInterface extends Remote
{
public SudokuInterface newSudoku() throws RemoteException ; 
public void close() throws RemoteException;
}
