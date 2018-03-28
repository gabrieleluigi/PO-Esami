package tennis;

import java.util.Map;
import java.util.TreeMap;

public class Giocatore {
	
	private String id;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String nazionalita;
	private  Map<String, Incontro> mappaIncontri = new TreeMap<String, Incontro>();
	
	public Giocatore(String id, String nome, String cognome, String dataNascita,
			String nazionalita) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.nazionalita = nazionalita;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public String getNazionalita() {
		return nazionalita;
	}
	
	public Map<String, Incontro> getMappaIncontri() {
		return mappaIncontri;
	}
	
	public void addIncontro(Incontro i){
		mappaIncontri.put(i.getTorneo().getNome()+" "+i.getTurno(), i);
	}
	
}
