package torneidicalcio;

import java.util.*;

public class Federazione {
	
	private String sigla;
	private String denominazione;
	private String sitoWeb;
	private int NUMERO_TESSERA = 1000;
	
	private Map<String, Torneo> mappaTornei = new HashMap<String, Torneo>();
	private List<Torneo> listaTornei = new LinkedList<Torneo>();
	private Map<String, Squadra> mappaSquadre = new TreeMap<String, Squadra>();
	private Map<Integer, Tesserato> mappaTesserati = new HashMap<Integer, Tesserato>();

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
		
		if(mappaTornei.containsKey(nomeTorneo))
			return mappaTornei.get(nomeTorneo);
		
		Torneo t = new Torneo(nomeTorneo,numeroSquadre);
		mappaTornei.put(nomeTorneo,t);
		listaTornei.add(t);
		return mappaTornei.get(nomeTorneo);
	}

	public void nuovaSquadra(String nome, String citta, int anno, String stadio){
		if(cercaSquadra(nome) == null) {
			Squadra s = new Squadra(nome, citta, anno, stadio);
			mappaSquadre.put(nome, s);
		}
	}

	public Squadra cercaSquadra(String nomeSquadra){
		if(mappaSquadre.containsKey(nomeSquadra))
			return mappaSquadre.get(nomeSquadra);
		return null;
	}

	public Collection<Torneo> elencoTornei(){
		return listaTornei;
	}

	public Collection<Squadra> elencoSquadre(){
		/*List<Squadra> listaSquadreOdinata = new LinkedList<Squadra>();
		listaSquadreOdinata = (List<Squadra>) mappaSquadre.values();
		Collections.sort(listaSquadreOdinata, new SquadreComparator());
		return listaSquadreOdinata;*/
		return mappaSquadre.values();
	}
	
	public void iscriviSquadraTorneo(String nomeTorneo, String nomeSquadra){
		if(cercaSquadra(nomeSquadra) != null && mappaTornei.containsKey(nomeTorneo)) {
			mappaTornei.get(nomeTorneo).aggiungiSquara(cercaSquadra(nomeSquadra));
		}
	}

	public Collection<Squadra> elencoSquadreTorneo(String nomeTorneo){
		if(mappaTornei.containsKey(nomeTorneo))
			return mappaTornei.get(nomeTorneo).elencoSquadre();
		return null;
	}
	
	public int tesseramento(String nome, String cognome, String nomeSquadra, String ruolo) throws EccezioneErroreDatiTesseramento{
		if (nome == null || cognome == null || nomeSquadra == null || ruolo == null || cercaSquadra(nomeSquadra) == null)
			throw new EccezioneErroreDatiTesseramento();
		Squadra s = cercaSquadra(nomeSquadra);
		Tesserato t = new Dirigente(nome, cognome, s, ruolo);
		mappaTesserati.put(NUMERO_TESSERA, t);
		NUMERO_TESSERA++;
		return (NUMERO_TESSERA-1);

	}

	public int tesseramento(String nome, String cognome, String nomeSquadra, String ruolo, int numeroMaglia) throws EccezioneErroreDatiTesseramento{
		if (nome == null || cognome == null || nomeSquadra == null || ruolo == null || numeroMaglia < 0 || cercaSquadra(nomeSquadra) == null)
			throw new EccezioneErroreDatiTesseramento();
		Squadra s = cercaSquadra(nomeSquadra);
		Tesserato t = new Calciatore(nome, cognome, s, ruolo, numeroMaglia);
		mappaTesserati.put(NUMERO_TESSERA, t);
		NUMERO_TESSERA++;
		return (NUMERO_TESSERA-1);
	}
	
	public int tesseramento(String nome, String cognome, String sezione) throws EccezioneErroreDatiTesseramento{
		if (nome == null || cognome == null || (sezione.compareTo("") == 0))
			throw new EccezioneErroreDatiTesseramento();
		Tesserato t = new Arbitro(nome, cognome, sezione);
		mappaTesserati.put(NUMERO_TESSERA, t);
		NUMERO_TESSERA++;
		return (NUMERO_TESSERA-1);
	}
	
	public Tesserato cercaTesseratoPerNumeroTessera(int numeroTessera) throws EccezioneTesseratoInesistente{
		if(mappaTesserati.get(numeroTessera)==null)
			throw new EccezioneTesseratoInesistente();
		return mappaTesserati.get(numeroTessera);
	}

	public Tesserato cercaTesseratoPerNomeCognome(String nome, String cognome) throws EccezioneTesseratoInesistente{
		for(Tesserato t: mappaTesserati.values())
			if(nome.compareTo(t.getNome())==0 && cognome.compareTo(t.getCognome())==0)
				return t;
		throw new EccezioneTesseratoInesistente();
	}
	
	public Collection<Tesserato> elencoTesseratiSquadra(String nomeSquadra){
		List<Tesserato> listaTesserati = new LinkedList<Tesserato>();
		for(Tesserato t: mappaTesserati.values()) {
			if (t instanceof Dirigente) {
				if(nomeSquadra.compareTo(((Dirigente) t).getSquadra().getNome()) == 0) {
					listaTesserati.add(t);
				}
			}
			else if (t instanceof Calciatore) {
				if(nomeSquadra.compareTo(((Calciatore) t).getSquadra().getNome()) == 0) {
					listaTesserati.add(t);
				}
			}
		}
		Collections.sort(listaTesserati, new TesseratiSquadraComparator());
		return listaTesserati;
	}
	
	public Incontro nuovoIncontro(String nomeTorneo, int giornata, String nomeSquadraCasa, String nomeSquadraOspite, String risultato, String nomeArbitro, String cognomeArbitro){
		Squadra sC = cercaSquadra(nomeSquadraCasa);
		Squadra sO = cercaSquadra(nomeSquadraOspite);
		Tesserato t;
		try {
			t = cercaTesseratoPerNomeCognome(nomeArbitro,cognomeArbitro);
		} catch (EccezioneTesseratoInesistente e) {
			return null;
		}
		if(mappaTornei.containsKey(nomeTorneo) && sC !=null && sO!= null && t instanceof Arbitro) {
			 String[] r = risultato.split("-");
			 int r1 = Integer.parseInt(r[0]); 
			 int r2 = Integer.parseInt(r[1]);
			 Incontro i = new Incontro(giornata, sC, sO, r1, r2, (Arbitro) t);
			 return i;
		}
		return null;
	}

	public Collection<Incontro> elencoIncontriPerGiornata(String nomeTorneo){
		if(mappaTornei.containsKey(nomeTorneo))
			return mappaTornei.get(nomeTorneo).getIncontri();
		return null;
	}

	public Collection<Incontro> elencoIncontriPerDifferenzaReti(String nomeTorneo){
		//vedi torneidicalcio_soluzione che mette ilcomaratore nella classe
		return null;
	}

	public int puntiSquadra(String nomeSquadra){
		return -1;
	}
	
	public String classificaTorneo(String nomeTorneo){
		return null;
	}
}
