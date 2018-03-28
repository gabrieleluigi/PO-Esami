package torneidicalcio;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Torneo {
	
	private String nome;
	private int numeroSquadre;
	private Map<String, Squadra> mappaSquadre = new TreeMap<String, Squadra>();
	private Map<Integer, Incontro> mappaIncontri = new TreeMap<Integer, Incontro>();

	public Torneo(String nome, int numeroSquadre) {
		this.nome = nome;
		this.numeroSquadre = numeroSquadre;
	}

	public String getNome() {
		return nome;
	}

	public int getNumeroSquadre() {
		return numeroSquadre;
	}
	
	public int getNumeroSquadreInserite() {
		return mappaSquadre.size();
	}
	
	public void aggiungiSquara(Squadra s) {
		if(mappaSquadre.size() < numeroSquadre) {
			mappaSquadre.put(s.getNome(), s);
			s.inscriviATorneo(this);
		}
	}
	
	public Collection<Squadra> elencoSquadre(){
		return mappaSquadre.values();
	}
	
	public void aggiungiIncontro(Incontro i) {
		mappaIncontri.put(i.getGiornata(), i);
	}
	
	public Collection<Incontro> getIncontri(){
		return mappaIncontri.values();
	}
}
