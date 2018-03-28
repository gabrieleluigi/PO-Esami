package torneidicalcio;

public class Arbitro extends Tesserato{

	String sezione;
	
	public Arbitro(int numeroTessera, String nome, String cognome, String sezione) {
		super(numeroTessera, nome, cognome);
		this.sezione=sezione;
	}

	public String getSezione(){
		return sezione;
	}

}
