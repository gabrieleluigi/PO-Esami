package torneidicalcio;

import java.util.*;

public class Federazione {

	private static int prossimoNumeroTessera=1000;

	
	private String sigla;
	private String denominazione;
	private String sitoWeb;
	
	private TreeMap<String, Squadra> squadre = new TreeMap<String, Squadra>();
	private TreeMap<String, Torneo> mappaTornei = new TreeMap<String, Torneo>();
	private LinkedList<Torneo> listaTornei = new LinkedList<Torneo>();
	TreeMap<Integer, Tesserato> tesserati = new TreeMap<Integer, Tesserato>();
	
	public Federazione(String sigla, String denominazione, String sitoWeb){
		this.sigla = sigla;
		this.denominazione=denominazione;
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
	
		Torneo ttemp = new Torneo(nomeTorneo, numeroSquadre);
		mappaTornei.put(nomeTorneo, ttemp);
		listaTornei.add(ttemp);
		return ttemp;
	}

	public void nuovaSquadra(String nome, String citta, int anno, String stadio){
		if(!squadre.containsKey(nome)){
			Squadra stemp = new Squadra(nome, citta, anno, stadio);
			squadre.put(nome, stemp);
		}
	}

	public Squadra cercaSquadra(String nomeSquadra){
		return squadre.get(nomeSquadra);
	}

	public Collection<Torneo> elencoTornei(){
		return listaTornei;
	}

	public Collection<Squadra> elencoSquadre(){
		return squadre.values();
	}
	
	public void iscriviSquadraTorneo(String nomeTorneo, String nomeSquadra){
		
		Torneo ttemp = mappaTornei.get(nomeTorneo);
		Squadra stemp = squadre.get(nomeSquadra);
		
		if(stemp!=null && ttemp!=null)
			if(ttemp.elencoSquadre().size()<ttemp.getNumeroSquadre())
				ttemp.iscrivi(stemp);
	}

	public Collection<Squadra> elencoSquadreTorneo(String nomeTorneo){
		Torneo ttemp = mappaTornei.get(nomeTorneo);
		return ttemp.elencoSquadre();
	}
	
	public int tesseramento(String nome, String cognome, String nomeSquadra, String ruolo) throws EccezioneErroreDatiTesseramento{

		if(nomeSquadra==null)
			throw new EccezioneErroreDatiTesseramento();
		
		Squadra stemp = squadre.get(nomeSquadra);
		
		if(stemp==null || nome==null || cognome == null || nome.compareTo("")==0 || cognome.compareTo("")==0 || ruolo==null || ruolo.compareTo("")==0 )
			throw new EccezioneErroreDatiTesseramento();

		Tesserato t = new Dirigente(prossimoNumeroTessera++, nome,cognome, stemp, ruolo);
		
		tesserati.put(t.getNumeroTessera(), t);
		
		return t.getNumeroTessera();
	}

	public int tesseramento(String nome, String cognome, String nomeSquadra, String ruolo, int numeroMaglia) throws EccezioneErroreDatiTesseramento{

		if(nomeSquadra==null)
			throw new EccezioneErroreDatiTesseramento();
		
		Squadra stemp = squadre.get(nomeSquadra);
		
		if(stemp==null || nome==null || cognome == null || nome.compareTo("")==0 || cognome.compareTo("")==0 || ruolo==null || ruolo.compareTo("")==0 || numeroMaglia<0)
			throw new EccezioneErroreDatiTesseramento();
		
		Tesserato t = new Calciatore(prossimoNumeroTessera++, nome,cognome, stemp, ruolo, numeroMaglia);

		tesserati.put(t.getNumeroTessera(), t);
		
		return t.getNumeroTessera();

	}
	
	public int tesseramento(String nome, String cognome, String sezione) throws EccezioneErroreDatiTesseramento{

		if(nome==null || cognome == null || sezione==null || sezione.compareTo("")==0 )
			throw new EccezioneErroreDatiTesseramento();
		
		Tesserato t = new Arbitro(prossimoNumeroTessera++, nome,cognome, sezione);
		
		tesserati.put(t.getNumeroTessera(), t);
		
		return t.getNumeroTessera();

	}
	
	
	public Tesserato cercaTesseratoPerNumeroTessera(int numeroTessera) throws EccezioneTesseratoInesistente{
		if(tesserati.containsKey(numeroTessera))
			return tesserati.get(numeroTessera);
		else throw new EccezioneTesseratoInesistente();
	}

	public Tesserato cercaTesseratoPerNomeCognome(String nome, String cognome) throws EccezioneTesseratoInesistente{
		Tesserato ttemp = null;
		
		for(Tesserato t : tesserati.values())
			if(t.getNome().compareTo(nome)==0 && t.getCognome().compareTo(cognome)==0)
			{
				ttemp=t;
				break;
			}

		if(ttemp!=null)
			return ttemp;
		else throw new EccezioneTesseratoInesistente();
	}

	
	
	public Collection<Tesserato> elencoTesseratiSquadra(String nomeSquadra){

		LinkedList<Dirigente> listaDirigenti = new LinkedList<Dirigente>();
		LinkedList<Calciatore> listaCalciatori = new LinkedList<Calciatore>();
		LinkedList<Tesserato> lista = new LinkedList<Tesserato>();
		
		for(Tesserato ttemp : tesserati.values())
			if(ttemp instanceof Dirigente && ((Dirigente)ttemp).getSquadra().getNome().compareTo(nomeSquadra)==0)
				listaDirigenti.add((Dirigente)ttemp);

		for(Tesserato ttemp : tesserati.values())
			if(ttemp instanceof Calciatore && ((Calciatore)ttemp).getSquadra().getNome().compareTo(nomeSquadra)==0)
				listaCalciatori.add((Calciatore)ttemp);

		Collections.sort(listaDirigenti);
		Collections.sort(listaCalciatori);
		
		lista.addAll(listaDirigenti);
		lista.addAll(listaCalciatori);
		
		return lista;
		
	}
	
	public Incontro nuovoIncontro(String nomeTorneo, int giornata, String nomeSquadraCasa, String nomeSquadraOspite, String risultato, String nomeArbitro, String cognomeArbitro){
		Torneo ttemp = mappaTornei.get(nomeTorneo);
		Arbitro atemp =null;
		
		for(Tesserato t : tesserati.values())
			if(t.getNome().compareTo(nomeArbitro)==0 && t.getCognome().compareTo(cognomeArbitro)==0)
			{
				atemp = (Arbitro)t;
				break;
			}
			
		if(ttemp!=null && atemp!=null)
			return ttemp.nuovoIncontro( giornata,  nomeSquadraCasa,  nomeSquadraOspite,  risultato, atemp);
		else
			return null;
		
	}

	
	public Collection<Incontro> elencoIncontriPerGiornata(String nomeTorneo){

		Torneo ttemp = mappaTornei.get(nomeTorneo);

		if(ttemp!=null)
			return ttemp.elencoIncontriPerGiornata();
		else
			return null;
	}


	public Collection<Incontro> elencoIncontriPerDifferenzaReti(String nomeTorneo){

		Torneo ttemp = mappaTornei.get(nomeTorneo);

		if(ttemp!=null)
			return ttemp.elencoIncontriPerDifferenzaReti();
		else
			return null;
	}

	public int puntiSquadra(String nomeSquadra){
		for(Torneo t : listaTornei)
			for(Squadra s : t.elencoSquadre())
				if(s.getNome().compareTo(nomeSquadra)==0)
					return t.puntiSquadra(nomeSquadra);
		return -1;
	}
	
	
	
	public String classificaTorneo(String nomeTorneo){
		Torneo ttemp = null;
		for(Torneo t : listaTornei)
			if(t.getNome().compareTo(nomeTorneo)==0){
				ttemp = t;
				break;
			}
		if(ttemp!=null)
			return ttemp.classifica();
		else
			return null;

	}
}
