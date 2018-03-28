package rete;

public class Fermata {
	
	protected int id;
	protected String nome;
	private int numerolinee = 0;

	public Fermata(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public int getId() {
		return -1;

	}

	public String getNome() {
		return null;

	}

	public String ubicazione() {
		return null;
	}
	
	//--
	public void incrementaNumeroLinee() {
		numerolinee++;
	}
	public int getNumerolinee() {
		return numerolinee;
	}
	
}
