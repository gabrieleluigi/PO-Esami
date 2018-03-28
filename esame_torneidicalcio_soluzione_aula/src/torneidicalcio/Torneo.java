package torneidicalcio;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Torneo {
	private String nome;
	private int numeroSquadre;
	
	private Map<String, Squadra> mappaSquadre = new TreeMap<String, Squadra>();

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
	
	public void iscriviSquadra(Squadra s){
		if (mappaSquadre.size() < numeroSquadre){
			mappaSquadre.put(s.getNome(), s);
			
			//  memorizzo il torneo nella squadra
			s.iscriviATorneo(this);
			
		}
	}

	public Map<String, Squadra> getMappaSquadre() {
		return mappaSquadre;
	}
	
	

}
