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

	public int nf_metodo(int op, String nome, Double valor, String cpf, 
			String curso) throws RemoteException {
		if (op == 1) { // rotina normal de geracao da nota 
					if (Double.parseDouble(cpf) != 0) {
						System.out.println("--------------------------------------------");
						System.out.println("|          Emissão da Nota Fiscal          |");
						System.out.println("--------------------------------------------");
						System.out.println("Nome: \t" + nome);
						System.out.println("Valor:\t" + valor);
						System.out.println("Curso: \t" + curso);
						System.out.println("CPF: \t" + cpf);
						System.out.println("\n");
						return 1;			
					}
					else  return 2;
		}
		else {
			// rotina de estorno da nota 
			System.out.println("--------------------------------------------");
			System.out.println("|          Estorno  da Nota Fiscal          |");
			System.out.println("--------------------------------------------");
			System.out.println("Nome: \t" + nome);
			System.out.println("Valor:\t" + valor);
			System.out.println("Curso: \t" + curso);
			System.out.println("CPF: \t" + cpf);
			System.out.println("\n");
			return 2;	
		}
			
	  }		
	}


