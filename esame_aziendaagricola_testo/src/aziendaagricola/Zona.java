package aziendaagricola;

import java.util.*;

public class Zona {
	
	private int codice;
	private int ampiezza;
	private int temperaturaMedia;
	private float irraggiamentoMedio;
	private List<String> caratteristiche = new LinkedList<String>();
	private List<Coltivazione> coltivazioniLista = new LinkedList<Coltivazione>();
	private Map<String, Coltivazione> coltivazioniMappa = new TreeMap<String, Coltivazione>();
	private Map<String, Raccolto> raccoltiMappa = new TreeMap<String, Raccolto>();

	public Zona(int codice) {
		this.codice = codice;
	}

	public int getCodice() {
		return codice;
	}
	
	public int getAmpiezza() {
		return ampiezza;
	}

	public int getTemperaturaMedia() {
		return temperaturaMedia;
	}

	public float getIrraggiamentoMedio() {
		return irraggiamentoMedio;
	}
	
	public void setAmpiezza(int ampiezza) {
		this.ampiezza = ampiezza;
	}

	public void setTemperaturaMedia(int temperaturaMedia) {
		this.temperaturaMedia = temperaturaMedia;
	}

	public void setIrraggiamentoMedio(float irraggiamentoMedio) {
		this.irraggiamentoMedio = irraggiamentoMedio;
	}
	//--
	public void aggiungiCaratteristica(String caratteristica) {
		if(caratteristiche.contains(caratteristica) == false)
			caratteristiche.add(caratteristica);
	}
	//--

	public Collection<String> elencoCaratteristiche(){
		//provo a stampare e devere l'ordine
		Collections.sort(caratteristiche);
		//------->Contollo risultato SORT!!!!
		return caratteristiche;
	}
	
	public Coltivazione aggiungiColtivazione(String prodotto, int meseSemina, int meseRaccolto) throws ColtivazioneDuplicataException {
		if(coltivazioniMappa.containsKey(prodotto+" "+meseSemina))
			throw new ColtivazioneDuplicataException();
		Coltivazione c = new Coltivazione(prodotto, meseRaccolto, meseRaccolto);
		coltivazioniMappa.put(prodotto+" "+meseSemina, c);
		coltivazioniLista.add(c);
		return c;
	}

	public Collection<Coltivazione> elencoColtivazioni(){
		return coltivazioniLista;
	}
	
	
	public Raccolto nuovoRaccolto(String prodotto, String data, int quantita){
		Raccolto r = new Raccolto(prodotto, data, quantita);
		raccoltiMappa.put(data+" "+prodotto, r);
		return r;
	}
	
	public Raccolto cercaRaccolto(String prodotto, String data){
		return raccoltiMappa.get(data+" "+prodotto);
	}
	
	public Collection<Raccolto>elencoRaccolti(){
		Collection<Raccolto> ltemp = new LinkedList<Raccolto>(raccoltiMappa.values());
		Collections.sort((List<Raccolto>) ltemp);
		return ltemp;
	}
	
}
