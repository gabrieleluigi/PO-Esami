package aziendaagricola;

import java.util.LinkedList;
import java.util.List;

public class Coltivazione {

	private String prodotto;
	private int meseSemina;
	private int meseRaccolta;
	List<Fertilizzante> fertilizzanti = new LinkedList<Fertilizzante>();
	
	public Coltivazione(String prodotto, int meseSemina, int meseRaccolta) {
		this.prodotto = prodotto;
		this.meseSemina = meseSemina;
		this.meseRaccolta = meseRaccolta;
	}

	public String getProdotto() {
		return prodotto;
	}

	public int getMeseSemina() {
		return meseSemina;
	}

	public int getMeseRaccolta() {
		return meseRaccolta;
	}
	
	public void setProdotto(String prodotto) {
		this.prodotto = prodotto;
	}

	public void setMeseSemina(int meseSemina) {
		this.meseSemina = meseSemina;
	}

	public void setMeseRaccolta(int meseRaccolta) {
		this.meseRaccolta = meseRaccolta;
	}

   public Fertilizzante richiedeFertilizzante(char tipo, String nome, float costo, int periodicita, int quantitaImpiego){
	   Fertilizzante f;
	   if(tipo == 'A') {
		   f = new FertilizzanteArtificiale(nome, costo, quantitaImpiego, quantitaImpiego);
		   fertilizzanti.add(f);
		   return f;
	   }
	   else if(tipo == 'N') {
		   f = new FertilizzanteNaturale(nome, costo, quantitaImpiego, quantitaImpiego);
		   fertilizzanti.add(f);
		   return f;
	   }
	   return null;
   }
	
}
