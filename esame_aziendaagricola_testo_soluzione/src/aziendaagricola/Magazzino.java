package aziendaagricola;

public class Magazzino {

	private String nome;
	private String prodotto;
	private int quantitaStoccabile;
	private int quantitaStoccata;
	
	public Magazzino (String nome, String prodotto, int quantitaStoccabile){
		this.nome=nome;
		this.prodotto = prodotto;
		this.quantitaStoccabile=quantitaStoccabile;

	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProdotto() {
		return prodotto;
	}

	public void setProdotto(String prodotto) {
		this.prodotto = prodotto;
	}

	public int getQuantitaStoccabile() {
		return quantitaStoccabile;
	}

	public void setQuantitaStoccabile(int quantitaStoccabile) {
		this.quantitaStoccabile = quantitaStoccabile;
	}
	
	public int getQuantitaStoccata() {
		return quantitaStoccata;
	}

	public void setQuantitaStoccata(int quantitaStoccata) {
		this.quantitaStoccata = quantitaStoccata;
	}	
	
	public int stocca(Raccolto raccolto) throws ProdottoNonCompatibileException{

		if(raccolto.getProdotto().compareTo(this.prodotto)!=0)
			throw new ProdottoNonCompatibileException();
		else
		{
			if(raccolto.getQuantita()<=(this.quantitaStoccabile-this.quantitaStoccata))
			{
				this.quantitaStoccata+=raccolto.getQuantita();
				return this.quantitaStoccabile-this.quantitaStoccata;
			}
			else
			{
				int residuo=this.quantitaStoccabile-this.quantitaStoccata-raccolto.getQuantita();
				this.quantitaStoccata=quantitaStoccabile;
				return residuo;
			}
		}
	}
	
	public void preleva(int quantita){
		this.quantitaStoccata-=quantita;
		if(this.quantitaStoccata<=0)
			this.quantitaStoccata=0;
	}
	
}
