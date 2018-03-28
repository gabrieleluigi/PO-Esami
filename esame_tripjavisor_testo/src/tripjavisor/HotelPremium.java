package tripjavisor;

public class HotelPremium extends Hotel{
	
	private String numeroTelefono;
	private String sitoInternet;
	private String email;

	public HotelPremium(int codice, String nome, String indirizzo, String citta, int numCamere, String numeroTelefono, String sitoInternet, String email) {
		super(codice, nome, indirizzo, citta, numCamere);
		this.numeroTelefono = numeroTelefono;
		this.sitoInternet = sitoInternet;
		this.email = email;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public String getSitoInternet() {
		return sitoInternet;
	}

	public String getEmail() {
		return email;
	}
}
