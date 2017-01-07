import java .rmi.registry.* ;
import java.rmi.*;
import java.util.Scanner;
public class SudokuClient{
	public SudokuClient(String args[]){

                               if(System.getSecurityManager() == null)
		     		System.setSecurityManager(new RMISecurityManager());

             int[][] grid ;
             Scanner scan = new Scanner(System.in);
	//System.setSecurityManager(new RMISecurityManager());
	try{
           Callback callback = new Callback(); 
          //creation objet callback
	   Registry reg=LocateRegistry.getRegistry("localhost",1099) ;
              final FabSudokuInterface fabrique=(FabSudokuInterface) reg.lookup("Fabrique") ;
             SudokuInterface Obj ;
             Obj= (SudokuInterface)fabrique.newSudoku() ;

             Runtime.getRuntime().addShutdownHook(new Thread ()
				{
						public void run()
						{
							try
							{
							fabrique.close();
						}
							catch (Exception e) {
						    System.out.println ("Erreur d'acces a l'objet distant. serveur saturé");
						    System.out.println (e.toString());
			            }
						}
				});
        
             System.out.println("\n selectionner l'operation que vous voulez faire(quitter avec ctrl-c)");
	     System.out.println("\n selectionner le niveau \n 1: facile \n 2: difficile"); 
             int sc = scan.nextInt();
             if (sc==1) Obj.facile();
             else if (sc==2) Obj.difficile();
             else System.out.println("choix invalide !! veuillez verifier votre choix");  
	while(true){
                grid =Obj.getGameGrid ();
                String S=Obj.affichage(grid) ;
                System.out.println(S);

	System.out.println("selectionner l'operation que vous voulez faire(quitter avec ctrl-c)");
	System.out.println(" 0: choisir un autre niveau \n 1: Rejouer la partie  \n 2: ajouter un numero  \n 3: voir la solution \n 4: verifier votre grille " );
        int s = scan.nextInt();
	switch(s){
        case 0:
	System.out.println("selectionner le niveau \n 1: facile \n 2: difficile"); 
             sc = scan.nextInt();
             if (sc==1) Obj.facile();
             else Obj.difficile();
	break;
	
	case 1:
	System.out.print("la sudoku initiale est : \n ");
                if (sc==1) Obj.facile();
                else Obj.difficile();
                //grid =Obj.getGameGrid ();
	break;
	case 2:
                System.out.print("donner la ligne entre 1 et 9 ou tu veux ajouter le numero: \n ");
                while (!scan.hasNextInt()) 
                {
                System.out.print("donner la ligne entre 1 et 9:\n ");
                scan.next();
                }
                int l = scan.nextInt();

                System.out.print("donner la colonne entre 1 et 9 où tu veux ajouter le numero: \n ");
                while (!scan.hasNextInt()) 
                {
                System.out.print("donner la colonne entre 1 et 9:\n ");
                scan.next();
                }
                int c = scan.nextInt();
               
                System.out.print("donner le numero entre 1et 9:\n ");

                while (!scan.hasNextInt()) 
                {
                System.out.print("donner le numero entre 1 et 9:\n ");
                scan.next();
                }
                int num = scan.nextInt();

                if (num >0 && num <10 && c>0 && c<10 && l>0 && l<10) {
                
                boolean XX=Obj.addNumberC(num,l-1,c-1,callback);
                  
                }
                else
                 System.out.println("erreur dans l'insertion des valeurs ...");

	break;
	case 3:
             System.out.print("la solution est: \n ");
              Obj.estValide(0);
                

                 	break;
         case 4:
              boolean WW =Obj.verif(callback);
           
                

                 	break;
	default:
	System.out.println("choix invalide !! veuillez verifier votre choix");
	break;
	}
	}
	}
	catch(Exception e){
	System.out.println("erreur d'accés a l'objet distant");
	System.out.println(e.toString());
	}
}
}
