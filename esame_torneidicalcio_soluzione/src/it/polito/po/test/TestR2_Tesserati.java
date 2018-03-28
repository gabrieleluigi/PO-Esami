package it.polito.po.test;

import java.util.LinkedList;

import torneidicalcio.*;
import junit.framework.*;

public class TestR2_Tesserati extends TestCase {

	public void testNumeriTessera(){
		
		System.out.println("\n*** R2. testNumeriTessera() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definita squadra.");

		f.nuovaSquadra("Juventus", "Torino", 1897, "Juventus Stadium");

		System.out.println("\nNuovo tesseramento.");

		int numeroTessera1=-1;
		try {
			numeroTessera1 = f.tesseramento("Mario", "Mandzukic","Juventus","Attaccante",17);
			System.out.println("\nNumero di tessera assegnato: "+numeroTessera1);
		} catch (EccezioneErroreDatiTesseramento e) {
			e.printStackTrace();
		}

		System.out.println("\nNuovo tesseramento.");

		int numeroTessera2=-1;
		try {
			numeroTessera2 = f.tesseramento("Mario", "Lemina","Juventus","Centrocampista",18);
			System.out.println("\nNumero di tessera assegnato: "+numeroTessera2);
		} catch (EccezioneErroreDatiTesseramento e) {
			e.printStackTrace();
		}
		
		boolean corretto = false;
		if(numeroTessera2==numeroTessera1+1)
		{
			corretto=true;
			System.out.println("\nNumeri di tessera assegnati in maniera corretta.");
		}
		else
		{
			corretto=false;
			System.out.println("\nErrore nella assegnazione dei numeri di tessera.");
		}

		assertEquals("Implementazione del metodo tesseramento() e/o dei metodi correlati errata", true,corretto);
	}
	
	
	public void testTesseramentoDirigenteCercaTesseratoPerNumeroTessera(){
		
		System.out.println("\n*** R2. testTesseramentoDirigenteCercaTesseratoPerNumeroTessera() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definita squadra.");

		f.nuovaSquadra("Juventus", "Torino", 1897, "Juventus Stadium");

		System.out.println("\nNuovo tesseramento dirigente.");

		int numeroTessera = -1;
		try {
			numeroTessera = f.tesseramento("Giuseppe", "Marotta","Juventus","Amministratore delegato");
			System.out.println("\nNumero di tessera assegnato: "+numeroTessera);
		} catch (EccezioneErroreDatiTesseramento e) {
			e.printStackTrace();
		}
		
		System.out.println("\nCerca tesserato con il numero di tessera assegnato.");

		Tesserato tesserato = null;
		try {
			tesserato = f.cercaTesseratoPerNumeroTessera(numeroTessera);
			System.out.println("\nTesserato trovato: ");
			System.out.println(" Nome: "+tesserato.getNome());
			System.out.println(" Cognome: "+tesserato.getCognome());
		} catch (EccezioneTesseratoInesistente e) {
			e.printStackTrace();
		}

		boolean corretto = false;
		if(tesserato instanceof Dirigente && 
		   tesserato.getNome().compareTo("Giuseppe")==0 && 
		   tesserato.getCognome().compareTo("Marotta")==0 && 
		   ((Dirigente)tesserato).getSquadra().getNome().compareTo("Juventus")==0 && 
		   ((Dirigente)tesserato).getRuolo().compareTo("Amministratore delegato")==0)
		{
			corretto=true;
			System.out.println("\nDirigente tesserato/trovato in maniera corretta.");
		}
		else
		{
			corretto=false;
			System.out.println("\nErrore nel tesseramento/nella ricerca del dirigente.");
		}

		assertEquals("Implementazione dei metodi tesseramento(), cercaTesseratoPerNumeroTessera() e/o dei metodi correlati errata", true,corretto);
	}
	
	
	public void testTesseramentoCalciatoreCercaTesseratoPerNumeroTessera(){
		
		System.out.println("\n*** R2. testTesseramentoCalciatoreCercaTesseratoPerNumeroTessera() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definita squadra.");

		f.nuovaSquadra("Juventus", "Torino", 1897, "Juventus Stadium");

		System.out.println("\nNuovo tesseramento calciatore.");

		int numeroTessera = -1;
		try {
			numeroTessera = f.tesseramento("Mario", "Lemina","Juventus","Centrocampista",18);
			System.out.println("\nNumero di tessera assegnato: "+numeroTessera);
		} catch (EccezioneErroreDatiTesseramento e) {
			e.printStackTrace();
		}
		
		System.out.println("\nCerca tesserato con il numero di tessera assegnato.");

		Tesserato tesserato = null;
		try {
			tesserato = f.cercaTesseratoPerNumeroTessera(numeroTessera);
			System.out.println("\nTesserato trovato: ");
			System.out.println(" Nome: "+tesserato.getNome());
			System.out.println(" Cognome: "+tesserato.getCognome());
		} catch (EccezioneTesseratoInesistente e) {
			e.printStackTrace();
		}

		boolean corretto = false;
		if(tesserato instanceof Calciatore && 
		   tesserato.getNome().compareTo("Mario")==0 && 
		   tesserato.getCognome().compareTo("Lemina")==0 && 
		   ((Calciatore)tesserato).getSquadra().getNome().compareTo("Juventus")==0 && 
		   ((Calciatore)tesserato).getRuolo().compareTo("Centrocampista")==0 && 
		   ((Calciatore)tesserato).getNumeroMaglia()==18)
		{
			corretto=true;
			System.out.println("\nCalciatore tesserato/trovato in maniera corretta.");
		}
		else
		{
			corretto=false;
			System.out.println("\nErrore nel tesseramento/nella ricerca del calciatore.");
		}

		assertEquals("Implementazione dei metodi tesseramento(), cercaTesseratoPerNumeroTessera() e/o dei metodi correlati errata", true,corretto);
	}
	
	
	public void testTesseramentoArbitroCercaTesseratoPerNumeroTessera(){
		
		System.out.println("\n*** R2. testTesseramentoArbitroCercaTesseratoPerNumeroTessera() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Nuovo tesseramento arbitro.");

		int numeroTessera = -1;
		try {
			numeroTessera = f.tesseramento("Andrea", "Gervasoni","Bari");
			System.out.println("\nNumero di tessera assegnato: "+numeroTessera);
		} catch (EccezioneErroreDatiTesseramento e) {
			e.printStackTrace();
		}
		
		System.out.println("\nCerca tesserato con il numero di tessera assegnato.");

		Tesserato tesserato = null;
		try {
			tesserato = f.cercaTesseratoPerNumeroTessera(numeroTessera);
			System.out.println("\nTesserato trovato: ");
			System.out.println(" Nome: "+tesserato.getNome());
			System.out.println(" Cognome: "+tesserato.getCognome());
		} catch (EccezioneTesseratoInesistente e) {
			e.printStackTrace();
		}

		boolean corretto = false;
		if(tesserato instanceof Arbitro && 
		   tesserato.getNome().compareTo("Andrea")==0 && 
		   tesserato.getCognome().compareTo("Gervasoni")==0 &&  
		   ((Arbitro)tesserato).getSezione().compareTo("Bari")==0)
		{
			corretto=true;
			System.out.println("\nArbitro tesserato/trovato in maniera corretta.");
		}
		else
		{
			corretto=false;
			System.out.println("\nErrore nel tesseramento/nella ricerca dell'arbitro.");
		}

		assertEquals("Implementazione dei metodi tesseramento(), cercaTesseratoPerNumeroTessera() e/o dei metodi correlati errata", true,corretto);
	}
	
	
	public void testTesseramentoConErrori(){
	
		System.out.println("\n*** R2. testTesseramentoConErrori() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Nuovo tesseramento con errori nei parametri (stringa nome vuota):");

		boolean eccezione1 = false;
		try {
			f.tesseramento("", "Mandzukic","Juventus","Attaccante",17);
			eccezione1=false;
			System.out.println(" Tesseramento avvenuto.");
		} catch (EccezioneErroreDatiTesseramento e) {
			eccezione1=true;
			System.out.println(" Scatenata eccezione EccezioneErroreDatiTesseramento.");
		}

		System.out.println("\nNuovo tesseramento con errori nei parametri (stringa squadra a null):");

		boolean eccezione2 = false;
		try {
			f.tesseramento("Giuseppe", "Marotta",null,"Amministratore delegato");
			eccezione2=false;
			System.out.println(" Tesseramento avvenuto.");
		} catch (EccezioneErroreDatiTesseramento e) {
			eccezione2=true;
			System.out.println(" Scatenata eccezione EccezioneErroreDatiTesseramento.");
		}

		System.out.println("\nNuovo tesseramento con errori nei parametri (numero di maglia negativo):");

		boolean eccezione3 = false;
		try {
			f.tesseramento("Mario", "Lemina","Juventus","Centrocampista",-1);
			eccezione3=false;
			System.out.println(" Tesseramento avvenuto.");
		} catch (EccezioneErroreDatiTesseramento e) {
			eccezione3=true;
			System.out.println(" Scatenata eccezione EccezioneErroreDatiTesseramento.");
		}
		
		System.out.println("\nNuovo tesseramento con errori nei parametri (stringa sezione vuota):");

		boolean eccezione4 = false;
		try {
			f.tesseramento("Andrea", "Gervasoni","");
			eccezione4=false;
			System.out.println(" Tesseramento avvenuto.");
		} catch (EccezioneErroreDatiTesseramento e) {
			eccezione4=true;
			System.out.println(" Scatenata eccezione EccezioneErroreDatiTesseramento.");
		}
		
		boolean corretto = false;
		if(eccezione1==true && eccezione2==true && eccezione3==true && eccezione4==true)
		{
			corretto=true;
			System.out.println("\nErrori nel tesseramento gestiti in maniera corretta.");
		}
		else
		{
			corretto=false;
			System.out.println("\nErrori nel tesseramento gestiti in maniera errata.");
		}

		assertEquals("Implementazione del metodo tesseramento() e/o dei metodi correlati errata", true,corretto);
	}
	
	
	public void testCercaTesseratoPerNomeCognome(){

		System.out.println("\n*** R2. testCercaTesseratoPerNomeCognome() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Nuovo tesseramento.");

		try {
			f.tesseramento("Andrea", "Gervasoni","Bari");
		} catch (EccezioneErroreDatiTesseramento e) {
			e.printStackTrace();
		}
		
		System.out.println("\nCerca tesserato per nome e cognome.");

		Tesserato tesserato = null;
		try {
			tesserato = f.cercaTesseratoPerNomeCognome("Andrea","Gervasoni");
			System.out.println("\nTesserato trovato: ");
			System.out.println(" Nome: "+tesserato.getNome());
			System.out.println(" Cognome: "+tesserato.getCognome());
		} catch (EccezioneTesseratoInesistente e) {
			e.printStackTrace();
		}

		boolean corretto = false;
		if(tesserato instanceof Arbitro && 
		   tesserato.getNome().compareTo("Andrea")==0 && 
		   tesserato.getCognome().compareTo("Gervasoni")==0 &&  
		   ((Arbitro)tesserato).getSezione().compareTo("Bari")==0)
		{
			corretto=true;
			System.out.println("\nTesserato trovato in maniera corretta.");
		}
		else
		{
			corretto=false;
			System.out.println("\nErrore nella ricerca del tesserato.");
		}

		assertEquals("Implementazione del metodo cercaTesseratoPerNomeCognome() e/o dei metodi correlati errata", true,corretto);	
	}
	
	
	public void testCercaTesseratoInesistente(){

		System.out.println("\n*** R2. testCercaTesseratoInesistente() ***\n");

		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Nuovo tesseramento.");

		int numeroTessera = -1;
		try {
			numeroTessera = f.tesseramento("Andrea", "Gervasoni","Bari");
		} catch (EccezioneErroreDatiTesseramento e) {
			e.printStackTrace();
		}
		
		System.out.println("\nCerca tesserato con numero di tessera inesistente:");
		
		boolean eccezione1 = false;
		try {
			f.cercaTesseratoPerNumeroTessera(numeroTessera+50);
			eccezione1=false;
			System.out.println(" Ricerca effettuata senza errori.");
		} catch (EccezioneTesseratoInesistente e) {
			eccezione1=true;
			System.out.println(" Scatenata eccezione EccezioneTesseratoInesistente.");
		}
		
		System.out.println("\nCerca tesserato con nome cognome inesistente:");

		boolean eccezione2 = false;
		try {
			f.cercaTesseratoPerNomeCognome("Mario","Rossi");
			eccezione1=false;
			System.out.println(" Ricerca effettuata senza errori.");
		} catch (EccezioneTesseratoInesistente e) {
			eccezione2=true;
			System.out.println(" Scatenata eccezione EccezioneTesseratoInesistente.");
		}

		boolean corretto = false;
		if(eccezione1==true && eccezione2==true)
		{
			corretto=true;
			System.out.println("\nRicerca tesserato inesistente gestita in maniera corretta.");
		}
		else
		{
			corretto=false;
			System.out.println("\nRicerca tesserato inesistente gestita in maniera errata.");
		}

		assertEquals("Implementazione dei metodi cercaTesseratoPerNumeroTessera(), cercaTesseratoPerNomeCognome() e/o dei metodi correlati errata", true,corretto);	
	}
	
	
	public void testElencoTesseratiSquadra(){
		
		System.out.println("\n*** R2. testElencoTesseratiSquadra() ***\n");
	
		Federazione f = new Federazione("FIGC", "Federazione Italiana Giuoco Calcio", "http://www.figc.it/");

		System.out.println("Definita squadra.");

		f.nuovaSquadra("Juventus", "Torino", 1897, "Juventus Stadium");

		System.out.println("\nTesseramento di due dirigenti e tre calciatori.");

		try {
			f.tesseramento("Mario", "Mandzukic","Juventus","Attaccante",17);
			f.tesseramento("Paulo", "Dybala","Juventus","Attaccante",21);
			f.tesseramento("Andrea", "Agnelli","Juventus","Presidente");
			f.tesseramento("Mario", "Lemina","Juventus","Centrocampista",18);
			f.tesseramento("Giuseppe", "Marotta","Juventus","Amministratore delegato");
		} catch (EccezioneErroreDatiTesseramento e) {
			e.printStackTrace();
		}
		
		System.out.println("\nElenco tesserati squadra, prima dirigenti poi calciatori (ordinati per nome e cognome):");

		LinkedList<Tesserato> tesserati;
		tesserati = new LinkedList<Tesserato>(f.elencoTesseratiSquadra("Juventus"));
		for(Tesserato ttemp : tesserati)
			if(ttemp instanceof Dirigente)
				System.out.println(" "+ttemp.getNome()+" "+ttemp.getCognome()+ " (Dirigente)");
			else if(ttemp instanceof Calciatore)
				System.out.println(" "+ttemp.getNome()+" "+ttemp.getCognome()+ " (Calciatore)");
		
		boolean corretto=false;
		
		if(tesserati.size()==5 && tesserati.get(0) instanceof Dirigente && tesserati.get(0).getNome().compareTo("Andrea")==0 && tesserati.get(0).getCognome().compareTo("Agnelli")==0 && 
                  				  tesserati.get(1) instanceof Dirigente && tesserati.get(1).getNome().compareTo("Giuseppe")==0 && tesserati.get(1).getCognome().compareTo("Marotta")==0 &&				
                  				  tesserati.get(2) instanceof Calciatore && tesserati.get(2).getNome().compareTo("Mario")==0 && tesserati.get(2).getCognome().compareTo("Lemina")==0 &&				
                  				  tesserati.get(3) instanceof Calciatore && tesserati.get(3).getNome().compareTo("Mario")==0 && tesserati.get(3).getCognome().compareTo("Mandzukic")==0 &&				
                  				  tesserati.get(4) instanceof Calciatore && tesserati.get(4).getNome().compareTo("Paulo")==0 && tesserati.get(4).getCognome().compareTo("Dybala")==0)
		{
			corretto=true;
			System.out.println("\nElenco tesserati squadra corretto.");
		}
		else
		{
			corretto=false;
			System.out.println("\nElenco tesserati squadra errato.");
		}

		assertEquals("Implementazione del metodo elencoTesseratiSquadra() e/o dei metodi correlati errata", true,corretto);		
	}
	
}
