package it.polito.po.test;

import java.io.*;
import java.util.*;

import junit.framework.TestCase;
import aziendaagricola.*;

public class TestR4_CaricamentoDaFile extends TestCase {

	Azienda a;
	
		public void setUp(){
			a = new Azienda();
	    }
	  
	  /**
	   * Create a new temporary file and write the content
	   * @param content
	   * @return the path of the new file.
	   * @throws IOException
	   */
	  private static String writeFile(String content) throws IOException {          
	          File f = File.createTempFile("off","txt");
	          FileOutputStream fos = new FileOutputStream(f);
	          fos.write(content.getBytes());
	          fos.close();
	          return f.getAbsolutePath();
	  }

	  
	  public void testCaricamentoRigaColtivazione() throws IOException {
		  	
			System.out.println("\n*** testCaricamentoRigaColtivazione() ***\n");
	  
			String normale = "C;1001;Patate;4;8";
		  	
		  	String file = writeFile(normale);

		  	a.leggi(file);

			List<Zona> zone = new LinkedList<Zona>(a.elencoZone());

			assertNotNull("Errore nella creazione della zona.",zone);
			
			Zona za = zone.get(0);

			assertNotNull("Errore nella creazione della zona.",za);
			
			List<Coltivazione> coltivazioni = new LinkedList<Coltivazione>(za.elencoColtivazioni());

			assertNotNull("Errore nell'aggiunta della coltivazione.",coltivazioni);

			Coltivazione c = coltivazioni.get(0);
			
			assertNotNull("Errore nell'aggiunta della coltivazione.",c);
			
			System.out.println("Letta coltivazione di "+c.getProdotto()+" semina nel mese "+c.getMeseSemina()+" raccolta nel mese "+c.getMeseRaccolta());

			if(c.getProdotto().compareTo("Patate")==0 && c.getMeseSemina()==4 && c.getMeseRaccolta()==8)
				System.out.println("Informazioni lette per la coltivazione corrette");
			else
				System.out.println("Informazioni lette per la coltivazione errate");

			assertEquals("Prodotto errato.","Patate",c.getProdotto());
			assertEquals("Mese di semina errato.",4,c.getMeseSemina());
			assertEquals("Mese di raccolta errato.",8,c.getMeseRaccolta());
		  	
	  }
	  
	  
	  public void testCaricamentoRigaRaccolto() throws IOException {
		  	
			System.out.println("\n*** testCaricamentoRigaRaccolto() ***\n");
	  
			String normale = "R;1001;Patate;2011-07-12;200";
		  	
		  	String file = writeFile(normale);

		  	a.leggi(file);

			List<Zona> zone = new LinkedList<Zona>(a.elencoZone());

			assertNotNull("Errore nella creazione della zona.",zone);
			
			Zona za = zone.get(0);

			assertNotNull("Errore nella creazione della zona.",za);

			List<Raccolto> raccolti = new LinkedList<Raccolto>(za.elencoRaccolti());

			assertNotNull("Errore nell'aggiunta del raccolto.",zone);
			
			Raccolto ra = raccolti.get(0);
			
			assertNotNull("Errore nell'aggiunta del raccolto.",zone);

			System.out.println("Letto raccolto di "+ra.getProdotto()+" del "+ra.getData()+" quantita' "+ra.getQuantita());
			
			if(ra.getProdotto().compareTo("Patate")==0 && ra.getData().compareTo("2011-07-12")==0 && ra.getQuantita()==200)
				System.out.println("Informazioni lette per il raccolto corrette");
			else
				System.out.println("Informazioni lette per il raccolto errate");

			assertEquals("Prodotto errato.","Patate",ra.getProdotto());
			assertEquals("Data errata.","2011-07-12",ra.getData());
			assertEquals("Quantita' errata.",200,ra.getQuantita());
		  	
	  }	  

}

