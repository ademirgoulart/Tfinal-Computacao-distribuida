
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Pgto extends Remote {
   public String  pgto_metodo(String nomecc, String numcc, int validadecc, int codvcc) throws RemoteException;
}