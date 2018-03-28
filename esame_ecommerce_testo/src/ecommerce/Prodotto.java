package ecommerce;

public class Prodotto {
	
	private String codice;
	private String nome;
	private String descrizione;
	private double prezzo;

	public Prodotto(String codice, String nome, String descrizione, double prezzo) {
		this.codice = codice;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
	}

	public String getCodice() {
		return codice;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public double getPrezzo() {
		return prezzo;
	}
	
}


