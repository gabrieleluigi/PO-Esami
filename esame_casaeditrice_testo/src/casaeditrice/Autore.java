package casaeditrice;

import java.util.*;

public class Autore implements Comparable<Autore>{
	
	private int codice;
	private String nome;
	private String cognome;
	private String email;
	List<Pubblicazione> pubblicazioniLista = new LinkedList<Pubblicazione>();

	public Autore(int codice, String nome, String cognome, String email) {
		this.codice = codice;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
	}

	public String getNome(){
		return nome;
	}

	public String getCognome(){
		return cognome;
	}

	public String getEmail(){
		return email;
	}
	
	public int getCodice(){
		return codice;
	}
	
	public Pubblicazione aggiungiPubblicazione(String titolo, char tipologia, String volume, int anno, int contributo) {
		Pubblicazione p = null;
		if(tipologia=='R')
			p = new Rivista(titolo, volume, anno);
		else if(tipologia=='C')
			p = new Conferenza(titolo, volume, anno);
		p.setProprietario(this,contributo);
		pubblicazioniLista.add(p);
		return p;
	}
	
	public Collection<Pubblicazione> elencoPubblicazioni(){
		Collections.sort(pubblicazioniLista);
		return pubblicazioniLista;
	}

	@Override
	public int compareTo(Autore o) {
		if(this.getCognome().compareTo(o.getCognome()) == 0)
			return this.getNome().compareTo(o.getNome());
		return this.getCognome().compareTo(o.getCognome());
	}
	
}
