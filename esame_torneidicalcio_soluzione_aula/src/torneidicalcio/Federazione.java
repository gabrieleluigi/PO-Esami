package torneidicalcio;

import java.util.*;

public class Federazione {
	private String sigla;
	private String denominazione;
	private String sitoWeb;
	
	private int prossimoNumeroTessera = 1000;
	private Map<String, Torneo> mappaTornei = new HashMap<String, Torneo>();
	private List<Torneo> listaTornei = new LinkedList<Torneo>();
	private Map<String, Squadra> mappaSquadre = new TreeMap<String, Squadra>();
	private Map<Integer, Tesserato> tesserati = new HashMap<Integer, Tesserato>(); 
	private Map<String, Tesserato> tesseratiOrdinati = new TreeMap<String, Tesserato>(); // non HashMap, bensi' TreeMap, per ordinare

	public Federazione(String sigla, String denominazione, String sitoWeb){
		this.sigla = sigla;
		this.denominazione = denominazione;
		this.sitoWeb = sitoWeb;
	}
	
	public String getSigla() {
		return sigla;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public String getSitoWeb() {
		return sitoWeb;
	}

	public Torneo nuovoTorneo(String nomeTorneo, int numeroSquadre){
		if (mappaTornei.containsKey(nomeTorneo)){
			return mappaTornei.get(nomeTorneo);
		}
		
		Torneo t = new Torneo(nomeTorneo, numeroSquadre);
		mappaTornei.put(nomeTorneo, t);
		listaTornei.add(t);
		return t;
	}

	public void nuovaSquadra(String nome, String citta, int anno, String stadio){
		if (!mappaSquadre.containsKey(nome)){ // (mappaSquadre.containsKey(nome) == false)
			Squadra s = new Squadra(nome, citta, anno, stadio);
			mappaSquadre.put(nome, s);
		}
	}

	public Squadra cercaSquadra(String nomeSquadra){
		return mappaSquadre.get(nomeSquadra);
	}

	public Collection<Torneo> elencoTornei(){
		return listaTornei;
	}

	public Collection<Squadra> elencoSquadre(){
		return mappaSquadre.values();
	}
	
	public void iscriviSquadraTorneo(String nomeTorneo, String nomeSquadra){
		// ottengo il torneo
		Torneo t = mappaTornei.get(nomeTorneo);
		
		// ottengo la squadra
		Squadra s = cercaSquadra(nomeSquadra);
		
		if (s!= null && t!= null){
			// aggiungo la squadra al torneo
			t.iscriviSquadra(s);

			
		}
		
	}

	public Collection<Squadra> elencoSquadreTorneo(String nomeTorneo){
		Torneo t = mappaTornei.get(nomeTorneo);
		
		if (t!= null)
			return t.getMappaSquadre().values();
		
		return null;
	}
	
	public int tesseramento(String nome, String cognome, String nomeSquadra, String ruolo) throws EccezioneErroreDatiTesseramento{
		if (nome != null && cognome != null && nomeSquadra != null && ruolo != null){
			//ottengo la squadra dato il nome
			Squadra s = cercaSquadra(nomeSquadra);
			
			if (s != null){
				Tesserato t = new Dirigente(prossimoNumeroTessera, nome, cognome, s, ruolo);
				tesserati.put(prossimoNumeroTessera, t);
				tesseratiOrdinati.put(cognome+"_"+nome, t);
				prossimoNumeroTessera++;
				return (prossimoNumeroTessera-1);
			}
		}
		
		return -1;
	}

	public int tesseramento(String nome, String cognome, String nomeSquadra, String ruolo, int numeroMaglia) throws EccezioneErroreDatiTesseramento{
		if (nome != null && cognome != null && nomeSquadra != null && ruolo != null && numeroMaglia != -1){
			//ottengo la squadra dato il nome
			Squadra s = cercaSquadra(nomeSquadra);
			
			if (s != null){
				Tesserato t = new Calciatore(prossimoNumeroTessera, nome, cognome, s, ruolo, numeroMaglia);
				tesserati.put(prossimoNumeroTessera, t);
				tesseratiOrdinati.put(cognome+"_"+nome, t);
				prossimoNumeroTessera++;
				return (prossimoNumeroTessera-1);
			}
		}
		
		return -1;
	}
	
	public int tesseramento(String nome, String cognome, String sezione) throws EccezioneErroreDatiTesseramento{
		if (nome != null && cognome != null && sezione != null){
			Tesserato t = new Arbitro(prossimoNumeroTessera, nome, cognome, sezione);
			tesserati.put(prossimoNumeroTessera, t);
			tesseratiOrdinati.put(cognome+"_"+nome, t);
			prossimoNumeroTessera++;
			return (prossimoNumeroTessera-1);
		}
		return -1;
	}
	
	public Tesserato cercaTesseratoPerNumeroTessera(int numeroTessera) throws EccezioneTesseratoInesistente{
		return tesserati.get(numeroTessera);
	}

	public Tesserato cercaTesseratoPerNomeCognome(String nome, String cognome) throws EccezioneTesseratoInesistente{
		for (Tesserato t : tesserati.values()){
			if (t.getNome().compareTo(nome) == 0 && t.getCognome().compareTo(cognome) == 0)
				return t;
		}
		
		return null;
	}
	
	public Collection<Tesserato> elencoTesseratiSquadra(String nomeSquadra){
		List<Tesserato> lt = new LinkedList<Tesserato>();
		
		for (Tesserato t : tesseratiOrdinati.values()){
			if (t instanceof Dirigente){
				if (((Dirigente) t).getSquadra().getNome().compareTo(nomeSquadra) ==0 )
					lt.add(t);
			}
		}
	
		for (Tesserato t : tesseratiOrdinati.values()){
			if (t instanceof Calciatore){
				if (((Calciatore) t).getSquadra().getNome().compareTo(nomeSquadra) ==0 )
					lt.add(t);
			}
		}


		return lt;
	}
	
	public Incontro nuovoIncontro(String nomeTorneo, int giornata, String nomeSquadraCasa, String nomeSquadraOspite, String risultato, String nomeArbitro, String cognomeArbitro){
		return null;
	}

	public Collection<Incontro> elencoIncontriPerGiornata(String nomeTorneo){
		return null;
	}

	public Collection<Incontro> elencoIncontriPerDifferenzaReti(String nomeTorneo){
		return null;
	}

	public int puntiSquadra(String nomeSquadra){
		return -1;
	}
	
	public String classificaTorneo(String nomeTorneo){
		return null;
	}
}
