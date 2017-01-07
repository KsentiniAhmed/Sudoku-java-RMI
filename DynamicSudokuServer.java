import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.rmi.server.RMIClassLoader;
import java.util.Properties;

public class DynamicSudokuServer {
	public static void main(String[] args)
 {                      if(System.getSecurityManager() == null)
			System.setSecurityManager(new RMISecurityManager());

	       try 
       {
              Registry registry = LocateRegistry.createRegistry(1099);

		System.out.println( "Serveur : Construction de l'implementation ");
		//FabHelloImp fab= new FabHelloImp();
		Properties p=System.getProperties(); 
                String url=p.getProperty("java.rmi.server.codebase") ;
                
                Class ClasseServeur = RMIClassLoader.loadClass(url,"FabSudokuImpl");
                
                registry.rebind("Fabrique" , (Remote)ClasseServeur.newInstance()); 
               
		//registry.rebind("Fabrique",fab);
		System.out.println("Serveur prét.");
		System.out.println("Attente des invocations des clients..");
	       }
	       
	       catch (Exception e) {
		System.out.println("Erreur de liaison de l'objet Sudoku");
		System.out.println(e.toString());
	   }	
    }	
}

