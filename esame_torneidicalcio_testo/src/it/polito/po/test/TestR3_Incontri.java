package it.polito.po.test;

import java.util.LinkedList;

import torneidicalcio.*;
import junit.framework.*;

public class TestR3_Incontri extends TestCase {

	public void testNuovoIncontro(){
		
		System.out.println("\n*** R3. testNuovoIncontro() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definita federazione.");

		System.out.println("\nDefinito torneo.");

		f.nuovoTorneo("Serie A", 20);
		
		System.out.println("\nDefinite squadre.");

		f.nuovaSquadra("Juventus", "Torino", 1897, "Juventus Stadium");
		f.nuovaSquadra("Fiorentina", "Firenze", 1926, "Artemio Franchi");

		System.out.println("\nSquadre iscritte a torneo definito.");

		f.iscriviSquadraTorneo("Serie A", "Juventus");
		f.iscriviSquadraTorneo("Serie A", "Fiorentina");
		
		System.out.println("\nDefinito arbitro.");
		
		try {
			f.tesseramento("Marco", "Guida","Torre Annunziata");
		} catch (EccezioneErroreDatiTesseramento e) {
			e.printStackTrace();
		}
		
		System.out.println("\nDefinito incontro:");

		Incontro incontro = f.nuovoIncontro("Serie A", 1, "Juventus", "Fiorentina", "2-0", "Marco", "Guida");

		System.out.println(" Giornata:   "+incontro.getGiornata());
		System.out.println(" Sq. casa:   "+incontro.getSquadraCasa().getNome());
		System.out.println(" Sq. ospite: "+incontro.getSquadraOspite().getNome());
		System.out.println(" Gol casa:   "+incontro.getNumeroGolSquadraCasa());
		System.out.println(" Gol ospite: "+incontro.getNumeroGolSquadraOspite());
		System.out.println(" Arbitro:    "+incontro.getArbitro());
		
		boolean corretto = false;
		if(incontro.getGiornata()==1 && incontro.getSquadraCasa().getNome().compareTo("Juventus")==0 && incontro.getSquadraOspite().getNome().compareTo("Fiorentina")==0 && 
		   incontro.getNumeroGolSquadraCasa()==2 && incontro.getNumeroGolSquadraOspite()==0 && incontro.getArbitro().compareTo("(Marco Guida, Torre Annunziata)")==0)
		{
			corretto=true;
			System.out.println("\nIncontro definito in maniera corretta.");
		}
		else
		{
			corretto=false;
			System.out.println("\nIncontro definito in maniera errata.");
		}

		assertEquals("Implementazione del metodo nuovoIncontro() e/o dei metodi correlati errata", true,corretto);	
	}
	
	
	public void testElencoIncontriPerGiornata(){
		
		System.out.println("\n*** R3. testElencoIncontriPerGiornata() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definiti torneo, squadre ed arbitri.");
		
		f.nuovoTorneo("Serie A", 20);
		
		f.nuovaSquadra("Juventus", "Torino", 1897, "Juventus Stadium");
		f.nuovaSquadra("Inter", "Milano", 1908, "Giuseppe Meazza");
		f.nuovaSquadra("Fiorentina", "Firenze", 1926, "Artemio Franchi");
		f.nuovaSquadra("Sampdoria", "Genova", 1946, "Stadio Luigi Ferraris");

		f.iscriviSquadraTorneo("Serie A", "Juventus");
		f.iscriviSquadraTorneo("Serie A", "Inter");
		f.iscriviSquadraTorneo("Serie A", "Fiorentina");
		f.iscriviSquadraTorneo("Serie A", "Sampdoria");

		try {
			f.tesseramento("Andrea", "Gervasoni","Bari");
			f.tesseramento("Marco", "Guida","Torre Annunziata");
		} catch (EccezioneErroreDatiTesseramento e) {
			e.printStackTrace();
		}

		System.out.println("\nDefiniti incontri.");

		f.nuovoIncontro("Serie A", 2, "Inter", "Sampdoria", "2-2", "Marco", "Guida");
		f.nuovoIncontro("Serie A", 3, "Fiorentina", "Inter", "0-3", "Andrea", "Gervasoni");
		f.nuovoIncontro("Serie A", 2, "Fiorentina", "Juventus", "1-3", "Andrea", "Gervasoni");
		f.nuovoIncontro("Serie A", 1, "Juventus", "Inter", "5-1", "Andrea", "Gervasoni");
		
		System.out.println("\nElenco incontri torneo definito (ordinati per giornata):");

		LinkedList<Incontro> incontri;
		incontri = new LinkedList<Incontro>(f.elencoIncontriPerGiornata("Serie A"));

		for(Incontro itemp : incontri)
			System.out.println(" "+itemp.getGiornata()+" "+itemp.getSquadraCasa().getNome()+" vs "+itemp.getSquadraOspite().getNome()+" "+itemp.getNumeroGolSquadraCasa()+"-"+itemp.getNumeroGolSquadraOspite()+ " "+itemp.getArbitro());
	
		boolean corretto = false;
		if((incontri.get(0).getGiornata()==1 && incontri.get(0).getSquadraCasa().getNome().compareTo("Juventus")==0 && incontri.get(0).getSquadraOspite().getNome().compareTo("Inter")==0 && 
		   incontri.get(1).getGiornata()==2 && incontri.get(1).getSquadraCasa().getNome().compareTo("Inter")==0 && incontri.get(1).getSquadraOspite().getNome().compareTo("Sampdoria")==0 &&				
		   incontri.get(2).getGiornata()==2 && incontri.get(2).getSquadraCasa().getNome().compareTo("Fiorentina")==0 && incontri.get(2).getSquadraOspite().getNome().compareTo("Juventus")==0 && 				
		   incontri.get(3).getGiornata()==3 && incontri.get(3).getSquadraCasa().getNome().compareTo("Fiorentina")==0 && incontri.get(3).getSquadraOspite().getNome().compareTo("Inter")==0 ) ||
		   (incontri.get(0).getGiornata()==1 && incontri.get(0).getSquadraCasa().getNome().compareTo("Juventus")==0 && incontri.get(0).getSquadraOspite().getNome().compareTo("Inter")==0 && 
		   incontri.get(1).getGiornata()==2 && incontri.get(1).getSquadraCasa().getNome().compareTo("Fiorentina")==0 && incontri.get(1).getSquadraOspite().getNome().compareTo("Juventus")==0 &&				
		   incontri.get(2).getGiornata()==2 && incontri.get(2).getSquadraCasa().getNome().compareTo("Inter")==0 && incontri.get(2).getSquadraOspite().getNome().compareTo("Sampdoria")==0 && 				
		   incontri.get(3).getGiornata()==3 && incontri.get(3).getSquadraCasa().getNome().compareTo("Fiorentina")==0 && incontri.get(3).getSquadraOspite().getNome().compareTo("Inter")==0 ))		   
		{
			corretto=true;
			System.out.println("\nElenco incontri per giornata corretto.");
		}
		else
		{
			corretto=false;
			System.out.println("\nElenco incontri per giornata errato.");
		}

		assertEquals("Implementazione del metodo elencoIncontriPerGiornata() e/o dei metodi correlati errata", true,corretto);	
	}
	
	
	public void testElencoIncontriPerDifferenzaReti(){
		
		System.out.println("\n*** R3. testElencoIncontriPerDifferenzaReti() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definiti torneo, squadre ed arbitri.");
		
		f.nuovoTorneo("Serie A", 20);
		
		f.nuovaSquadra("Juventus", "Torino", 1897, "Juventus Stadium");
		f.nuovaSquadra("Inter", "Milano", 1908, "Giuseppe Meazza");
		f.nuovaSquadra("Fiorentina", "Firenze", 1926, "Artemio Franchi");
		f.nuovaSquadra("Sampdoria", "Genova", 1946, "Stadio Luigi Ferraris");

		f.iscriviSquadraTorneo("Serie A", "Juventus");
		f.iscriviSquadraTorneo("Serie A", "Inter");
		f.iscriviSquadraTorneo("Serie A", "Fiorentina");
		f.iscriviSquadraTorneo("Serie A", "Sampdoria");

		try {
			f.tesseramento("Andrea", "Gervasoni","Bari");
			f.tesseramento("Marco", "Guida","Torre Annunziata");
		} catch (EccezioneErroreDatiTesseramento e) {
			e.printStackTrace();
		}

		System.out.println("\nDefiniti incontri.");

		f.nuovoIncontro("Serie A", 1, "Juventus", "Inter", "2-0", "Andrea", "Gervasoni");
		f.nuovoIncontro("Serie A", 2, "Fiorentina", "Juventus", "3-0", "Andrea", "Gervasoni");
		f.nuovoIncontro("Serie A", 3, "Fiorentina", "Inter", "2-1", "Andrea", "Gervasoni");
		
		System.out.println("\nElenco incontri torneo definito (ordinati per differenza reti):");

		LinkedList<Incontro> incontri;
		incontri = new LinkedList<Incontro>(f.elencoIncontriPerDifferenzaReti("Serie A"));

		for(Incontro itemp : incontri)
			System.out.println(" "+itemp.getGiornata()+" "+itemp.getSquadraCasa().getNome()+" vs "+itemp.getSquadraOspite().getNome()+" "+itemp.getNumeroGolSquadraCasa()+"-"+itemp.getNumeroGolSquadraOspite()+ " "+itemp.getArbitro());
	
		boolean corretto = false;
		if(incontri.get(0).getGiornata()==3 && incontri.get(0).getSquadraCasa().getNome().compareTo("Fiorentina")==0 && incontri.get(0).getSquadraOspite().getNome().compareTo("Inter")==0 && 
		   incontri.get(1).getGiornata()==1 && incontri.get(1).getSquadraCasa().getNome().compareTo("Juventus")==0 && incontri.get(1).getSquadraOspite().getNome().compareTo("Inter")==0 &&				
		   incontri.get(2).getGiornata()==2 && incontri.get(2).getSquadraCasa().getNome().compareTo("Fiorentina")==0 && incontri.get(2).getSquadraOspite().getNome().compareTo("Juventus")==0)
		{
			corretto=true;
			System.out.println("\nElenco incontri per differenza reti corretto.");
		}
		else
		{
			corretto=false;
			System.out.println("\nElenco incontri per differenza reti errato.");
		}

		assertEquals("Implementazione del metodo elencoIncontriPerDifferenzaReti() e/o dei metodi correlati errata", true,corretto);	
	}
}
