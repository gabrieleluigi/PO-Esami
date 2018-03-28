package ecommerce;

import java.util.LinkedList;
import java.util.List;

public class Utente {
	
	private int codice;
	
	private String nome;
	private String cognome;
	private String email;
	private String indirizzo;
	protected List<Prodotto> listaCarrello = new LinkedList<Prodotto>();
	protected List<Prodotto> listaCarrelloND = new LinkedList<Prodotto>();
	protected double totCarrello;

	public Utente(int codice, String nome, String cognome, String email, String indirizzo) {
		this.codice = codice;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.indirizzo = indirizzo;
		totCarrello = 0;
	}

	public int getCodice() {
		return codice;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getEmail() {
		return email;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void selezionaProdotto(Prodotto prodotto){
		listaCarrello.add(prodotto);
		if(listaCarrelloND.contains(prodotto) == false)
			listaCarrelloND.add(prodotto);
		totCarrello += prodotto.getPrezzo();
	}
	
	public List<Prodotto> getListaCarrello() {
		return listaCarrello;
	}

	public List<Prodotto> getListaCarrelloND() {
		return listaCarrelloND;
	}

	public double paga(String data){
		listaCarrello.clear();
		listaCarrelloND.clear();
		double d = totCarrello;
		totCarrello = 0;
		return d;
	}

}
