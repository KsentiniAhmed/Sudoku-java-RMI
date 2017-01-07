import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.*;

interface SudokuInterface extends Remote { 
public void facile() throws RemoteException;
public void difficile() throws RemoteException ;
public int [][] getGameGrid () throws RemoteException; 
public void callMeBack(String param,ICallback callback) throws RemoteException;
public boolean addNumber (int num, int r, int c) throws RemoteException; 
public boolean absentSurLigne (int k, int grille[][], int i) throws RemoteException ;
public boolean absentSurColonne (int k, int grille[][], int j) throws RemoteException ;
public boolean absentSurBloc (int k, int grille[][], int i, int j) throws RemoteException ; 
public String affichage (int grille[][])throws RemoteException;
public boolean estValide (int position)throws RemoteException;
public boolean addNumberC (int num, int r, int c,ICallback cb) throws RemoteException; 
public boolean verif (ICallback cb) throws RemoteException ;

}
