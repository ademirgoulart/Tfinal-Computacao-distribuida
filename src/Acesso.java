
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface Acesso extends Remote {
   public int acesso_metodo( String nome, String curso, Double valor, String cpf, String nomecc, 
		   String numcc, int validadecc,  int codvcc, String email) throws RemoteException;
}