
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Pgto extends Remote {
   public int  pgto_metodo(int op,String nomecc, String numcc, int validadecc, int codvcc) throws RemoteException;
}