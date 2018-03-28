package tripjavisor;

public class Recensione {
	
	private int codice;
	private String data;
	private String titolo;
	private String testo;
	private double voto;
	private Utente utente;
	private Hotel hotel;
	
	private int miPiace = 0;
	private int nonMiPiace = 0;
	
	public Recensione(int codice, String data, String titolo, String testo, double voto, Utente utente, Hotel hotel) {
		this.codice = codice;
		this.data = data;
		this.titolo = titolo;
		this.testo = testo;
		this.voto = voto;
		this.utente = utente;
		this.hotel = hotel;
	}

	public String getData() {
		return data;
	}

	public String getTitolo() {
		return titolo;
	}

	public String getTesto() {
		return testo;
	}

	public double getVoto() {
		return voto;
	}

	public int getCodice() {
		return codice;
	}
	
	public Utente getUtente() {
		return utente;
	}

	public Hotel getHotel() {
		return hotel;
	}
	//---

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public void setVoto(double voto) {
		this.voto = voto;
	}
	
	public void incrmetaMiPiace() {
		miPiace++;
	}
	
	public void incrmetaNonMiPiace() {
		nonMiPiace++;
	}

	public int getMiPiace() {
		return miPiace;
	}

	public int getNonMiPiace() {
		return nonMiPiace;
	}
	
	
	
}
