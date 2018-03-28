package aziendaagricola;

import java.util.*;

public class Coltivazione {

	private String prodotto;
	private int meseSemina;
	private int meseRaccolta;
	
	List<Fertilizzante> fertilizzanti = new LinkedList<Fertilizzante>();
	
	public Coltivazione(String prodotto, int meseSemina, int meseRaccolta){
		this.prodotto=prodotto;
		this.meseSemina = meseSemina;
		this.meseRaccolta = meseRaccolta;
	}
	
	
	public String getProdotto() {
		return prodotto;
	}

	public void setProdotto(String prodotto) {
		this.prodotto = prodotto;
	}

	public int getMeseSemina() {
		return meseSemina;
	}

	public void setMeseSemina(int meseSemina) {
		this.meseSemina = meseSemina;
	}

	public int getMeseRaccolta() {
		return meseRaccolta;
	}

	public void setMeseRaccolta(int meseRaccolta) {
		this.meseRaccolta = meseRaccolta;
	}

   public Fertilizzante richiedeFertilizzante(char tipo, String nome, float costo, int periodicita, int quantitaImpiego){
	   Fertilizzante ftemp=null;
	   if(tipo=='N'){
		   ftemp = new FertilizzanteNaturale(nome,costo,periodicita,quantitaImpiego);
	   }
	   else if(tipo=='A')
	   {
		   ftemp = new FertilizzanteArtificiale(nome,costo,periodicita,quantitaImpiego);
	   }
	   fertilizzanti.add(ftemp);
	   return ftemp;
   }
   
	
	
}
