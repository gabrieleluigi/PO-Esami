package tripjavisor;

import java.util.LinkedList;
import java.util.List;

public class Hotel implements Comparable<Hotel>{
	
	private int codice;
	private String nome;
	private String indirizzo;
	private String citta;
	private int numCamere;
	private List<Recensione> listaRecensioniHotel = new LinkedList<Recensione>();
	private double mediaVoti;
	
	public Hotel(int codice, String nome, String indirizzo, String citta, int numCamere) {
		this.codice = codice;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.numCamere = numCamere;
	}

	public int getCodice() {
		return codice;
	}

	public String getNome() {
		return nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public String getCitta() {
		return citta;
	}

	public int getNumCamere() {
		return numCamere;
	}
	
	//--
	
	public double getMediaVoti() {
		return mediaVoti;
	}

	public List<Recensione> getListaRecensioniHotel() {
		return listaRecensioniHotel;
	}
	
	public void aggiungiRecensione(Recensione r) {
		listaRecensioniHotel.add(r);
		aggiornaMediaVoti();
	}

	private void aggiornaMediaVoti() {
		double somma = 0;
		for(Recensione r: listaRecensioniHotel)
			somma += r.getVoto();
		this.mediaVoti = (somma/listaRecensioniHotel.size());
	}

	@Override
	public int compareTo(Hotel h) {
		return - ((int) (this.getMediaVoti()*1000 - h.getMediaVoti()*1000));
	}
}
