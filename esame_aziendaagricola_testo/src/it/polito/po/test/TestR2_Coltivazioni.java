package it.polito.po.test;

import aziendaagricola.*;

import junit.framework.TestCase;
import java.util.*;

public class TestR2_Coltivazioni extends TestCase {

	Azienda a;
	
	public void setUp(){
		a = new Azienda();
    }
	
	
	public void testAggiungiColtivazione() throws ColtivazioneDuplicataException {
		
		System.out.println("\n*** testAggiungiColtivazione() ***\n");

		System.out.println("Aggiungo zona");
		Zona za = a.aggiungiZona();

		System.out.println("Aggiunta coltivazione alla zona");
		
		Coltivazione c = za.aggiungiColtivazione("Zucchine", 4, 6);

		assertNotNull("Errore nell'aggiunta della coltivazione.",c);
		
		if(c.getProdotto().compareTo("Zucchine")==0 && c.getMeseSemina()==4 && c.getMeseRaccolta()==6)
			System.out.println("Informazioni specificate per la coltivazione corrette");
		else
			System.out.println("Informazioni specificate per la coltivazione errate");

		assertEquals("Prodotto errato.","Zucchine",c.getProdotto());
		assertEquals("Mese di semina errato.",4,c.getMeseSemina());
		assertEquals("Mese di raccolta errato.",6,c.getMeseRaccolta());
		
	}
	
	
	public void testAggiungiColtivazioneParzialmenteDuplicata() {
		
		System.out.println("\n*** testAggiungiColtivazioneParzialmenteDuplicata() ***\n");

		System.out.println("Aggiungo zona");
		Zona za = a.aggiungiZona();

		boolean flagEccezione=false;
		
		try {
			za.aggiungiColtivazione("Zucchine", 4, 6);
			System.out.println("Aggiunta coltivazione alla zona");
			System.out.println("Provo ad aggiungere altra coltivazione alla zona (stesso prodotto, MA mese di semina diverso)");
			za.aggiungiColtivazione("Zucchine", 5, 6);
			
		} catch (ColtivazioneDuplicataException e) {
			flagEccezione=true;
		}

		if(flagEccezione==false)
			System.out.println("Gestione della coltivazione con il solo nome prodotto duplicato corretta");
		else
			System.out.println("Gestione della coltivazione con il solo nome prodotto duplicato errata");

		assertEquals("Scatenata eccezione non giustificata.",false,flagEccezione);

	}
	
	
	public void testAggiungiColtivazioneCompletamenteDuplicata() {
		
		System.out.println("\n*** testAggiungiColtivazioneCompletamenteDuplicata() ***\n");

		System.out.println("Aggiungo zona");
		Zona za = a.aggiungiZona();

		boolean flagEccezione=false;
		
		try {
			za.aggiungiColtivazione("Zucchine", 4, 6);
			System.out.println("Aggiunta coltivazione alla zona");
			System.out.println("Provo ad aggiungere altra coltivazione alla zona (stesso prodotto E stesso mese di semina)");
			za.aggiungiColtivazione("Zucchine", 4, 6);
			
		} catch (ColtivazioneDuplicataException e) {
			flagEccezione=true;
		}

		if(flagEccezione==true)
			System.out.println("Gestione della coltivazione con nome prodotto e mese di semina duplicato corretta");
		else
			System.out.println("Gestione della coltivazione con nome prodotto e mese di semina duplicato errata");

		assertEquals("Eccezione non gestita correttamente.",true,flagEccezione);
		
	}
	
	
	public void testElencoColtivazioni() throws ColtivazioneDuplicataException {
		
		System.out.println("\n*** testElencoColtivazioni() ***\n");
	
		System.out.println("Aggiungo zona");
		Zona za = a.aggiungiZona();

		System.out.println("Aggiunte coltivazioni alla zona");
		
		za.aggiungiColtivazione("Zucchine", 4, 6);
        za.aggiungiColtivazione("Cavolo", 10, 12);
        za.aggiungiColtivazione("Fagiolini", 7, 9);
        
		List<Coltivazione> coltivazioni = new LinkedList<Coltivazione>(za.elencoColtivazioni());

		assertNotNull("Errore nell'aggiunta delle coltivazioni.",coltivazioni);
		
		for (Coltivazione ctemp : coltivazioni)
		{
		System.out.println(" "+ctemp.getProdotto()+" semina nel mese "+ctemp.getMeseSemina()+" raccolta nel mese "+ctemp.getMeseRaccolta());
		}

		int numero = coltivazioni.size();
		
		if(numero==3)
			System.out.println("Numero di coltivazioni corretto");
		else
			System.out.println("Numero di coltivazioni errato");

		assertEquals("Numero di coltivazioni errato.",3,numero);
		
		boolean flagCorretto=false;
		if(coltivazioni.get(0).getProdotto().compareTo("Zucchine")==0 &&
				coltivazioni.get(1).getProdotto().compareTo("Cavolo")==0 && 
				coltivazioni.get(2).getProdotto().compareTo("Fagiolini")==0)
			flagCorretto=true;

		if(flagCorretto==true)
			System.out.println("Elenco coltivazioni corretto");
		else
			System.out.println("Elenco coltivazioni errato");

		assertEquals("Elenco coltivazioni errato.",true,flagCorretto);
		
	}


	public void testRichiedeFertilizzante() throws ColtivazioneDuplicataException {
		
		System.out.println("\n*** testRichiedeFertilizzante() ***\n");

		System.out.println("Aggiungo zona");
		Zona za = a.aggiungiZona();

		System.out.println("Aggiunta coltivazione alla zona");
		
		Coltivazione c = za.aggiungiColtivazione("Zucchine", 4, 6);

		Fertilizzante f1 = c.richiedeFertilizzante('N', "Letame", 20, 4, 60);
		System.out.println("Aggiunto fertilizzante (naturale): "+f1.getNome());

		assertNotNull("Errore nell'aggiunta del fertilizzante.",f1);
		
		boolean flagCorretto=false;
		if(f1 instanceof FertilizzanteNaturale){
			System.out.println("Tipo di fertilizzante corretto");
			flagCorretto=true;
		}
		else
			System.out.println("Tipo di fertilizzante errato");
		
		assertEquals("Tipo di fertilizzante errato.",true,flagCorretto);
		
		FertilizzanteArtificiale f2 = (FertilizzanteArtificiale)c.richiedeFertilizzante('A', "Nitrato potassico", 120, 1, 20);

		assertNotNull("Errore nell'aggiunta del fertilizzante.",f2);
		
		f2.setRischio(99);
		System.out.println("Aggiunto fertilizzante (artificiale): "+f2.getNome());

		flagCorretto=false;
		if(f2 instanceof FertilizzanteArtificiale){
			System.out.println("Tipo di fertilizzante corretto");
			flagCorretto=true;
		}
		else
			System.out.println("Tipo di fertilizzante errato");
		
		assertEquals("Tipo di fertilizzante errato.",true,flagCorretto);
		
		if(f2.getRischio()==99)
			System.out.println("Rischio corretto");
		else
			System.out.println("Rischio errato");
			
		assertEquals("Rischio errato.",99,f2.getRischio());
		
	}	
	
}
