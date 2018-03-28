package it.polito.po.test;

import java.util.LinkedList;

import torneidicalcio.*;
import junit.framework.*;

public class TestR1_TorneiSquadre extends TestCase {

	public void testFederazione(){
		
		System.out.println("\n*** R1. testFederazione() ***\n");

		Federazione f = new Federazione("F.I.G.C.", "Federazione Italiana Gioco Calcio", "http://www.figc.it");

		System.out.println("Definita federazione:");

		System.out.println(" Sigla:    "+f.getSigla());
		System.out.println(" Nome:     "+f.getDenominazione());
		System.out.println(" Sito Web: "+f.getSitoWeb());

		boolean corretto = false;
		if(f!=null && f.getSigla().compareTo("F.I.G.C.")==0 && f.getDenominazione().compareTo("Federazione Italiana Gioco Calcio")==0 && f.getSitoWeb().compareTo("http://www.figc.it")==0)
		{
			corretto=true;
			System.out.println("\nFederazione definita in maniera corretta.");
		}
		else
		{
			corretto=false;
			System.out.println("\nFederazione definita in maniera errata.");
		}

		assertEquals("Implementazione del metodo Federazione() e/o dei metodi correlati errata", true,corretto);	
	}
	
	
	public void testNuovoTorneo(){
		
		System.out.println("\n*** R1. testNuovoTorneo() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definita federazione.");

		System.out.println("\nDefinito nuovo torneo:");

		Torneo t = f.nuovoTorneo("Serie N", 26);
		
		System.out.println(" Nome:    "+t.getNome());
		System.out.println(" Squadre: "+t.getNumeroSquadre());

		boolean corretto = false;
		if(t!=null && t.getNome().compareTo("Serie N")==0 && t.getNumeroSquadre()==26)
		{
			corretto=true;
			System.out.println("\nNuovo torneo definito in maniera corretta.");
		}
		else
		{
			corretto=false;
			System.out.println("\nErrore nella definizione del nuovo torneo.");
		}

		assertEquals("Implementazione del metodo nuovoTorneo() e/o dei metodi correlati errata", true,corretto);	
	}
	
	
	public void testNuovoTorneoConDuplicati(){
		
		System.out.println("\n*** R1. testNuovoTorneoConDuplicati() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definita federazione.");

		System.out.println("\nDefiniti diversi tornei (alcuni ripetuti).");

		Torneo t1 = f.nuovoTorneo("Serie A", 20);
		Torneo t2 = f.nuovoTorneo("Serie B", 22);
		Torneo t3 = f.nuovoTorneo("Serie B", 18);
		Torneo t4 = f.nuovoTorneo("Serie A", 25);

		boolean corretto = false;

		if(t1!=null && t2!=null && t3!=null && t4!=null && t4.getNome().compareTo(t1.getNome())==0 && t4.getNumeroSquadre()==t1.getNumeroSquadre() && t3.getNome().compareTo(t2.getNome())==0 && t3.getNumeroSquadre()==t2.getNumeroSquadre())
		{
			corretto=true;
			System.out.println("\nDefinizione tornei con duplicati gestita in maniera corretta.");
		}
		else
		{
			corretto=false;
			System.out.println("\nErrore nella gestione della definizione di tornei duplicati.");
		}

		assertEquals("Implementazione del metodo nuovoTorneo() e/o dei metodi correlati errata", true,corretto);	
	}
	
	
	public void testNuovaSquadraCercaSquadra(){
		
		System.out.println("\n*** R1. testNuovaSquadraCercaSquadra() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definita federazione.");

		System.out.println("\nDefinita nuova squadra.");

		f.nuovaSquadra("Juventus", "Torino", 1897, "Juventus Stadium");
		
		System.out.println("\nCerca squadra definita:");

		Squadra s = f.cercaSquadra("Juventus");
		
		System.out.println(" Nome:   "+s.getNome());
		System.out.println(" Citta': "+s.getCitta());
		System.out.println(" Anno:   "+s.getAnno());
		System.out.println(" Stadio: "+s.getStadio());

		boolean corretto = false;
		if(s!=null && s.getNome().compareTo("Juventus")==0 && s.getCitta().compareTo("Torino")==0 && s.getAnno()==1897 && s.getStadio().compareTo("Juventus Stadium")==0)
		{
			corretto=true;
			System.out.println("\nNuova squadra definita/trovata in maniera corretta.");
		}
		else
		{
			corretto=false;
			System.out.println("\nErrore nella definizione/ricerca della nuova squadra.");
		}

		assertEquals("Implementazione dei metodi nuovaSquadra(), cercaSquadra e/o dei metodi correlati errata", true,corretto);	
	}
	
	
	public void testElencoTornei(){
		
		System.out.println("\n*** R1. testElencoTornei() ***\n");
	
		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definiti diversi tornei (alcuni ripetuti).");

		f.nuovoTorneo("Serie B", 22);
		f.nuovoTorneo("Serie A", 20);
		f.nuovoTorneo("Serie D", 162);
		f.nuovoTorneo("Serie A", 25);
		f.nuovoTorneo("Lega Pro", 54);
		f.nuovoTorneo("Serie B", 18);
	
		LinkedList<Torneo> tornei;
		tornei = new LinkedList<Torneo>(f.elencoTornei());

		System.out.println("\nElenco tornei (ordine inserimento):");

		tornei = new LinkedList<Torneo>(f.elencoTornei());
		for(Torneo ttemp : tornei)
			System.out.println(" "+ttemp.getNome()+" "+ttemp.getNumeroSquadre());

		boolean corretto=false;
		
		if(tornei.size()==4 && tornei.get(0).getNome().compareTo("Serie B")==0  && tornei.get(0).getNumeroSquadre()==22 &&
				               tornei.get(1).getNome().compareTo("Serie A")==0  && tornei.get(1).getNumeroSquadre()==20 && 
				               tornei.get(2).getNome().compareTo("Serie D")==0 && tornei.get(2).getNumeroSquadre()==162 &&
				               tornei.get(3).getNome().compareTo("Lega Pro")==0 && tornei.get(3).getNumeroSquadre()==54 )
		{
			corretto=true;
			System.out.println("\nElenco tornei corretto.");
		}
		else
		{
			corretto=false;
			System.out.println("\nElenco tornei errato.");
		}
	
		assertEquals("Implementazione del metodo elencoTornei() e/o dei metodi correlati errata", true,corretto);	
	}
	
	
	public void testElencoSquadre(){
		
		System.out.println("\n*** R1. testElencoSquadre() ***\n");
	
		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definite diverse squadre.");

		f.nuovaSquadra("Juventus", "Torino", 1897, "Juventus Stadium");
		f.nuovaSquadra("Inter", "Milano", 1908, "Giuseppe Meazza");
		f.nuovaSquadra("Fiorentina", "Firenze", 1926, "Artemio Franchi");
		f.nuovaSquadra("Lumezzane", "Brescia", 1946, "Stadio Tullio Saleri");
		f.nuovaSquadra("Cuneo", "Cuneo", 1905, "Stadio Fratelli Paschiero");
		
		System.out.println("\nElenco squadre federazione (ordine alfabetico):");

		LinkedList<Squadra> squadre;
		squadre = new LinkedList<Squadra>(f.elencoSquadre());
		for(Squadra stemp : squadre)
			System.out.println(" "+stemp.getNome()+" "+stemp.getCitta()+" "+stemp.getAnno()+" "+stemp.getStadio());

		boolean corretto=false;
		
		if(squadre.size()==5 && squadre.get(0).getNome().compareTo("Cuneo")==0 && 
							    squadre.get(1).getNome().compareTo("Fiorentina")==0 && 
							    squadre.get(2).getNome().compareTo("Inter")==0 && 
							    squadre.get(3).getNome().compareTo("Juventus")==0 && 
							    squadre.get(4).getNome().compareTo("Lumezzane")==0 )
		{
			corretto=true;
			System.out.println("\nElenco squadre corretto.");
		}
		else
		{
			corretto=false;
			System.out.println("\nElenco squadre errato.");
		}
	
		assertEquals("Implementazione del metodo elencoSquadre() e/o dei metodi correlati errata", true,corretto);		
	}
	
	
	public void testElencoSquadreConDuplicati(){
		
		System.out.println("\n*** R1. testElencoSquadreConDuplicati() ***\n");
	
		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definite diverse squadre (alcune duplicate).");

		f.nuovaSquadra("Juventus", "Torino", 1897, "Juventus Stadium");
		f.nuovaSquadra("Inter", "Milano", 1908, "Giuseppe Meazza");
		f.nuovaSquadra("Juventus", "Turin", 1111, "Stadio delle Alpi");
		
		System.out.println("\nElenco squadre federazione (ordine alfabetico):");

		LinkedList<Squadra> squadre;
		squadre = new LinkedList<Squadra>(f.elencoSquadre());
		for(Squadra stemp : squadre)
			System.out.println(" "+stemp.getNome()+" "+stemp.getCitta()+" "+stemp.getAnno()+" "+stemp.getStadio());

		boolean corretto=false;
		
		if(squadre.size()==2 && squadre.get(0).getNome().compareTo("Inter")==0 && squadre.get(0).getCitta().compareTo("Milano")==0 && squadre.get(0).getAnno()==1908 && squadre.get(0).getStadio().compareTo("Giuseppe Meazza")==0 && 
				                squadre.get(1).getNome().compareTo("Juventus")==0 && squadre.get(1).getCitta().compareTo("Torino")==0 && squadre.get(1).getAnno()==1897 && squadre.get(1).getStadio().compareTo("Juventus Stadium")==0)
		{
			corretto=true;
			System.out.println("\nElenco squadre corretto.");
		}
		else
		{
			corretto=false;
			System.out.println("\nElenco squadre errato.");
		}
	
		assertEquals("Implementazione del metodo elencoSquadre() e/o dei metodi correlati errata", true,corretto);		
	}	
	
	
	public void testIscriviSquadraTorneoElencoSquadreTorneo(){
		
		System.out.println("\n*** R1. testIscriviSquadraTorneoElencoSquadreTorneo() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definito torneo.");

		f.nuovoTorneo("Serie A", 4);
		
		System.out.println("\nDefinite squadre.");

		f.nuovaSquadra("Juventus", "Torino", 1897, "Juventus Stadium");
		f.nuovaSquadra("Inter", "Milano", 1908, "Giuseppe Meazza");
		f.nuovaSquadra("Fiorentina", "Firenze", 1926, "Artemio Franchi");

		System.out.println("\nIscrizione squadre al torneo.");

		f.iscriviSquadraTorneo("Serie A", "Juventus");
		f.iscriviSquadraTorneo("Serie A", "Inter");
		f.iscriviSquadraTorneo("Serie A", "Fiorentina");

		System.out.println("\nElenco squadre iscritte al torneo definito (ordine alfabetico):");

		LinkedList<Squadra> squadre;
		squadre = new LinkedList<Squadra>(f.elencoSquadreTorneo("Serie A"));
		for(Squadra stemp : squadre)
			System.out.println(" "+stemp.getNome());		
		
		boolean corretto=false;
		
		if(squadre.size()==3 && squadre.get(0).getNome().compareTo("Fiorentina")==0 && 
							    squadre.get(1).getNome().compareTo("Inter")==0 && 
							    squadre.get(2).getNome().compareTo("Juventus")==0)
		{
			corretto=true;
			System.out.println("\nElenco squadre iscritte al torneo corretto.");
		}
		else
		{
			corretto=false;
			System.out.println("\nElenco squadre iscritte al torneo errato.");
		}
	
		assertEquals("Implementazione dei metodi iscriviSquadraTorneo(), elencoSquadreTorneo() e/o dei metodi correlati errata", true,corretto);	
	}
	
	
	public void testIscriviSquadraTorneoNumeroMassimoSquadre(){
		
		System.out.println("\n*** R1. testIscriviSquadraTorneoNumeroMassimoSquadre() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definito torneo (massimo due squadre).");

		f.nuovoTorneo("Serie A", 2);
		
		System.out.println("\nDefinite squadre.");

		f.nuovaSquadra("Juventus", "Torino", 1897, "Juventus Stadium");
		f.nuovaSquadra("Inter", "Milano", 1908, "Giuseppe Meazza");
		f.nuovaSquadra("Fiorentina", "Firenze", 1926, "Artemio Franchi");

		System.out.println("\nIscrizione tre squadre al torneo (una in piu' di quelle ammissibili).");

		f.iscriviSquadraTorneo("Serie A", "Juventus");
		f.iscriviSquadraTorneo("Serie A", "Inter");
		f.iscriviSquadraTorneo("Serie A", "Fiorentina");

		System.out.println("\nElenco squadre iscritte al torneo definito (ordine alfabetico):");

		LinkedList<Squadra> squadre;
		squadre = new LinkedList<Squadra>(f.elencoSquadreTorneo("Serie A"));
		for(Squadra stemp : squadre)
			System.out.println(" "+stemp.getNome());		
		
		boolean corretto=false;
		
		if(squadre.size()==2 && squadre.get(0).getNome().compareTo("Inter")==0 && 
							    squadre.get(1).getNome().compareTo("Juventus")==0)
		{
			corretto=true;
			System.out.println("\nElenco squadre iscritte al torneo corretto.");
		}
		else
		{
			corretto=false;
			System.out.println("\nElenco squadre iscritte al torneo errato.");
		}
	
		assertEquals("Implementazione dei metodi iscriviSquadraTorneo(), elencoSquadreTorneo() e/o dei metodi correlati errata", true,corretto);	
	}

}
