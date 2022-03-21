
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Nf extends Remote {
   public int  nf_metodo(int op, String nome, Double valor, String cpf, String curso) throws RemoteException;
}