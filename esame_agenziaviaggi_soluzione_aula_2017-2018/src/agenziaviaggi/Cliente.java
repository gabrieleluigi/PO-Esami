package agenziaviaggi;

import java.util.*;

public class Cliente implements Comparable<Cliente>{
	private String cognome;
	private String nome;
	private String indirizzo;
	private List<String> contatti = new LinkedList<String>();
	private Map<Integer, Pratica> pratiche = new HashMap<Integer, Pratica>();
	
	public Cliente(String cognome, String nome, String indirizzo) {
		this.cognome = cognome;
		this.nome = nome;
		this.indirizzo = indirizzo;
	}

	public String getCognome(){
		return cognome;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getIndirizzo(){
		return indirizzo;
	}

	public void aggiungiContatto(String contatto){
		for (String s : contatti)
			if (s.compareTo(contatto) == 0)
				return;
		contatti.add(contatto);
	}

	public Collection<String> elencoContatti(){
		return contatti;
	}

	public Collection<Pratica> elencoPratiche(){
		List<Pratica> ltemp = new LinkedList<Pratica>(pratiche.values());
		Collections.sort(ltemp);
		return ltemp;
	}

	public int compareTo(Cliente altro) {
		//ordina prima per cognome poi nome e poi indirizzo
		if (this.cognome.compareTo(altro.getCognome()) != 0)
			return this.cognome.compareTo(altro.getCognome());
		if (this.nome.compareTo(altro.getNome()) != 0)
			return this.nome.compareTo(altro.getNome());
		
		return this.indirizzo.compareTo(altro.getIndirizzo());
	}

	public void aggiungiPratica(Pratica p) {
		pratiche.put(p.getIdPratica(), p);
	}
	
	public void eliminaPratica(int idPratica){
		pratiche.remove(idPratica);
	}
}
