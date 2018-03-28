package casaeditrice;

import java.util.*;

public class Pubblicazione implements Comparable<Pubblicazione> {
	
	private String titolo;
	private String volume;
	private int anno;
	private Autore proprietario;
	Map<Autore, Integer> coautori = new HashMap<Autore, Integer>();
	
	public Pubblicazione(String titolo, String volume, int anno) {
		this.titolo = titolo;
		this.volume = volume;
		this.anno = anno;
	}

	public String getTitolo(){
		return titolo;
	}
	
	public String getVolume(){
		return volume;
	}
	
	public int getAnno(){
		return anno;
	}

	public Autore getProprietario(){
		return proprietario;
	}

	public void aggiungiCoautori(Autore a, int contributo) throws AutoreDuplicatoException{
		for(Autore c: coautori.keySet())
			if(c.getCognome().compareTo(a.getCognome()) == 0 && c.getNome().compareTo(a.getNome()) == 0)
				throw new AutoreDuplicatoException();
		coautori.put(a, contributo);
	}
	
	public Collection<Autore> elencoAutori(){
		List<Autore> autoriLista = new LinkedList<Autore>();
		for(Autore a: coautori.keySet())
			if(a.compareTo(proprietario) != 0)
				autoriLista.add(a);
		Collections.sort(autoriLista);
		autoriLista.add(0, proprietario);
		return autoriLista;
	}

	public int getContributo(Autore a){
		return coautori.get(a);
	}

	public void setProprietario(Autore autore, int contributo) {
		this.proprietario=autore;
		this.coautori.put(proprietario, contributo);
	}

	@Override
	public int compareTo(Pubblicazione o) {
		return -(this.anno-o.anno);
	}
	
}
