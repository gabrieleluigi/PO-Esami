package torneidicalcio;

import java.util.Comparator;

public class TesseratiSquadraComparator implements Comparator<Tesserato> {

	@Override
	public int compare(Tesserato t1, Tesserato t2) {
		if(t1 instanceof Dirigente && t2 instanceof Dirigente) {
			if(t1.getNome().compareTo(t2.getNome()) == 0)
				return t1.getCognome().compareTo(t2.getCognome());
			return t1.getNome().compareTo(t2.getNome());
		}	
		if(t1 instanceof Calciatore && t2 instanceof Calciatore) {
			if(t1.getNome().compareTo(t2.getNome()) == 0)
				return t1.getCognome().compareTo(t2.getCognome());
			return t1.getNome().compareTo(t2.getNome());
		}
		if(t1 instanceof Calciatore || t1 instanceof Dirigente && t1 instanceof Calciatore || t1 instanceof Dirigente) {
			if(t1.getNome().compareTo(t2.getNome()) == 0)
				return t1.getCognome().compareTo(t2.getCognome());
			return t1.getNome().compareTo(t2.getNome());
		}
		return 0;
	}

}
