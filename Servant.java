import java.rmi.*;
import java.util.logging.Level;

public class Servant extends Thread {
private int time;
private String param;
private ICallback callback;
public Servant(String param, ICallback callback) {
this.time = time;
this.param = param;
this.callback = callback;}

public void run(){
try{
Thread.sleep(1000*time);
}
catch(InterruptedException e){}
try {
callback.doCallback(param);
}
catch(RemoteException e)
{System.err.println("Echec appel en retour : "+e);
}
callback = null;
System.gc();
}}