package it.polito.po.test;

import torneidicalcio.*;
import junit.framework.*;

public class TestR4_PuntiClassifica extends TestCase {

	public void testPuntiSquadra(){
		
		System.out.println("\n*** R4. testPuntiSquadra() ***\n");

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

		f.nuovoIncontro("Serie A", 1, "Inter", "Juventus", "2-4", "Marco", "Guida");
		f.nuovoIncontro("Serie A", 1, "Fiorentina", "Sampdoria", "1-3", "Andrea", "Gervasoni");
		f.nuovoIncontro("Serie A", 2, "Juventus", "Fiorentina", "3-3", "Andrea", "Gervasoni");
		f.nuovoIncontro("Serie A", 2, "Sampdoria", "Inter", "1-0", "Marco", "Guida");
		
		System.out.println("\nPunti:");

		System.out.println(" Juventus "+f.puntiSquadra("Juventus"));
		System.out.println(" Inter "+f.puntiSquadra("Inter"));
		System.out.println(" Fiorentina "+f.puntiSquadra("Fiorentina"));

		boolean corretto = false;
		if(f.puntiSquadra("Juventus")==4 && f.puntiSquadra("Inter")==0 && f.puntiSquadra("Fiorentina")==1)		   
		{
			corretto=true;
			System.out.println("\nPunti corretti.");
		}
		else
		{
			corretto=false;
			System.out.println("\nPunti errati.");
		}

		assertEquals("Implementazione del metodo puntiSquadra() e/o dei metodi correlati errata", true,corretto);		
	}


	public void testClassificaTorneo(){
		
		System.out.println("\n*** R4. testClassificaTorneo() ***\n");

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
		f.nuovoIncontro("Serie A", 4, "Sampdoria", "Fiorentina", "0-0", "Andrea", "Gervasoni");
		
		System.out.println("\nClassifica:");

		String classifica = f.classificaTorneo("Serie A");
		System.out.println(""+classifica);
		
		boolean corretto = false;
		if(classifica.compareTo("6 Juventus;\n4 Inter;\n2 Sampdoria;\n1 Fiorentina.")==0 || 
		   classifica.compareTo(" 6 Juventus;\n 4 Inter;\n 2 Sampdoria;\n 1 Fiorentina.")==0)		   
		{
			corretto=true;
			System.out.println("\nClassifica corretta.");
		}
		else
		{
			corretto=false;
			System.out.println("\nClassifica errata.");
		}

		assertEquals("Implementazione del metodo classificaTorneo() e/o dei metodi correlati errata", true,corretto);		
	}

}
