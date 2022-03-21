package entities;

public class Clientes {
	private String nome;
	private String curso;
	private Double valor;
	private String cpf;
	private String nomecc;
	private String numcc;
	private int validadecc;
	private int codvcc;
	private String email;
	private int estado;  // pendente =1 ; vigente =2; cancelado =3
	public Clientes(String nome, String curso, Double valor, String cpf, String nomecc, String numcc, int validadecc,
			int codvcc, String email, int estado) {
		super();
		this.nome = nome;
		this.curso = curso;
		this.valor = valor;
		this.cpf = cpf;
		this.nomecc = nomecc;
		this.numcc = numcc;
		this.validadecc = validadecc;
		this.codvcc = codvcc;
		this.email = email;
		this.estado = estado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNomecc() {
		return nomecc;
	}
	public void setNomecc(String nomecc) {
		this.nomecc = nomecc;
	}
	public String getNumcc() {
		return numcc;
	}
	public void setNumcc(String numcc) {
		this.numcc = numcc;
	}
	public int getValidadecc() {
		return validadecc;
	}
	public void setValidadecc(int validadecc) {
		this.validadecc = validadecc;
	}
	public int getCodvcc() {
		return codvcc;
	}
	public void setCodvcc(int codvcc) {
		this.codvcc = codvcc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}

}			
	


