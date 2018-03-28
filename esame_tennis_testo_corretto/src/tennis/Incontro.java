package tennis;

public class Incontro{
	
	private String id;
	private Torneo torneo;
	private Giocatore giocatore1;
	private Giocatore giocatore2;
	private int turno;
	private String risultato;
	
	public Incontro(String id, Torneo torneo, Giocatore giocatore1,
			Giocatore giocatore2, int turno) {
		this.id = id;
		this.torneo = torneo;
		this.giocatore1 = giocatore1;
		this.giocatore2 = giocatore2;
		this.turno = turno;
		this.risultato = "";
	}

	public String getId() {
		return id;
	}

	public Torneo getTorneo() {
		return torneo;
	}

	public Giocatore getGiocatore1() {
		return giocatore1;
	}

	public Giocatore getGiocatore2() {
		return giocatore2;
	}

	public int getTurno() {
		return turno;
	}

	public void setRisultato(String risultato) {
		this.risultato = risultato;
	}

	public String getRisultato() {
		return risultato;
	}

}
