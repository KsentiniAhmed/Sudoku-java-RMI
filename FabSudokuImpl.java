import java.rmi.* ; 
import java.rmi.server.*; 
public class FabSudokuImpl extends UnicastRemoteObject implements FabSudokuInterface {
public static int nbclient=0;
public FabSudokuImpl() throws RemoteException {} ;
public SudokuInterface newSudoku() throws RemoteException 
  { 
		if ((nbclient++) <10)
		{
		 System.out.println("le client numero: "+nbclient +"  commence à jouer " );
		 return new Sudoku() ;
		}
                else 
                {
			System.out.println("max 10 client en meme temps ... ");
			return null;
		}
  }
public void close() throws RemoteException{ 
       
 nbclient--; 
 System.out.println("un client est sorti \n le nombre de client :"+nbclient);
       
 }
}

