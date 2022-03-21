import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Locale;
import java.util.Scanner;

import entities.Clientes;
//   ativar inicialmente rmiregistry 
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
		    			  fields[3], fields[4], fields[5], 
		    			  Integer.parseInt(fields[6]), Integer.parseInt(fields[7]), fields[8], 1  );
						     
				      try {
										    	 
				         // Obtém uma referência para o registro do RMI
				         Registry registry = LocateRegistry.getRegistry(host);
				 
				         // Obtém a stub de cada servidor
				         Pgto stub= (Pgto) registry.lookup("pgto");   		// Pgto  &  ServerPgto
				         Nf stub2= (Nf) registry.lookup("nf"); 				// Nf    &  ServerNf
				         Acesso stub3= (Acesso) registry.lookup("acesso");	//Acesso &  ServerAcesso
				 
				         //  imprime a mensagem para o aluno em processamento
				         System.out.println("======================================== ");
				         System.out.println("Processamento para o aluno: " + aluno.getNome());
				        
				         // Chama o método do servidor que trata pagamento
				         int  retorno_pgto = stub.pgto_metodo(1, aluno.getNomecc(), aluno.getNumcc(),
				        		aluno.getValidadecc(), aluno.getCodvcc());
				         
				         if (retorno_pgto == 1) {
				        	 System.out.println("Mensagem do Servidor Pagamento: Pagamento processado OK." );
				        	// Chama o método do servidor que trata Nota fiscal
					         int  retorno_nf = stub2.nf_metodo(1, aluno.getNome(), aluno.getValor(), 
					        		 aluno.getCpf(), aluno.getCurso());
					         
					         if (retorno_nf == 1) {
					        	 System.out.println("Mensagem do Servidor NF : Nota Fiscal emitida OK."); 
					        	 // Chama o método do servidor que trata liberação do aluno
						         int retorno_liberacao = stub3.acesso_metodo(aluno.getNome(), aluno.getCurso(), aluno.getValor(),
						        		aluno.getCpf(), aluno.getNomecc(), aluno.getNumcc(), 
						        		aluno.getValidadecc(), aluno.getCodvcc(), aluno.getEmail());
						         
						         if (retorno_liberacao == 1) {
						        	 System.out.println("Mensagem do Servidor Acesso : Acesso processado OK. "); 
						        	 // confirma efetivação torna estado = vigente   codigo 2
						        	 aluno.setEstado(2);
						        	 
						         }
						         else {
						        	 // desfaz operações 
						        	 System.out.println("Erro no modulo que libera Acesso - inscrição cancelada");
						        	 // chama serverNf para cancelar nota
						        	  retorno_nf = stub2.nf_metodo(2, aluno.getNome(), aluno.getValor(), 
							        		 aluno.getCpf(), aluno.getCurso());
						        	 System.out.println("Nota fiscal cancelada");
						        	 // chama serverPgto para estornar pagamento
						        	   retorno_pgto = stub.pgto_metodo(2, aluno.getNomecc(), aluno.getNumcc(),
								        		aluno.getValidadecc(), aluno.getCodvcc());
						        	 System.out.println("Pagamento estornado");
						        	 
						        	 // marca aluno com estado cancelado = 3
						        	 
						        	 aluno.setEstado(3);
						         }
						        				        	 
					         }
					         else {
					        	 System.out.println("Erro no modulo Nota Fiscal - inscrição cancelada");
					        	 aluno.setEstado(3); // cancelado 
					        	 //chamar pgto para estorno.
					        	  retorno_pgto = stub.pgto_metodo(2, aluno.getNomecc(), aluno.getNumcc(),
							        		aluno.getValidadecc(), aluno.getCodvcc());
					        	 System.out.println("Pagamento estornado");
					         }
					         
				         }
				         else {
				        	 System.out.println("Erro no modulo de pagamento - inscrição cancelada");
				        	 aluno.setEstado(3); // cancelado 
				         }
				         
				        
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