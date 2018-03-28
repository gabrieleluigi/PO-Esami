package rete;

import java.util.*;

public class Linea {
	private String numero;
	private Fermata fermataDa;
	private Fermata fermataA;
	private List<Fermata> FermateLineaLista = new LinkedList<Fermata>();
	private int next = 1;
	
	public Linea(String numero, Fermata fermataDa, Fermata fermataA) {
		this.numero = numero;
		this.fermataDa = fermataDa;
		this.fermataA = fermataA;
		FermateLineaLista.add(fermataDa);
		FermateLineaLista.add(fermataA);
	}

	public String getNumero() {
		return numero;
	}

	public Fermata getFermataDa() {
		return fermataDa;
	}

	public Fermata getFermataA() {
		return fermataA;
	}
	
	public void aggiungiFermata(Fermata f){
		FermateLineaLista.add(next, f);
		next++;
	}

	public List<Fermata> getFermateLineaLista() {
		return FermateLineaLista;
	}
	
}
