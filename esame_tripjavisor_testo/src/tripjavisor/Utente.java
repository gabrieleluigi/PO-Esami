package tripjavisor;

import java.util.*;

public class Utente implements Comparable<Utente>{
	
	private String username;
	private List<Recensione> listaRecensioni = new LinkedList<Recensione>();
	private List<Recensione> listaRecensioniValutate = new LinkedList<Recensione>();
	private int punteggio;

	public Utente(String username) {
		this.username = username;
		punteggio = 0;
	}

	public String getUsername() {
		return username;
	}
	
	public void aggiungiValutazioneRecensione(Recensione r) {
		listaRecensioniValutate.add(r);
	}
	
	public void aggiungiRecensione(Recensione r) {
		listaRecensioni.add(r);
	}

	public void aggiornaPunteggio() {
		int miPiaceTot = 0;
		int NONmiPiaceTot = 0;
		for(Recensione r: listaRecensioni) {
			miPiaceTot += r.getMiPiace();
			NONmiPiaceTot += r.getNonMiPiace();
		}
		this.punteggio = miPiaceTot - NONmiPiaceTot;
	}

	public int getPunteggio() {
		return punteggio;
	}

	public List<Recensione> getListaRecensioniValutate() {
		return listaRecensioniValutate;
	}
	
	public List<Recensione> getListaRecensioni() {
		return listaRecensioni;
	}

	@Override
	public int compareTo(Utente u) {
		return -(this.punteggio*1000 - u.getPunteggio()*1000);
	}
	
}
