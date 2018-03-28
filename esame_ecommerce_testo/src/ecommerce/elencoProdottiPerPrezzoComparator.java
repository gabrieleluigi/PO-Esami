package ecommerce;

import java.util.Comparator;

public class elencoProdottiPerPrezzoComparator implements Comparator<Prodotto> {

	@Override
	public int compare(Prodotto p1, Prodotto p2) {
		return (int) (p1.getPrezzo()*1000 - p2.getPrezzo()*1000);
	}

}
