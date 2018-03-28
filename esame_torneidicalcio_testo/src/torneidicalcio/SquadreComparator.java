package torneidicalcio;

import java.util.Comparator;

public class SquadreComparator implements Comparator<Squadra> {

	@Override
	public int compare(Squadra s1, Squadra s2) {
		return s1.getNome().compareTo(s2.getNome());
	}

}
