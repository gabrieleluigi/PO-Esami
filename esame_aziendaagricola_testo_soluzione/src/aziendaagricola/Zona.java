package aziendaagricola;

import java.util.*;

public class Zona {

	private int codice;

	private int ampiezza;
	private int temperaturaMedia;
	private float irraggiamentoMedio;	
	
	private Map<String, String> caratteristiche = new TreeMap<String, String>();
	private Map<String, Coltivazione> coltivazioni = new TreeMap<String, Coltivazione>();
	private List<Coltivazione> coltivazioniL = new LinkedList<Coltivazione>();
	private Map<String,Raccolto> raccolti = new HashMap<String,Raccolto>();
	
	
	public Zona(int codice){
		this.codice = codice;
	}

	
	public int getCodice() {
		return codice;
	}
	
	public int getAmpiezza() {
		return ampiezza;
	}

	public void setAmpiezza(int ampiezza) {
		this.ampiezza = ampiezza;
	}

	public int getTemperaturaMedia() {
		return temperaturaMedia;
	}

	public void setTemperaturaMedia(int temperaturaMedia) {
		this.temperaturaMedia = temperaturaMedia;
	}

	public float getIrraggiamentoMedio() {
		return irraggiamentoMedio;
	}

	public void setIrraggiamentoMedio(float irraggiamentoMedio) {
		this.irraggiamentoMedio = irraggiamentoMedio;
	}

	public void aggiungiCaratteristica(String caratteristica){
		
		if(!caratteristiche.containsKey(caratteristica))
		    caratteristiche.put(caratteristica, caratteristica);
	}
	
	public Collection<String> elencoCaratteristiche(){
		
		Collection<String> ltemp = new LinkedList<String>(caratteristiche.values());
		Collections.sort((LinkedList<String>)ltemp);
		return ltemp;
	}
	
	public Coltivazione aggiungiColtivazione(String prodotto, int meseSemina, int meseRaccolto) throws ColtivazioneDuplicataException{
		
		if(coltivazioni.containsKey(prodotto+""+meseSemina))
		{
			throw new ColtivazioneDuplicataException();
		}
		else
		{
			Coltivazione ctemp = new Coltivazione(prodotto, meseSemina, meseRaccolto);
			coltivazioni.put(prodotto+""+meseSemina, ctemp);
			coltivazioniL.add(ctemp);
			return ctemp;
		}
	}

	public Collection<Coltivazione> elencoColtivazioni(){
		return coltivazioniL;
	}
	
	
	public Raccolto nuovoRaccolto(String prodotto, String data, int quantita){
		
		Raccolto rtemp = new Raccolto(prodotto, data, quantita);
		
		raccolti.put(""+prodotto+""+data, rtemp);
		
		return rtemp;
	}
	
	public Raccolto cercaRaccolto(String prodotto, String data){
		return raccolti.get(""+prodotto+""+data);
	}
	
	public Collection<Raccolto>elencoRaccolti(){
		
		Collection<Raccolto> ltemp = new LinkedList<Raccolto>(raccolti.values());
		Collections.sort((LinkedList<Raccolto>)ltemp);
		return ltemp;
	}
	
	
	
	
}
