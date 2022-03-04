import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Locale;
import java.util.Scanner;

import entities.Clientes;

public class Cliente {
	public static void main(String[] args) {
      String host = (args.length < 1) ? null : args[0];
      Locale.setDefault(Locale.US);
      Scanner sc = new Scanner(System.in);      
      System.out.print("Enter nome arquivo: ");
		String sourceFileStr = sc.nextLine();
		try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))) {

			String itemCsv = br.readLine();
			while (itemCsv != null) {
				String[] fields = itemCsv.split(",");
			
				 Clientes  aluno = new Clientes(fields[0], fields[1], Double.parseDouble(fields[2]),
		    			  fields[3], fields[4], fields[5], Integer.parseInt(fields[6]), Integer.parseInt(fields[7])  );
			
			     
				      try {
				    	  
				
				    	 
				         // Obtém uma referência para o registro do RMI
				         Registry registry = LocateRegistry.getRegistry(host);
				 
				         // Obtém a stub do servidor
				         Pgto stub= (Pgto) registry.lookup("pgto");
				         Nf stub2= (Nf) registry.lookup("nf");
				         Acesso stub3= (Acesso) registry.lookup("acesso");
				 
				         // Chama o método do servidor e imprime a mensagem
				         System.out.println("======================================== ");
				         System.out.println("Processamento para o aluno: " + aluno.getNome());
				        String msg = stub.pgto_metodo(aluno.getNomecc(), aluno.getNumcc(),
				        		aluno.getValidadecc(), aluno.getCodvcc());
				        System.out.println("Mensagem do Servidor Pagamento: " + msg);
				        
				        String msg2 = stub2.nf_metodo(aluno.getNome(), aluno.getValor(), aluno.getCpf(), aluno.getCurso());
				        System.out.println("Mensagem do Servidor NF : " + msg2); 
				        
				        String msg3 = stub3.acesso_metodo(aluno.getNome(), aluno.getCurso(), aluno.getValor(),
				        		aluno.getCpf(), aluno.getNomecc(), aluno.getNumcc(), 
				        		aluno.getValidadecc(), aluno.getCodvcc());
				        System.out.println("Mensagem do Servidor Acesso : " + msg3); 
				        
				        
				      } catch (Exception ex) {
				         ex.printStackTrace();
				      } 
				      
				      itemCsv = br.readLine();
			}
			sc.close();
		}
		catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
	}
}