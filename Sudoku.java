import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.io.*;
public class Sudoku extends UnicastRemoteObject implements SudokuInterface{
private static final int GRSIZE= 9; // default grid size
private int size;// size of grid 
private int[][] grid;// sudoku grid
private int[][] gridi;


    public Sudoku() throws RemoteException{
	super();

}// end constructor

	
public void facile() throws RemoteException {
this.size = GRSIZE;
//initialise the Grid 

initGrid();
initGridi();
// set the starting conditions
addNumber(4, 0, 0);addNumber(3, 0, 6);addNumber(8, 0, 8);
addNumber(9, 1, 2);addNumber(1, 1, 3);addNumber(7, 1, 5);addNumber(5, 1, 6);
addNumber(2, 2, 0);addNumber(6, 2, 1);addNumber(5, 2, 4);addNumber(3, 2, 5);
addNumber(1, 2, 6);
addNumber(3, 3, 4);addNumber(6, 3, 5);addNumber(8, 3, 7);addNumber(1, 3, 8);
addNumber(7, 4, 1);addNumber(4, 4, 3);addNumber(1, 4, 5);addNumber(9, 4, 7);
addNumber(5, 5, 0);addNumber(1, 5, 1);addNumber(7, 5, 3);addNumber(2, 5, 4);
addNumber(5, 6, 2);addNumber(2, 6, 3);addNumber(9, 6, 4);addNumber(6, 6, 7);
addNumber(7, 6, 8);
addNumber(4, 7, 2);addNumber(6, 7, 3);addNumber(8, 7, 5);addNumber(9, 7, 6);
addNumber(8, 8, 0);addNumber(6, 8, 2);addNumber(2, 8, 8);
gridI();
}
public void difficile() throws RemoteException {
this.size = GRSIZE;
//initialise the Grid 
initGridi();
initGrid();
// set the starting conditions
addNumber(4, 0, 0);addNumber(5,2 , 4);
addNumber(3, 3, 4);addNumber(6, 3, 5);
addNumber(7, 4, 1);addNumber(4, 4, 3);
addNumber(5, 5, 0);addNumber(1, 5, 1);
addNumber(4, 7, 2);addNumber(6, 7, 3);
addNumber(8, 8, 0);addNumber(2,8 , 8);
addNumber(1,7,4);
gridI();
}
public void initGrid() {

grid = new int[size][size];
// initialse each cell
for (int r = 0; r < grid.length; r++) {
for (int c = 0; c < grid[0].length; c++) {

grid[r][c]= 0;

}
}
}
public void gridI() {

gridi = new int[9][9];
// initialse each cell
for (int r = 0; r < 9; r++) {
for (int c = 0; c < 9; c++) {
if (grid[r][c]!=0)
{gridi[r][c]=grid[r][c];} 
else gridi[r][c]=10;
}
}
}
public void initGridi() {

gridi = new int[9][9];
// initialse each cell
for (int r = 0; r < 9; r++) {
for (int c = 0; c < 9; c++) {

gridi[r][c]=10; 

}
}
}

public String affichage (int grille[][])throws RemoteException

{
String S="------------\n";
    for (int i=0; i<9; i++)
   {
        for (int j=0; j<9; j++)
        { if ((j+1)%3!=0)
         S=S+(grille[i][j]);
          else
         S=S+(grille[i][j]+"|");
                 }

        S=S+("\n");

        if (((i+1)%3)==0)

       S=S+("------------\n");

    }

    S=S+("\n");
return (S);
}
public int [][] getGameGrid () throws RemoteException{
  int copy[][] = new int [grid.length][grid[0].length];
        for (int r=0; r<copy.length; r++)
          for (int c=0; c< copy[0].length; c++)
             copy[r][c] = grid[r][c];
        return ( copy); 
    } //  end getGrid
////////////////fonction de verification et de validation:

public boolean absentSurLigne (int k, int grille[][], int i) throws RemoteException 

{

    for (int j=0; j < 9; j++)

        if (grille[i][j] == k)

            return false;

    return true;

}


public boolean absentSurColonne (int k, int grille[][], int j) throws RemoteException 

{

    for (int i=0; i < 9; i++)

        if (grille[i][j] == k)

            return false;

    return true;

}


public boolean absentSurBloc (int k, int grille[][], int i, int j) throws RemoteException 

{

    int _i = i-(i%3), _j = j-(j%3);  // ou encore : _i = 3*(i/3), _j = 3*(j/3);

    for (i=_i; i < _i+3; i++)

        for (j=_j; j < _j+3; j++)

            if (grille[i][j] == k)

                return false;

    return true;

}///////////////verification//////////////////

public boolean verif(ICallback cb) throws RemoteException 
{ 
// on a fait comme ca car la grille n'a pas une seule solution donc selon le remplissage de client on cree une solution
////la matrice solution va contenir la matrice de client et non pas la solution car la solution sera dans la grid
int [][]solution;
solution=getGameGrid ();
boolean X =estValide(0);
    for (int i=0; i <9; i++)
   {
     for (int j=0; j < 9; j++)
     {  if (grid[i][j]!=solution[i][j]) {
            callMeBack("tu as perdu :( \n tu peux rejouer ou choisir un autre niveau \n voici la solution  :",cb); 
        return true;}
      }
    }
 callMeBack("tu as gagné félicitation,,,,,,,,,,,",cb);
 return false;
}

/////////fonction backtracking de openclassroom /////////
public boolean estValide (int position) throws RemoteException 
{
    if (position == 9*9)
        return true;
    int i = position/9;
    int j = position%9;
    if (grid[i][j] != 0)
        return estValide(position+1);
    for (int k=1; k <= 9; k++)
    {
        if (absentSurLigne(k,grid,i) && absentSurColonne(k,grid,j) && absentSurBloc(k,grid,i,j))
        {
            grid[i][j] = k;
            if ( estValide (position+1) )
                return true;
        }
    }
    grid[i][j] = 0;
    return false;
}

//////////////fonction add number 
public boolean addNumber(int n, int r, int c) throws RemoteException 
{
      if (absentSurLigne(n,grid,r) && absentSurColonne(n,grid,c) && absentSurBloc(n,grid,r,c)&& gridi[r][c]!=grid[r][c])
        {
            grid[r][c] = n;
            return true ;
         }
                 else {
                        //grid[r][c] = n;
                        return false;
                      }
}
//////////fonction add number avec callback//////////
public boolean addNumberC(int n, int r, int c,ICallback cb) throws RemoteException 
{
      if (absentSurLigne(n,grid,r) && absentSurColonne(n,grid,c) && absentSurBloc(n,grid,r,c)&& gridi[r][c]!=grid[r][c])
        {
            grid[r][c] = n;
            callMeBack("le numero: "+n+" est ajouter avec succée dans la ligne :"+(r+1)+" et la colonne :"+(c+1)+"",cb); 
            return true ;
         }
                 else {            callMeBack("erreur le numéro existe déja dans la grille vérifier",cb); 
                        //grid[r][c] = n;
                        return false;
                      }
}
 //////////////////
  public void callMeBack(String param,ICallback callback)throws RemoteException{
	Servant servant = new Servant(param,callback);
	// création du servant
	servant.start();
	// démarrage du servant
	}
}
