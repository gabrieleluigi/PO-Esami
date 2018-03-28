package ecommerce;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Sito {

	private Map<String, Categoria> mappaCategorie = new TreeMap<String, Categoria>();
	private List<Categoria> listaCategorie = new LinkedList<Categoria>();
	private Map<String, Prodotto> mappaProdotti = new TreeMap<String, Prodotto>();
	private List<Prodotto> listaProdotti = new LinkedList<Prodotto>();
	private int CODICE_PRODOTTO = 100;
	private Map<Integer, Utente> mappaUtentiCodice = new TreeMap<Integer, Utente>();
	private Map<String, Utente> mappaUtentiEmail = new TreeMap<String, Utente>();
	private List<Utente> listaUtenti = new LinkedList<Utente>();
	private int CODICE_UTENTE = 1;
	
	public void nuovaCategoria(String nomeCategoria){
		Categoria c = new Categoria(nomeCategoria);
		mappaCategorie.put(nomeCategoria, c);
		listaCategorie.add(c);
	}

	public Collection<String> elencoCategorie(){
		return mappaCategorie.keySet();
	}
	
	public String nuovoProdotto(String nomeCategoria, String nomeProdotto, String descrizione, double prezzo){
		if(mappaCategorie.containsKey(nomeCategoria) == false)
			nuovaCategoria(nomeCategoria);
		String c = Integer.toString(CODICE_PRODOTTO);
		String z = "00";
		if(c.length() == 4)
			z = "0";
		else if(c.length() == 5)
			z = "";
		String key = nomeCategoria.substring(0,1).toUpperCase() + z + c;
		Prodotto p = new Prodotto(key, nomeProdotto, descrizione, prezzo);
		mappaProdotti.put(key, p);
		listaProdotti.add(p);
		CODICE_PRODOTTO++;
		return key;
	}
	
	public Prodotto cercaProdotto(String stringaRicerca){
		for(Prodotto p: listaProdotti)
			if(p.getDescrizione().toLowerCase().contains(stringaRicerca.toLowerCase()) ||
					p.getCodice().toLowerCase().contains(stringaRicerca.toLowerCase()) ||
					p.getNome().toLowerCase().contains(stringaRicerca.toLowerCase()))
				return p;
		return null;
	}
	
	public Collection<Prodotto> elencoProdottiPerNome(){
		Collections.sort(listaProdotti, new elencoProdottiPerNomeComparator());
		return listaProdotti;
	}

	public Collection<Prodotto> elencoProdottiPerPrezzo(){
		Collections.sort(listaProdotti, new elencoProdottiPerPrezzoComparator());
		return listaProdotti;
	}
	
	public Collection<Prodotto> elencoProdottiPerNome(String nomeCategoria){
		List<Prodotto> ltemp = new LinkedList<Prodotto>(elencoProdottiPerNome());
		List<Prodotto> mtemp = new LinkedList<Prodotto>();
		for(Prodotto p: ltemp)
			if(p.getCodice().substring(0,1).toLowerCase().compareTo(nomeCategoria.substring(0,1).toLowerCase()) == 0)
				mtemp.add(p);
		return mtemp;
	}

	public Collection<Prodotto> elencoProdottiPerPrezzo(String nomeCategoria){
		List<Prodotto> ltemp = new LinkedList<Prodotto>(elencoProdottiPerPrezzo());
		List<Prodotto> mtemp = new LinkedList<Prodotto>();
		for(Prodotto p: ltemp)
			if(p.getCodice().substring(0,1).toLowerCase().compareTo(nomeCategoria.substring(0,1).toLowerCase()) == 0)
				mtemp.add(p);
		return mtemp;
	}
	
	public void nuovoUtente(String nome, String cognome, String email, String indirizzo){
		if(mappaUtentiEmail.containsKey(email) == false) {
			Utente u = new Utente(CODICE_UTENTE, nome, cognome, email, indirizzo);
			mappaUtentiCodice.put(CODICE_UTENTE, u);
			mappaUtentiEmail.put(email, u);
			listaUtenti.add(u);
			CODICE_UTENTE++;
		}
	}
	
	public void nuovoUtente(String nome, String cognome, String email, String indirizzo, String username, String password){
		if(mappaUtentiEmail.containsKey(email) == false) {
			Utente u = new UtenteRegistrato(CODICE_UTENTE, nome, cognome, email, indirizzo, username, password);
			mappaUtentiCodice.put(CODICE_UTENTE, u);
			mappaUtentiEmail.put(email, u);
			listaUtenti.add(u);
			CODICE_UTENTE++;
		}
	}
	
	public Utente cercaUtente(int codiceUtente) throws EccezioneUtenteInesistente{
		if(mappaUtentiCodice.containsKey(codiceUtente) == false)
			throw new EccezioneUtenteInesistente();
		return mappaUtentiCodice.get(codiceUtente);
	}
	
	public String dettagliCarrello(int codiceUtente) throws EccezioneUtenteInesistente{
		cercaUtente(codiceUtente);
		Map<String, String> riga = new TreeMap<String, String>();
		String s = "";
		int q;
		for(Prodotto p1: mappaUtentiCodice.get(codiceUtente).getListaCarrelloND()) {
			q = 0;
			for(Prodotto p2: mappaUtentiCodice.get(codiceUtente).getListaCarrello())
				if(p1.equals(p2)) 
					q++;	
			riga.put(p1.getCodice(), p1.getCodice() + " " + p1.getPrezzo() + " " + q);
		}
		for(String t: riga.values())
				s += t+ "\n";
		if(s.compareTo("") != 0)
			s = s.substring(0, s.length() - 1);
		return s;
	}
	
    public void leggiFile(String file) throws IOException{
    		BufferedReader br = new BufferedReader(new FileReader(file));
		String line; 
		while ((line = br.readLine()) != null){
			String [] array = line.split(";");
			if (array[0].trim().compareTo("P") == 0) {
				if(mappaCategorie.containsKey(array[1].trim()) == false)
					nuovaCategoria(array[1].trim());
				nuovoProdotto(array[1].trim(), array[2].trim(), array[3].trim(), Double.parseDouble(array[4].trim()));
			}
			if (array[0].trim().compareTo("U") == 0) {
				if (array.length > 5)
					nuovoUtente(array[1].trim(), array[2].trim(), array[3].trim(), array[4].trim(), array[5].trim(), array[6].trim());
				nuovoUtente(array[1].trim(), array[2].trim(), array[3].trim(), array[4].trim());
			}
		}
    }
}
