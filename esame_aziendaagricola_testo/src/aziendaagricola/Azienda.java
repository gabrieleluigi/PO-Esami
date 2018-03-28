package aziendaagricola;

import java.util.*;

import java.io.*;

public class Azienda {
	
	private int CODICE_ZONA = 1000;
	private Map<Integer, Zona> zoneMappa = new TreeMap<Integer, Zona>();
	private Map<String, Magazzino> magazziniMappa = new TreeMap<String, Magazzino>();
	private List<Magazzino> magazziniLista = new LinkedList<Magazzino>();

	public Zona aggiungiZona(){
		Zona z = new Zona(CODICE_ZONA);
		zoneMappa.put(CODICE_ZONA, z);
		CODICE_ZONA ++;
		return z;
	}
	
	public Zona cercaZona(int codice){
		if(zoneMappa.containsKey(codice))
			return zoneMappa.get(codice);
		else { //secondo me non è il modo giusto in cui farlo
			Zona z = aggiungiZona();
			return z;
		}
	}
	
	public void specificaCaratteristicheZona(int codice, String caratteristica){
		zoneMappa.get(codice).aggiungiCaratteristica(caratteristica);
	}
	
	public Collection<Zona> elencoZone(){
		return zoneMappa.values();
	}
	
	public Collection<Zona> elencoZone(int ampiezza){
		List<Zona> listaZoneAmpiezza = new LinkedList<Zona>();
		for(Zona z: zoneMappa.values()) {
			if(z.getAmpiezza() > ampiezza)
				listaZoneAmpiezza.add(z);
		}
		return listaZoneAmpiezza;
	}
	
	public Magazzino aggiungiMagazzino(String nome, String prodotto, int quantitaStoccabile){
		Magazzino m = new Magazzino(nome, prodotto, quantitaStoccabile);
		magazziniMappa.put(nome, m);
		magazziniLista.add(m);
		return m;		
	}
	
public int totaleMagazzino(){
		
		Magazzino mtemp;
		int totale=0;
		
		for(int i=0;i<magazziniLista.size();i++){
			mtemp=magazziniLista.get(i);
			
			totale += mtemp.getQuantitaStoccata();
		}
			
		return totale;
	}
	
	
	public void leggi(String nomeFile) throws IOException{

	}
	
}
