package aziendaagricola;

public class Fertilizzante {

	private String nome;
	private float costo;
	private int periodicita;
	private int quantitaImpiego;
	
	public Fertilizzante(String nome, float costo, int periodicita, int quantitaImpiego) {
		this.nome = nome;
		this.costo = costo;
		this.periodicita = periodicita;
		this.quantitaImpiego = quantitaImpiego;
	}

	public String getNome() {
		return nome;
	}
	
	public float getCosto() {
		return costo;
	}
	
	public int getPeriodicita() {
		return periodicita;
	}
	
	public int getQuantitaImpiego() {
		return quantitaImpiego;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCosto(float costo) {
		this.costo = costo;
	}
	
	public void setPeriodicita(int periodicita) {
		this.periodicita = periodicita;
	}

	public void setQuantitaImpiego(int quantitaImpiego) {
		this.quantitaImpiego = quantitaImpiego;
	}
	
}
