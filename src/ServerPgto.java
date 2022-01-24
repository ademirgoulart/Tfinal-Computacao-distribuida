import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerPgto implements Pgto {
	public ServerPgto() {
	}
	// metodo main()

	public  static void main(String[] args) {
		try {
			// Instancia o objeto servidor e a sua stub
			ServerPgto server = new ServerPgto();
			Pgto stub = (Pgto) UnicastRemoteObject.exportObject(server, 0);
			// Registra a stub no RMI Registry para que ela seja obtida pelos clientes
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("pgto", stub);
			System.out.println("Servidor pgto pronto");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String pgto_metodo(String nomecc, String numcc, 
			int validadecc, int codvcc ) throws RemoteException {
		System.out.println("---------------------------------------------");
		System.out.println("|       Validando o Pagamento               |");
		System.out.println("---------------------------------------------");
		System.out.println("Nome no cartão  : " + nomecc);
		System.out.println("Numero do cartão: " + numcc);
		System.out.println("Validade        : " + validadecc);
		System.out.println("Codigo Val      : " + codvcc);
		return "1";
	}
}

