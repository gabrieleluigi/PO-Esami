package aziendaagricola;

public class Raccolto implements Comparable<Raccolto>{

	private double prezzoIngrosso;
	private String prodotto;
	private String data;
	private int quantita;
	
	
	public Raccolto(String prodotto, String data, int quantita){
		this.prodotto=prodotto;
		this.data=data;
		this.quantita=quantita;
	}
	
	public double getPrezzoIngrosso() {
		return prezzoIngrosso;
	}

	public void setPrezzoIngrosso(int prezzoIngrosso) {
		this.prezzoIngrosso = prezzoIngrosso;
	}
	
	public String getProdotto() {
		return prodotto;
	}

	public void setProdotto(String prodotto) {
		this.prodotto = prodotto;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	

	@Override
	public int compareTo(Raccolto r) {

		if(this.data.compareTo(r.getData())!=0)
			return this.data.compareTo(r.getData());
		else {
			return (this.quantita-r.getQuantita());
		}
			
					
	}
	
	
	
}
