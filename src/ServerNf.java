import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerNf implements Nf {
	public ServerNf() {
	}
	// metodo main()
	
	public  static void main(String[] args) {
		try {
			// Instancia o objeto servidor e a sua stub
			ServerNf server = new ServerNf();
			Nf stub = (Nf) UnicastRemoteObject.exportObject(server, 0);
			// Registra a stub no RMI Registry para que ela seja obtida pelos clientes
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("nf", stub);
			System.out.println("Servidor nf pronto");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String nf_metodo(String nome, Double valor, String cpf, String curso) throws RemoteException {
		
		System.out.println("--------------------------------------------");
		System.out.println("|          Emissão da Nota Fiscal          |");
		System.out.println("--------------------------------------------");
		System.out.println("Nome: \t" + nome);
		System.out.println("Valor:\t" + valor);
		System.out.println("Curso: \t" + curso);
		System.out.println("CPF: \t" + cpf);
		System.out.println("\n");
		
		return "1";
	}
}

