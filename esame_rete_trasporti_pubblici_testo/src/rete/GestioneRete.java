package rete;

import java.util.*;

public class GestioneRete {
	private Map<String, ElementoTopografico> ElementoTopograficoMappa = new TreeMap<String, ElementoTopografico>();
	private Map<Integer, Fermata> FermateMappaId = new HashMap<Integer, Fermata>();
	private Map<String, Fermata> FermateMappaNome = new TreeMap<String, Fermata>();
	private Map<String, Linea> LineaMappa = new HashMap<String, Linea>();
	private List<Linea> LineaLista = new LinkedList<Linea>();
	private Map<Integer, Mezzo> MezziMappa = new HashMap<Integer, Mezzo>();
	private int ID_MEZZO = 10000;

	public ElementoTopografico definisciElementoTopografico(String nome){
		if(ElementoTopograficoMappa.containsKey(nome) == false){
			ElementoTopografico e = new ElementoTopografico(nome);
			ElementoTopograficoMappa.put(nome, e);
			return e;
		}
		return null;
	}
	
	public Collection<ElementoTopografico> elencoElementiTopografici(){
		return ElementoTopograficoMappa.values();
	}
	
	public Fermata definisciFermata(int id, String nome, String nomeElementoTopografico1, String nomeElementoTopografico2) {
		if(cercaFermataPerId(id) == null){
			definisciElementoTopografico(nomeElementoTopografico1);
			definisciElementoTopografico(nomeElementoTopografico2);
			Fermata f = new FermataElementi(id, nome, nomeElementoTopografico1, nomeElementoTopografico2);
			FermateMappaId.put(id, f);
			FermateMappaNome.put(nome, f);
			return f;
		}
		return null;
	}
	
	public Fermata definisciFermata(int id, String nome, String nomeElementoTopografico, int numCivico) {
		if(cercaFermataPerId(id) == null){
			definisciElementoTopografico(nomeElementoTopografico);
			Fermata f = new FermataElemento(id, nome, nomeElementoTopografico, numCivico);
			FermateMappaId.put(id, f);
			FermateMappaNome.put(nome, f);
			return f;
		}
		return null;
	}
	
	public Fermata cercaFermataPerId(int id) {
		if(FermateMappaId.containsKey(id))
			return FermateMappaId.get(id);
		return null;
	}

	public Fermata cercaFermataPerNome(String nome) {
		for(String n: FermateMappaNome.keySet())
			if(n.toLowerCase().contains(nome.toLowerCase()))
				return FermateMappaNome.get(n);
		return null;
	}

	public Collection<Fermata> elencoFermatePerElementoTopografico(String nome){
		if(ElementoTopograficoMappa.containsKey(nome) == false)
			return null;
		List<Fermata> ltemp = new LinkedList<Fermata>();
		for(Fermata e: FermateMappaId.values()){
				if(e instanceof FermataElementi)
					if(((FermataElementi) e).getElemento1().compareTo(nome) == 0 || ((FermataElementi) e).getElemento2().compareTo(nome) == 0)
						ltemp.add(e);
				if(e instanceof FermataElemento){
					if(((FermataElemento) e).getElemento().compareTo(nome) == 0)
						ltemp.add(e);}
		}
		return ltemp;
	}

	public Linea definisciLinea(String numero, int idFermataDa, int idFermataA) throws FermataNonDefinitaException {
		Fermata f1 = cercaFermataPerId(idFermataDa);
		Fermata f2 = cercaFermataPerId(idFermataA);
		if(f1 == null || f2 == null)
			throw new FermataNonDefinitaException();
		FermateMappaId.get(idFermataDa).incrementaNumeroLinee();
		FermateMappaId.get(idFermataA).incrementaNumeroLinee();
		Linea l = new Linea(numero, f1, f2);
		LineaLista.add(l);
		LineaMappa.put(l.getNumero(), l);
		return l;
	}

	public void aggiungiFermataALinea(int idFermata, String numeroLinea) {
		if(cercaFermataPerId(idFermata) != null || LineaMappa.containsKey(numeroLinea) == true) {
			LineaMappa.get(numeroLinea).aggiungiFermata(FermateMappaId.get(idFermata));
			FermateMappaId.get(idFermata).incrementaNumeroLinee();
		}
	}	

	public String stampaElencoFermatePerNome(){
		String result =new String();
		int cont = 1;
		for(String n: FermateMappaNome.keySet()) {
			if(FermateMappaNome.size() == cont)
				result += n+".";
			else {
				result += n+";\n";
				cont ++;
			}
		}
		return result;
	}

	public String stampaElencoFermatePerNumeroLinee(){
		List<Fermata> elenco = new LinkedList<Fermata>(FermateMappaId.values());
		Collections.sort(elenco, new ElencoFermatePerNumeroLineeComparator());
		
		String result =new String();
		int cont = 1;
		
		for(Fermata f: elenco) {
			if(elenco.size() == cont)
				result += f.getNome()+".";
			else {
				result += f.getNome()+";\n";
				cont ++;
			}
		}
		
		return result;
	}

    public Collection<Fermata> elencoFermateLinea(String numeroLinea){
    		if(LineaMappa.containsKey(numeroLinea))
    			return LineaMappa.get(numeroLinea).getFermateLineaLista();
    		return null;
    }

    public Collection<Linea> elencoLineeFermata(int idFermata){
    		List<Linea> elenco = new LinkedList<Linea>();
    		for(Linea l: LineaMappa.values())
    			if(l.getFermateLineaLista().contains(cercaFermataPerId(idFermata)))
    				elenco.add(l);
		return elenco;
    }

    public Mezzo definisciTram() {
    		Tram t = new Tram(ID_MEZZO);
    		MezziMappa.put(ID_MEZZO,t);
    		ID_MEZZO++;
		return t;
    }

    public Mezzo definisciBus(String targa) {
    		Bus b = new Bus(ID_MEZZO, targa);
    		MezziMappa.put(ID_MEZZO, b);
    		ID_MEZZO++;
    		return b;
    }
    
    public void assegnaMezzoALinea(String data, int idMezzo, String numeroLinea) throws AssegnazioneMezzoStessaLineaDataException {
    		if(MezziMappa.containsKey(idMezzo) && LineaMappa.containsKey(numeroLinea)) {
    			if(MezziMappa.get(idMezzo).getServizio().containsKey(data))
    				throw new AssegnazioneMezzoStessaLineaDataException();
    			else
    				MezziMappa.get(idMezzo).aggiungiServizio(data, LineaMappa.get(numeroLinea));
    		}
    	}
    
    public String stampaElencoMezziInCircolazioneInData(String data) {
    		String result =new String();
    		for(Mezzo m: MezziMappa.values())
    			if(m.getServizio().containsKey(data)) {
    				if(m instanceof Bus)
    					result += m.id+" (bus)\n";
    				if(m instanceof Tram)
    					result += m.id+" (tram)\n";
    			}
    				
		return result;
    }
    
}



