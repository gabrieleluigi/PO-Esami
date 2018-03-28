package rete;

public class FermataElemento extends Fermata {
	
	private String elemento;
	private int numeroCivico;
	
	public FermataElemento(int id, String nome, String elemento, int numeroCivico) {
		super(id, nome);
		this.elemento = elemento;
		this.numeroCivico = numeroCivico;

	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public String ubicazione() {
		return elemento+" "+numeroCivico;
	}

	//----
	public String getElemento() {
		return elemento;
	}
	

}
