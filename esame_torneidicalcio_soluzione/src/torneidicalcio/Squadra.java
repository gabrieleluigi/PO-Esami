package torneidicalcio;

import java.util.TreeMap;

public class Squadra implements Comparable<Squadra>{

	private String nome;
	private String citta;
	private int anno;
	private String stadio;
	
	private int punti;
	
	TreeMap<Integer, String> dirigenti = new TreeMap<Integer,String>();
	TreeMap<Integer, String> calciatori = new TreeMap<Integer,String>();
	
	public Squadra(String nome, String citta, int anno, String stadio){
		this.nome=nome;
		this.citta=citta;
		this.anno=anno;
		this.stadio=stadio;
	}

	public void associaTesserato(Tesserato tesserato, String ruolo){
		if(tesserato instanceof Dirigente)
			dirigenti.put(tesserato.getNumeroTessera(), ruolo);
		else if(tesserato instanceof Calciatore)
			calciatori.put(tesserato.getNumeroTessera(), ruolo);
	}
	
	
	public TreeMap<Integer, String> getDirigenti() {
		return dirigenti;
	}

	public TreeMap<Integer, String> getCalciatori() {
		return calciatori;
	}

	public void setPunti(int punti){
		this.punti=punti;
	}

	public int getPunti(){
		return punti;
	}
	
	
	@Override
	public int compareTo(Squadra altra) {

		return -(this.punti-altra.getPunti());
	}
	
	
	public String getNome() {
		return nome;
	}

	public String getCitta() {
		return citta;
	}

	public int getAnno() {
		return anno;
	}

	public String getStadio() {
		return stadio;
	}
}
