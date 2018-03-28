package rete;

public class FermataElementi extends Fermata {
	
	private String elemento1;
	private String elemento2;
	
	public FermataElementi(int id, String nome, String elemento1, String elemento2) {
		super(id, nome);
		this.elemento1 = elemento1;
		this.elemento2 = elemento2;
	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	public String ubicazione() {
		return elemento1+" / "+elemento2;
	}
	//--

	public String getElemento1() {
		return elemento1;
	}

	public String getElemento2() {
		return elemento2;
	}
	
	
}
