package agenziaviaggi;

import java.util.*;

public class Pratica implements Comparable<Pratica>{
	private int idPratica;
	private String descrizione;
	private Cliente cliente;
	private List<Prenotazione> prenotazioni = new LinkedList<Prenotazione>();

	public Pratica(int idPratica, String descrizione, Cliente cliente) {
		this.idPratica = idPratica;
		this.descrizione = descrizione;
		this.cliente = cliente;
	}

	public int getIdPratica(){
		return idPratica;
	}
	
	public String getDescrizione(){
		return descrizione;
	}
	
	public void aggiungiPrenotazione(Prenotazione prenotazione){
		prenotazioni.add(prenotazione);
	}
	
	public double getImportoTotale(){
		double totale = 0.0;
		
		for (Prenotazione p : prenotazioni)
			totale += p.getImporto();
			
		return totale;
	}
	
	public Collection<Prenotazione> elencoPrenotazioniPerImporto()
	{
		Collections.sort(prenotazioni, new PrenotazioneImportoComparator());
		return prenotazioni;
	}

	public Collection<Prenotazione> elencoPrenotazioniPerData()
	{
		Collections.sort(prenotazioni, new PrenotazioneDataComparator());
		return prenotazioni;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public int compareTo(Pratica o) {
		//ordina per importo decrescente
		return - (int) (this.getImportoTotale()-o.getImportoTotale());
	}
	
	
}
