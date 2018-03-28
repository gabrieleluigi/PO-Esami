package it.polito.po.test;

import aziendaagricola.*;

import junit.framework.TestCase;
import java.util.*;

public class TestR3_Raccolti extends TestCase {

	Azienda a;
	
	public void setUp(){
		a = new Azienda();
    }

	
	public void testNuovoRaccolto()  {
		
		System.out.println("\n*** testNuovoRaccolto() ***\n");

		System.out.println("Aggiungo zona");
		Zona za = a.aggiungiZona();

		Raccolto ra = za.nuovoRaccolto("Fagiolini", "2012-08-28", 900);
					   
		assertNotNull("Errore nell'aggiunta del raccolto.",ra);
		
		ra.setPrezzoIngrosso(200);
		
		System.out.println("Creato raccolto di Fagiolini del 2012-08-28 per la zona (quantita' 900, prezzo 200)");

		if(ra.getProdotto().compareTo("Fagiolini")==0 && ra.getData().compareTo("2012-08-28")==0 && ra.getQuantita()==900 && ra.getPrezzoIngrosso()==200)
			System.out.println("Informazioni specificate per il raccolto corrette");
		else
			System.out.println("Informazioni specificate per il raccolto errate");

		assertEquals("Prodotto errato.","Fagiolini",ra.getProdotto());
		assertEquals("Data errata.","2012-08-28",ra.getData());
		assertEquals("Quantita' errato.",900,ra.getQuantita());
		assertEquals("Prezzo errato.",200.0,ra.getPrezzoIngrosso());
		
	}
	
	
	public void testCercaRaccolto()  {
		
		System.out.println("\n*** testCercaRaccolto() ***\n");

		System.out.println("Aggiungo zona");
		Zona za = a.aggiungiZona();

		Raccolto ra = za.nuovoRaccolto("Fagiolini", "2012-08-28", 900);
					   
		System.out.println("Creato raccolto di Fagiolini del 2012-08-28 per la zona");

		ra = za.cercaRaccolto("Fagiolini", "2012-08-28");
		
		assertNotNull("Errore nella ricerca del raccolto.",ra);

		System.out.println("Ricerca raccolto di Fagiolini del 2012-08-28 per la zona");
		
		if(ra.getProdotto().compareTo("Fagiolini")==0 && ra.getData().compareTo("2012-08-28")==0)
			System.out.println("Risultato ricerca raccolto corretto");
		else
			System.out.println("Risultato ricerca raccolto errato");

		assertEquals("Prodotto errato.","Fagiolini",ra.getProdotto());
		assertEquals("Data errata.","2012-08-28",ra.getData());
		
	}	
	
	
	public void testElencoRaccolti()  {
		
		System.out.println("\n*** testElencoRaccolti() ***\n");

		System.out.println("Aggiungo zona");
		Zona za = a.aggiungiZona();

		System.out.println("Creati raccolti per la zona");
		
		za.nuovoRaccolto("Fagiolini", "2012-08-28", 900);
 		za.nuovoRaccolto("Fagiolini", "2012-09-02", 200);	
 		za.nuovoRaccolto("Zucchine", "2012-06-12", 500);
 		za.nuovoRaccolto("Patate", "2012-06-12", 1000);

		System.out.println("Elenco raccolti per la zona "+za.getCodice());

		List<Raccolto> raccolti = new LinkedList<Raccolto>(za.elencoRaccolti());

		assertNotNull("Errore nell'aggiunta dei raccolti.",raccolti);
		
		for (Raccolto rtemp : raccolti)
		{
			System.out.println(" "+rtemp.getProdotto()+" "+rtemp.getData()+" "+rtemp.getQuantita());
		}
 		
		int numero = raccolti.size();
		
		if(numero==4)
			System.out.println("Numero di raccolti corretto");
		else
			System.out.println("Numero di raccolti errato");

		assertEquals("Numero di raccolti errato.",4,numero);
		
		boolean flagCorretto=false;
		if(raccolti.get(0).getProdotto().compareTo("Zucchine")==0 && raccolti.get(0).getData().compareTo("2012-06-12")==0 && raccolti.get(0).getQuantita()==500 &&
				raccolti.get(1).getProdotto().compareTo("Patate")==0 && raccolti.get(1).getData().compareTo("2012-06-12")==0 && raccolti.get(1).getQuantita()==1000 && 
				raccolti.get(2).getProdotto().compareTo("Fagiolini")==0 && raccolti.get(2).getData().compareTo("2012-08-28")==0 && raccolti.get(2).getQuantita()==900 &&		
				raccolti.get(3).getProdotto().compareTo("Fagiolini")==0 && raccolti.get(3).getData().compareTo("2012-09-02")==0 && raccolti.get(3).getQuantita()==200)  
			flagCorretto=true;

		if(flagCorretto==true)
			System.out.println("Elenco raccolti corretto");
		else
			System.out.println("Elenco raccolti errato");

		assertEquals("Elenco raccolti errato.",true,flagCorretto);
	
	}		
	
	
	public void testAggiungiMagazzino()  {
		
		System.out.println("\n*** testAggiungiMagazzino() ***\n");

		System.out.println("Aggiungo magazzino");
		Magazzino m = a.aggiungiMagazzino("Deposito centrale", "Fagiolini", 1000);

		assertNotNull("Errore nell'aggiunta del magazzino.",m);
		
		System.out.println(" "+m.getNome()+" di "+m.getProdotto()+" (quantita' stoccabile iniziale "+m.getQuantitaStoccabile()+")");

		if(m.getNome().compareTo("Deposito centrale")==0 && m.getProdotto().compareTo("Fagiolini")==0 && m.getQuantitaStoccabile()==1000 && m.getQuantitaStoccata()==0)
			System.out.println("Informazioni specificate per il magazzino corrette");
		else
			System.out.println("Informazioni specificate per il magazzino errate");

		assertEquals("Nome errato.","Deposito centrale",m.getNome());
		assertEquals("Prodotto errato.","Fagiolini",m.getProdotto());
		assertEquals("Quantita' stoccabile errata.",1000,m.getQuantitaStoccabile());
		assertEquals("Quantita' stoccata errata.",0,m.getQuantitaStoccata());				
		
	}
	
	
	public void testStoccaProdottoNonCompatibile()  {
		
		System.out.println("\n*** testStoccaProdottoNonCompatibile() ***\n");

		System.out.println("Aggiungo zona");
		Zona za = a.aggiungiZona();

		System.out.println("Creato raccolto di 200 Patate del 2012-06-12 per la zona");
		
		Raccolto ra = za.nuovoRaccolto("Patate", "2012-06-12", 200);
 	
		System.out.println("Aggiungo magazzino");
		Magazzino m = a.aggiungiMagazzino("Deposito centrale", "Fagiolini", 1000);
		System.out.println(" "+m.getNome()+" di "+m.getProdotto()+" (quantita' stoccabile iniziale "+m.getQuantitaStoccabile()+")");

		System.out.println("Tentativo di stoccaggio del raccolto di 200 Patate del 2012-06-12");
		
		boolean flagEccezione=false;
		try {
			m.stocca(ra);
		} catch (ProdottoNonCompatibileException e) {

			flagEccezione=true;
		}
		
		if(flagEccezione==true)
			System.out.println("Gestione stoccaggio prodotto non compatibile corretta");
		else
			System.out.println("Gestione stoccaggio prodotto non compatibile errata");
			
		assertEquals("Gestione stoccaggio prodotto non compatibile errata.",true,flagEccezione);		
		
	}	
	
	
	public void testStoccaProdottoCompatibileConPrelievo() throws ProdottoNonCompatibileException  {
		
		System.out.println("\n*** testStoccaProdottoCompatibileConPrelievo() ***\n");

		System.out.println("Aggiungo zona");
		Zona za = a.aggiungiZona();

		System.out.println("Creati raccolti per la zona");
		
		Raccolto ra = za.nuovoRaccolto("Fagiolini", "2012-08-28", 900);
 		Raccolto rb = za.nuovoRaccolto("Fagiolini", "2012-09-02", 200);	
 		              za.nuovoRaccolto("Zucchine", "2012-06-12", 500);

		System.out.println("Aggiungo magazzino");
		Magazzino m = a.aggiungiMagazzino("Deposito centrale", "Fagiolini", 1000);
		System.out.println(" "+m.getNome()+" di "+m.getProdotto()+" (quantita' stoccabile iniziale "+m.getQuantitaStoccabile()+")");

		System.out.println("Tentativo di stoccaggio del raccolto di 900 Fagiolini del 2012-08-28");
		
		int q = m.stocca(ra);
		System.out.println(" Quantita ancora stoccabile (o eccesso, se negativo): "+q);
		
		if(q==100)
			System.out.println("Quantita' corretta");
		else
			System.out.println("Quantita' errata");

		assertEquals("Quantita' errata.",100,q);		
			
		System.out.println("Prelievo di una quantita' 30 di prodotto dal magazzino");
		m.preleva(30);

		System.out.println("Tentativo di stoccaggio del raccolto di 200 Fagiolini del 2012-09-02");
		
		    q = m.stocca(rb);
		System.out.println(" Quantita ancora stoccabile (o eccesso non stoccato, se negativo): "+q);
	
		if(q==-70)
			System.out.println("Quantita' corretta");
		else
			System.out.println("Quantita' errata");

		assertEquals("Quantita' errata.",-70,q);		
		
		q = m.getQuantitaStoccata();
		
		System.out.println("Quantita' stoccata dopo i due stoccaggi ed il prelievo: "+m.getQuantitaStoccata());
		if(q==1000)
			System.out.println("Quantita' corretta");
		else
			System.out.println("Quantita' errata");

		assertEquals("Quantita' errata.",1000,q);		
			
	}
	
	
	public void testTotaleMagazini() throws ProdottoNonCompatibileException  {
		
		System.out.println("\n*** testTotaleMagazini() ***\n");

		System.out.println("Aggiungo zona");
		Zona za = a.aggiungiZona();

		System.out.println("Creati raccolti per la zona");
		
		Raccolto ra = za.nuovoRaccolto("Fagiolini", "2012-08-28", 900);
 		Raccolto rb = za.nuovoRaccolto("Fagiolini", "2012-09-02", 200);	
 		Raccolto rc	= za.nuovoRaccolto("Zucchine", "2012-06-12", 500);
		
		System.out.println("Aggiungi tre magazzini");
		Magazzino ma = a.aggiungiMagazzino("Deposito centrale", "Fagiolini", 1000);
		Magazzino mb = a.aggiungiMagazzino("Deposito secondario", "Fagiolini", 800);
		Magazzino mc = a.aggiungiMagazzino("Deposito all'estero", "Zucchine", 1000);

		System.out.println("Stoccaggio del raccolto di 900 Fagiolini");
		ma.stocca(ra);

		System.out.println("Stoccaggio del raccolto di 200 Fagiolini");
		mb.stocca(rb);

		System.out.println("Stoccaggio del raccolto di 500 Zucchine");
		mc.stocca(rc);

		int t = a.totaleMagazzino();
		System.out.println("Quantita' di prodotto totale stoccata "+t);
		
		if(t==1600)
			System.out.println("Quantita' totale stoccata corretta");
		else
			System.out.println("Quantita' totale stoccata errata");

		assertEquals("Quantita' totale stoccata errara.",1600,t);		
		
	}	
	
	
}
