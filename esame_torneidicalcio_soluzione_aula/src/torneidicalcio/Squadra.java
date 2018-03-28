package torneidicalcio;

public class Squadra {
	private String nome;
	private String citta;
	private int anno; 
	private String stadio;
	private Torneo torneo;
	
	public Squadra(String nome, String citta, int anno, String stadio) {
		this.nome = nome;
		this.citta = citta;
		this.anno = anno;
		this.stadio = stadio;
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

	public void iscriviATorneo(Torneo t){
		torneo = t;
	} 
}
