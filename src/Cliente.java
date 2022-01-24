import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import entities.Clientes;
public class Cliente {
   public static void main(String[] args) {
      String host = (args.length < 1) ? null : args[0];
      try {
    	  

    	  Clientes  aluno = new Clientes("nome aluno 1", "curso engenharia",
    			  100.00, "11122233344", "nome cartao","1234123412341234",122021, 123);
         // Obtém uma referência para o registro do RMI
         Registry registry = LocateRegistry.getRegistry(host);
 
         // Obtém a stub do servidor
         Pgto stub= (Pgto) registry.lookup("pgto");
         Nf stub2= (Nf) registry.lookup("nf");
         Acesso stub3= (Acesso) registry.lookup("acesso");
 
         // Chama o método do servidor e imprime a mensagem
        String msg = stub.pgto_metodo(aluno.getNomecc(), aluno.getNumcc(),
        		aluno.getValidadecc(), aluno.getCodvcc());
        System.out.println("Mensagem do Servidor: " + msg);
        
        String msg2 = stub2.nf_metodo(aluno.getNome(), aluno.getValor(), aluno.getCpf(), aluno.getCurso());
        System.out.println("Mensagem do Servidor NF : " + msg2); 
        
        String msg3 = stub3.acesso_metodo(aluno.getNome(), aluno.getCurso(), aluno.getValor(),
        		aluno.getCpf(), aluno.getNomecc(), aluno.getNumcc(), 
        		aluno.getValidadecc(), aluno.getCodvcc());
        System.out.println("Mensagem do Servidor acesso : " + msg3); 
        
        
      } catch (Exception ex) {
         ex.printStackTrace();
      } 
   }
}