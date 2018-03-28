import java.util.*;

import rete.*;

public class Esempio {

	public static void main(String[] args) throws FermataNonDefinitaException, AssegnazioneMezzoStessaLineaDataException {
		
		GestioneRete st = new GestioneRete();

		System.out.println("Definiti elementi topografici");

		st.definisciElementoTopografico("Via Allason");
		st.definisciElementoTopografico("Corso Tazzoli");

		System.out.println("\nElenco elementi topografici (ord. alfabetico):");
		LinkedList<ElementoTopografico> listaElementiTopografici = new LinkedList<ElementoTopografico>(st.elencoElementiTopografici());
		for(ElementoTopografico le : listaElementiTopografici)
			System.out.println(" "+le.getNome()+"");
		
		System.out.println("\nDefinita fermata");
		
		Fermata f1649 = st.definisciFermata(1649, "Poste e Telecomunicazioni Cap.", "Via Allason", "Corso Tazzoli");
		System.out.println(" Id: "+f1649.getId());
		System.out.println(" Nome: "+f1649.getNome());
		System.out.println(" Ubicazione: "+f1649.ubicazione());
		
		st.definisciElementoTopografico("Piazza Carlo Felice");
		
		System.out.println("\nDefinita altra fermata (Porta Nuova Cap.)");
		st.definisciFermata(1871, "Porta Nuova Cap.", "Piazza Carlo Felice", 48);

		System.out.println("\nRicerca fermata che contiene 'Porta': ");

		Fermata fPorta = st.cercaFermataPerId(1871);
		System.out.println(" Id: "+fPorta.getId());
		System.out.println(" Nome: "+fPorta.getNome());
		System.out.println(" Ubicazione: "+fPorta.ubicazione());

		System.out.println("\nDefinita altra fermata (su Corso Tazzoli)");
		st.definisciFermata(1001, "Induno", "Corso Tazzoli", 221);
		//
		System.out.println("\nElenco fermate per el. topografico Corso Tazzoli (ord. arbitrario)");
		LinkedList<Fermata> listaFermate = new LinkedList<Fermata>(st.elencoFermatePerElementoTopografico("Corso Tazzoli"));
		for(Fermata f : listaFermate)
			System.out.println(" "+f.getId()+" "+f.ubicazione());

		System.out.println("\nDefinita linea 12: ");
		Linea l12 = st.definisciLinea("12", 1649, 1871);
		System.out.println(" Numero: "+l12.getNumero());
		System.out.println(" Da: "+l12.getFermataDa().ubicazione());
		System.out.println(" A: "+l12.getFermataA().ubicazione());

		System.out.println("\nAggiunta altra fermata a linea 12");
		st.aggiungiFermataALinea(1001, "12");
		
		System.out.println("\nElenco fermate linea 12 (ord. inserimento, cap. inizio/fine):");
		listaFermate = new LinkedList<Fermata>(st.elencoFermateLinea("12"));
		for(Fermata f : listaFermate)
			System.out.println(" "+f.getId()+" "+f.ubicazione());
		
		System.out.println("\nDefinita altri/e elementi topografici, fermate e linee");
		st.definisciElementoTopografico("Via Martiri della Libertà");
		st.definisciElementoTopografico("Corso Francia");
		st.definisciFermata(1056, "Stazione Mocalieri Cap.", "Via Martiri della Libertà", 24);
		st.definisciFermata(963, "Massaua Cap.","Corso Francia", 342);

		st.definisciLinea("40", 1056, 963);
		st.aggiungiFermataALinea(1001, "40");

		System.out.println("\nElenco fermate (per nome)");
		String risultato = st.stampaElencoFermatePerNome();
		System.out.println(risultato);

		System.out.println("\nElenco fermate (per numero linee decrescente)");
		risultato = st.stampaElencoFermatePerNumeroLinee();
		System.out.println(risultato);
		
		System.out.println("\nElenco linee alla fermata 1001 (ord. arbitrario):");
		LinkedList<Linea> listaLinee = new LinkedList<Linea>(st.elencoLineeFermata(1001));
		for(Linea l : listaLinee)
			System.out.println(" "+l.getNumero());

		System.out.println("\nDefinito mezzo");
		Mezzo mCS456BC = st.definisciBus("CS456BC");
		System.out.println(" Id assegnato: "+mCS456BC.getId());
		
		System.out.println("\nAssegna mezzo a linea 12");
		
		st.assegnaMezzoALinea("20180202", mCS456BC.getId(), "12");
		
		System.out.println("\nStampa mezzi in circolazione in data 20180202 (ordine id mezzo): ");
		risultato = st.stampaElencoMezziInCircolazioneInData("20180202");
		System.out.println(risultato);
		
	}
}









