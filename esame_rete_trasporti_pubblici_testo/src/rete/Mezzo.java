package rete;

import java.util.*;

public class Mezzo {
	
	protected int id;
	private Map<String, Linea> servizio = new HashMap<String, Linea>();

	public Mezzo(int id) {
		this.id = id;
	}

	public int getId() {
		return -1;
	}
	
	public Map<String, Linea> getServizio() {
		return servizio;
	}

	public void aggiungiServizio(String data, Linea linea) {
		servizio.put(data, linea);
	}
}
