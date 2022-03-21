import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerAcesso implements Acesso {
	public ServerAcesso() {
	}
	// metodo main()
	
	public  static void main(String[] args) {
		try {
			// Instancia o objeto servidor e a sua stub
			ServerAcesso server = new ServerAcesso();
			Acesso stub = (Acesso) UnicastRemoteObject.exportObject(server, 0);
			// Registra a stub no RMI Registry para que ela seja obtida pelos clientes
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("acesso", stub);
			System.out.println("Servidor acesso pronto");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public int acesso_metodo(String nome, String curso, Double valor, String cpf, 
			String nomecc, String numcc, int validadecc, int codvcc, String email) throws RemoteException {
		if(email.contains("@")) {
			System.out.println("--------------------------------------------");
			System.out.println("|  acesso Confirmado para aluno Vigente    |");
			System.out.println("--------------------------------------------");
			System.out.println("Nome: \t\t" + nome);
			System.out.println("Nome: \t\t" + email);
			System.out.println("Curso:\t\t" + curso);
			System.out.println("Valor:\t\t" + valor);	
			System.out.println("CPF: \t\t" + cpf);
			System.out.println("Nome CC: \t" + nomecc);
			System.out.println("Num CC: \t" + numcc);
			System.out.println("Validade CC: \t" + validadecc);
			System.out.println("Cod V CC: \t" + codvcc);
			System.out.println("\n");
			return 1;			
		}
		else return 2;
		
	}
}

