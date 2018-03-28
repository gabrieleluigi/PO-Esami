package ecommerce;

import java.util.*;

public class UtenteRegistrato extends Utente{

	private String username;
	private String password;
	private boolean log;
	private Map<Double, String> storico = new TreeMap<Double, String>();

	public UtenteRegistrato(int codice, String nome, String cognome, String email, String indirizzo, String username, String password) {
		super(codice, nome, cognome, email, indirizzo);
		this.username = username;
		this.password = password;
		this.log = false;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void login(String username, String password) throws EccezioneLoginFallito{
		if(this.username.compareTo(username) != 0 || this.password.compareTo(password) != 0)
			throw new EccezioneLoginFallito();
		this.log = true;
	}

	public void logout(){
		this.log = false;
	}
	
	public boolean isLoggato() {
		return this.log;
	}
	
	public double paga(String data){
		double d = totCarrello;
		listaCarrello.clear();
		listaCarrelloND.clear();
		if(isLoggato())
			storico.put(totCarrello,data +" "+ Double.toString(totCarrello));
		totCarrello = 0;
		return d;
	}
	
	public String storicoAcquisti(){
		String s = "";
		for(String t: storico.values())
			s += t+";";
		return s;
	}
	
}
