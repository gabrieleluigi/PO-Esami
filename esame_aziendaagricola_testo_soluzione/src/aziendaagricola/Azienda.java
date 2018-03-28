package aziendaagricola;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Azienda {

	private int prossimoCodiceZona = 1000;
	
	private Map<Integer, Zona> zone = new TreeMap<Integer, Zona>();
	private Map<String, Magazzino> magazzini = new TreeMap<String, Magazzino>();
	private List<Magazzino> magazziniL = new LinkedList<Magazzino>();

	
	public Zona aggiungiZona(){
		Zona ztemp = new Zona(prossimoCodiceZona);
		
		zone.put(ztemp.getCodice(),ztemp);
		
		++prossimoCodiceZona;
		
		return ztemp;
	}
	
	public Zona cercaZona(int codice){
		
		if(zone.containsKey(codice))
			return zone.get(codice);
		else
		{	
			Zona ztemp = new Zona(prossimoCodiceZona);
			zone.put(ztemp.getCodice(),ztemp);
			return ztemp;
		}
	}
	
	public void specificaCaratteristicheZona(int codice, String caratteristica){

		Zona ztemp = zone.get(codice);
		
		ztemp.aggiungiCaratteristica(caratteristica);
	}
	
	public Collection<Zona> elencoZone(){
		
		Collection<Zona> ltemp = new LinkedList<Zona>(zone.values());
		return ltemp;
	}
	
	public Collection<Zona> elencoZone(int ampiezza){
		
		List<Zona> lsrc = new LinkedList<Zona>(zone.values());
		Collection<Zona> ldst = new LinkedList<Zona>();
		Zona ztemp;
		
		for(int i=0;i<lsrc.size();i++){
			ztemp = (Zona)lsrc.get(i);
			if(ztemp.getAmpiezza()>ampiezza)
				ldst.add(ztemp);
		}
		return ldst;
	}
	
	public Magazzino aggiungiMagazzino(String nome, String prodotto, int quantitaStoccabile){
		
		Magazzino mtemp = new Magazzino(nome, prodotto,quantitaStoccabile);
		magazzini.put(nome, mtemp);
		magazziniL.add(mtemp);
		return mtemp;
		
	}
	
	public int totaleMagazzino(){
		
		Magazzino mtemp;
		int totale=0;
		
		for(int i=0;i<magazziniL.size();i++){
			mtemp=magazziniL.get(i);
			
			totale += mtemp.getQuantitaStoccata();
		}
			
		return totale;
	}
	
	
	public void leggi(String nomeFile) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(nomeFile));
		String linea;

		String zona=null;
		String prodotto=null;
		String meseSemina=null;
		String meseRaccolta=null;

		String data = null;
		String quantita = null;
		
		while ((linea = in.readLine()) != null) {
			
			try {
			    StringTokenizer st = new StringTokenizer(linea, ";");
			    String iniziale = st.nextToken().trim();
			    if (iniziale.toUpperCase().equals("C")) 
			    {
			       zona = st.nextToken().trim();
			       prodotto = st.nextToken().trim();
			       meseSemina = st.nextToken().trim();
			       meseRaccolta = st.nextToken().trim();
			       
			       Zona ztemp = this.cercaZona(Integer.parseInt(zona));
			       ztemp.aggiungiColtivazione(prodotto, Integer.parseInt(meseSemina), Integer.parseInt(meseRaccolta));
			    } 
			    else if (iniziale.toUpperCase().equals("R")) 
			    {
			       zona = st.nextToken().trim();
			       prodotto = st.nextToken().trim();
			       data = st.nextToken().trim();
			       quantita = st.nextToken().trim();
			       
			       Zona ztemp = this.cercaZona(Integer.parseInt(zona));
			       
			       ztemp.nuovoRaccolto(prodotto, data, Integer.parseInt(quantita));
			    }
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		in.close();
	}
		
	
	
	
}
