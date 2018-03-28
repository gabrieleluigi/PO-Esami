package aziendaagricola;

public class Fertilizzante {

	private String nome;
	private float costo;
	private int periodicita;
	private int quantitaImpiego;

	public Fertilizzante (String nome, float costo, int periodicita, int quantitaImpiego){
		this.nome=nome;
		this.costo=costo;
		this.periodicita=periodicita;
		this.quantitaImpiego=quantitaImpiego;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getCosto() {
		return costo;
	}
	public void setCosto(float costo) {
		this.costo = costo;
	}
	public int getPeriodicita() {
		return periodicita;
	}
	public void setPeriodicita(int periodicita) {
		this.periodicita = periodicita;
	}
	public int getQuantitaImpiego() {
		return quantitaImpiego;
	}
	public void setQuantitaImpiego(int quantitaImpiego) {
		this.quantitaImpiego = quantitaImpiego;
	}
	
	
	
	
}
