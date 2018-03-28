package it.polito.po.test;

import aziendaagricola.*;

import junit.framework.TestCase;
import java.util.*;

public class TestR1_Zone extends TestCase {

	Azienda a;
	
	public void setUp(){
		
		a = new Azienda();
    }
	
	
	public void testAggiungiZona() {
		
		System.out.println("\n*** testAggiungiZona() ***\n");

		System.out.println("Aggiungo una zona");
		Zona za = a.aggiungiZona();

		assertNotNull("Errore nell'aggiunta della zona.",za);
		
		int codice = za.getCodice();
		
		System.out.println("Aggiunta zona "+codice);

		if(codice==1000)
			System.out.println("Codice zona corretto");
		else
			System.out.println("Codice zona errato");
			
		assertEquals("Codice numerico generato per la zona aggiunta errato.",1000,codice);
		
	}
	
	
	public void testAggiungiPiuZone() {
		
		System.out.println("\n*** testAggiungiPiuZone() ***\n");

		System.out.println("Aggiungo tre zone");
		Zona za = a.aggiungiZona();
		int codicea = za.getCodice();
		System.out.println("Aggiunta zona "+codicea);

		Zona zb = a.aggiungiZona();
		int codiceb = zb.getCodice();
		System.out.println("Aggiunta zona "+codiceb);

		Zona zc = a.aggiungiZona();
		int codicec = zc.getCodice();
		System.out.println("Aggiunta zona "+codicec);

		boolean flagCorretto=false;
		if(codicea==1000 && codiceb==1001 && codicec==1002){
			System.out.println("Codici zona corretti");
			flagCorretto=true;
		}
		else
		{
			System.out.println("Codici zona errati");
			flagCorretto=false;
		}
			
		assertEquals("Codici numerici generati per le zone aggiunte errati.",true,flagCorretto);
		
	}

	
	public void testMetodiSetterGetterDiZona() {
		
		System.out.println("\n*** testMetodiSetterGetterDiZona() ***\n");

		System.out.println("Aggiungo una zona");
		Zona za = a.aggiungiZona();

		za.setAmpiezza(500);
		za.setTemperaturaMedia(25);
		za.setIrraggiamentoMedio(9);

		System.out.println(" Ampiezza: "+za.getAmpiezza()+" m^2");
		System.out.println(" Temperatura media: "+za.getTemperaturaMedia()+" C");
		System.out.println(" Irraggiamento medio: "+za.getIrraggiamentoMedio()+ " ore");

		if(za.getAmpiezza()==500 && za.getTemperaturaMedia()==25 && za.getIrraggiamentoMedio()==9)
			System.out.println("Implementazione dei metodi getter e setter di Zona corretti");
		else
			System.out.println("Implementazione dei metodi getter e setter di Zona errati");
			
		assertEquals("Ampiezza errata.",500,za.getAmpiezza());
		assertEquals("Temperatura media errata.",25,za.getTemperaturaMedia());
		assertEquals("Irraggiamento medio errato.",9.0f,za.getIrraggiamentoMedio());

	}
	
	
	public void testSpecificaCaratteristicheZona() {

		System.out.println("\n*** testSpecificaCaratteristicheZona() ***\n");

		System.out.println("Aggiungo una zona");
		Zona za = a.aggiungiZona();

		a.specificaCaratteristicheZona(za.getCodice(), "Debolmente acido");

		System.out.println("Definita caratteristica per la zona");
				
		assertNotNull("Errore nell'aggiunta delle caratteristiche.",za.elencoCaratteristiche());
		
		int numero = za.elencoCaratteristiche().size();
		
		if(numero==1)
			System.out.println("Caratteristica aggiunta correttamente");
		else
			System.out.println("Caratteristica non aggiunta correttamente");
		
		assertEquals("Numero di caratteristiche errato.",1,numero);

	}
	
	
	public void testSpecificaPiuCaratteristicheZona() {

		System.out.println("\n*** testSpecificaPiuCaratteristicheZona() ***\n");

		System.out.println("Aggiungo una zona");
		Zona za = a.aggiungiZona();
		
		a.specificaCaratteristicheZona(za.getCodice(), "Debolmente acido");
		a.specificaCaratteristicheZona(za.getCodice(), "Assorbimento meccanico alto");

		System.out.println("Definite due caratteristiche per la zona");
				
		int numero = za.elencoCaratteristiche().size();
		
		if(numero==2)
			System.out.println("Caratteristiche aggiunte correttamente");
		else
			System.out.println("Caratteristiche non aggiunte correttamente");
		
		assertEquals("Numero di caratteristiche errato.",2,numero);

		a.specificaCaratteristicheZona(za.getCodice(), "Debolmente acido");

		System.out.println("Definita una terza caratteristica (duplicata) per la zona");
		
		    numero = za.elencoCaratteristiche().size();
		
		if(numero==2)
			System.out.println("Caratteristica duplicata correttamente scartata");
		else
			System.out.println("Gestione della caratteristica duplicata errata");
		
		assertEquals("Numero di caratteristiche errato.",2,numero);
		
	}	
	
	
	public void testElencoCaratteristiche() {

		System.out.println("\n*** testElencoCaratteristiche() ***\n");

		System.out.println("Aggiungo una zona");
		Zona za = a.aggiungiZona();

		a.specificaCaratteristicheZona(za.getCodice(), "Debolmente acido");
		a.specificaCaratteristicheZona(za.getCodice(), "Assorbimento meccanico alto");
		a.specificaCaratteristicheZona(za.getCodice(), "Potere tampone nella media");

		System.out.println("Definite caratteristiche per la zona");
		    	
		List<String> caratteristiche = new LinkedList<String>(za.elencoCaratteristiche());
	
		for (String s : caratteristiche)
		{
			System.out.println(" "+s);
			
		}
		boolean flagCorretto=false;
		if(caratteristiche.get(0).compareTo("Assorbimento meccanico alto")==0 &&
				caratteristiche.get(1).compareTo("Debolmente acido")==0 &&
				caratteristiche.get(2).compareTo("Potere tampone nella media")==0)
			flagCorretto=true;
		
		if(flagCorretto==true)
			System.out.println("Elenco caratteristiche corretto");
		else
			System.out.println("Elenco caratteristiche errato");

		assertEquals("Elenco caratteristiche errato.",true,flagCorretto);

	}
	
	
	public void testElencoZone() {
		
		System.out.println("\n*** testElencoZone() ***\n");

		System.out.println("Aggiungo tre zone");
		Zona za = a.aggiungiZona();
		za.setAmpiezza(500);
		System.out.println("Aggiunta zona "+za.getCodice());
		Zona zb = a.aggiungiZona();
		zb.setAmpiezza(100);
		System.out.println("Aggiunta zona "+zb.getCodice());
		Zona zc = a.aggiungiZona();
		zc.setAmpiezza(800);
		System.out.println("Aggiunta zona "+zc.getCodice());
		
		System.out.println("Elenco zone (tutte)");	
		List<Zona> zone = new LinkedList<Zona>(a.elencoZone());
		
		assertNotNull("Errore nell'aggiunta delle zone.",zone);
		
		for (Zona ztemp : zone)
		{
			System.out.println(" "+ztemp.getCodice());
		}

		boolean flagCorretto=false;
		if(zone.get(0).getCodice()==1000 &&
				zone.get(1).getCodice()==1001 &&
				zone.get(2).getCodice()==1002)
			flagCorretto=true;
		
		if(flagCorretto==true)
			System.out.println("Elenco zone corretto");
		else
			System.out.println("Elenco zone errato");

		assertEquals("Elenco zone errato.",true,flagCorretto);
		
	}

	
	public void testElencoZoneSopraAmpiezza() {
		
		System.out.println("\n*** testElencoZoneSopraAmpiezza() ***\n");

		System.out.println("Aggiungo tre zone");
		Zona za = a.aggiungiZona();
		za.setAmpiezza(500);
		System.out.println("Aggiunta zona "+za.getCodice());
		Zona zb = a.aggiungiZona();
		zb.setAmpiezza(100);
		System.out.println("Aggiunta zona "+zb.getCodice());
		Zona zc = a.aggiungiZona();
		zc.setAmpiezza(800);
		System.out.println("Aggiunta zona "+zc.getCodice());
		
		System.out.println("Elenco zone (solo quelle ampiezza maggiore di 200 m^2)");	
		List<Zona> zone = new LinkedList<Zona>(a.elencoZone(200));
		
		for (Zona ztemp : zone)
		{
			System.out.println(" "+ztemp.getCodice());
		}

		int numero = zone.size();
		
		if(numero==2)
			System.out.println("Numero di zone corretto");
		else
			System.out.println("Numero di zone errato");

		assertEquals("Numero di zone errato.",2,numero);
		
		boolean flagCorretto=false;
		if(zone.get(0).getCodice()==1000 &&
				zone.get(1).getCodice()==1002)
			flagCorretto=true;
		if(flagCorretto==true)
			System.out.println("Elenco zone corretto");
		else
			System.out.println("Elenco zone errato");

		assertEquals("Elenco zone errato.",true,flagCorretto);
		
	}	
	
	
	public void testCercaZona() {
		
		System.out.println("\n*** testCercaZona() ***\n");

		System.out.println("Aggiungo una zona");
		Zona za = a.aggiungiZona();
		System.out.println("Aggiunta zona "+za.getCodice());

		System.out.println("Cercata la zona 1000");
		Zona zonaCercata = a.cercaZona(1000);
		if(zonaCercata.getCodice()==1000)
			System.out.println("Zona presente trovata");
		else
			System.out.println("Zona presente, ma non trovata");
			
		assertEquals("Errore ricerca zona.",1000,zonaCercata.getCodice());

		System.out.println("Cercata la zona 1001");
		zonaCercata = a.cercaZona(1001);
		if(zonaCercata.getCodice()==1001)
			System.out.println("Zona assente creata");
		else
			System.out.println("Zona assente non creata correttamente");
			
		assertEquals("Errore ricerca (e creazione) zona.",1001,zonaCercata.getCodice());
	}
	
	
}
