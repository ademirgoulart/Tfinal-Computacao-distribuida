
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Nf extends Remote {
   public String  nf_metodo(String nome, Double valor, String cpf, String curso) throws RemoteException;
}