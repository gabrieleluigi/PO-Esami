package tripjavisor;

import java.util.*;

public class Sistema {
	
	private int CODICE_HOTEL = 1;
	private int CODICE_RECENSIONE = 100;
	
	private Map<Integer, Hotel> mappaHotel = new TreeMap<Integer, Hotel>();
	private List<Hotel> listaHotel = new LinkedList<Hotel>();
	private Map<String, Utente> mappaUtenti = new TreeMap<String, Utente>();
	private List<Utente> listaUtenti = new LinkedList<Utente>();
	private Map<Integer, Recensione> mappaRecensioni = new TreeMap<Integer, Recensione>();
	private List<Recensione> listaRecensioni = new LinkedList<Recensione>();


	public Hotel aggiungiHotel(String nome, String indirizzo, String citta, int numCamere) throws HotelGiaPresenteException{
		for(Hotel l: listaHotel)
			if(nome.compareTo(l.getNome()) == 0 && indirizzo.compareTo(l.getIndirizzo()) == 0 && citta.compareTo(l.getCitta()) == 0)
				throw new HotelGiaPresenteException();
		
		Hotel h = new Hotel(CODICE_HOTEL, nome, indirizzo, citta, numCamere);
		listaHotel.add(h);
		mappaHotel.put(CODICE_HOTEL, h);
		CODICE_HOTEL++;
		return h;
	}

	public Hotel aggiungiHotelPremium(String nome, String indirizzo, String citta, int numCamere, String numeroTelefono, 
			String sitoInternet, String email) 
			throws HotelGiaPresenteException{
		for(Hotel l: listaHotel)
			if(nome.compareTo(l.getNome()) == 0 && indirizzo.compareTo(l.getIndirizzo()) == 0 && citta.compareTo(l.getCitta()) == 0)
				throw new HotelGiaPresenteException();
		
		Hotel h = new HotelPremium(CODICE_HOTEL, nome, indirizzo, citta, numCamere, numeroTelefono, sitoInternet, email);
		listaHotel.add(h);
		mappaHotel.put(CODICE_HOTEL, h);
		CODICE_HOTEL++;
		return h;
	}
	
	public Hotel cercaHotel(int codice){
		if(mappaHotel.containsKey(codice))
			return mappaHotel.get(codice);
		return null;
	}

	public Collection<Hotel> elencoHotelInOrdineDiInserimento(){
		return listaHotel;
	}
	
	public Utente aggiungiUtente(String username){
		if(mappaUtenti.containsKey(username))
			return mappaUtenti.get(username);
		Utente u = new Utente(username);
		mappaUtenti.put(username, u);
		listaUtenti.add(u);
		return u;		
	}
	
	public Collection<Utente> elencoUtentiInOrdineAlfabetico(){
		return mappaUtenti.values();
	}
	
	public Utente cercaUtente(String username){
		if(mappaUtenti.containsKey(username))
			return mappaUtenti.get(username);
		return null;
	}
	
	public Recensione aggiungiRecensione(String data, String titolo, String testo, double voto, String username, int codiceHotel){
		for(Recensione r: listaRecensioni)
			if(data.compareTo(r.getData()) == 0 && username.compareTo(r.getUtente().getUsername()) == 0 && codiceHotel == r.getHotel().getCodice()) {
				r.setTitolo(titolo);
				r.setTesto(testo);
				r.setVoto(voto);
				return r;
			}
		Recensione r = new Recensione(CODICE_RECENSIONE, data, titolo, testo, voto, cercaUtente(username), cercaHotel(codiceHotel));
		mappaRecensioni.put(CODICE_RECENSIONE, r);
		listaRecensioni.add(r);
		mappaHotel.get(codiceHotel).aggiungiRecensione(r);
		mappaUtenti.get(username).aggiungiRecensione(r);
		CODICE_RECENSIONE++;
		return r;
	}

	public Recensione cercaRecensione(String stringa){
		for(Recensione r: listaRecensioni)
			if(r.getTitolo().contains(stringa) || r.getTesto().contains(stringa))
				return r;
		return null;
	}

	public int calcolaNumeroRecensioniGiornaliero(String data){
		int cont = 0;
		for(Recensione r: listaRecensioni)
			if(r.getData().compareTo(data) == 0)
				cont++;
		return cont;
	}
	
	public int calcolaNumeroRecensioniUtente(String username){
		if(mappaUtenti.containsKey(username) == false)
			return 0;
		
		int cont = 0;
		for(Recensione r: listaRecensioni)
			if(r.getUtente().getUsername().compareTo(username) == 0)
				cont++;
		return cont;
	}

	public double calcolaMediaVotiRecensioniHotel(int codiceHotel){
		Hotel h = cercaHotel(codiceHotel);
		if(h == null || h.getMediaVoti() == 0.0)
			return 0.0;
		return h.getMediaVoti();
	}
	
	public Collection<Hotel> elencoHotelCittaPerMediaVotiRecensioniDecrescenti(String citta){
		List<Hotel> listaHotelCitta = new LinkedList<Hotel>();
		for(Hotel h: listaHotel)
			if(h.getCitta().compareTo(citta) == 0)
				listaHotelCitta.add(h);
		Collections.sort(listaHotelCitta);
		return listaHotelCitta;
	}
	
	public Collection<Hotel> elencoHotelPerMediaVotiRecensioniDecrescenti(){
		Collections.sort(listaHotel);
		return listaHotel;
	}
	
	public void miPiaceARecensione(int codiceRecensione, String username){
		Utente u = cercaUtente(username);
		Recensione r = mappaRecensioni.get(codiceRecensione);
		if(u.getListaRecensioniValutate().contains(r) == false && r.getUtente().getUsername().compareTo(username) != 0) {
			r.incrmetaMiPiace();
			u.aggiungiValutazioneRecensione(r);
			System.out.println(""+r.getCodice()+" +1 mipiace");
		}
	}
	
	public void nonMiPiaceARecensione(int codiceRecensione, String username){
		Utente u = cercaUtente(username);
		Recensione r = mappaRecensioni.get(codiceRecensione);
		if(u.getListaRecensioniValutate().contains(r) == false && r.getUtente().getUsername().compareTo(username) != 0) {
			r.incrmetaNonMiPiace();
			u.aggiungiValutazioneRecensione(r);
			System.out.println(""+r.getCodice()+" +1 NONmipiace");
		}
	}
	
	public int calcolaPunteggioUtente(String username){
		Utente u = cercaUtente(username);
		u.aggiornaPunteggio();
		return u.getPunteggio();
	}

	public Collection<Utente> elencoUtentiPerPunteggioDecrescente(){
		for(Utente u: listaUtenti)
			u.aggiornaPunteggio();
		Collections.sort(listaUtenti);
		return listaUtenti;
	}
}
