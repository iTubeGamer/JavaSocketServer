package de.nordakademie.techg2.harnau.socketserver;

import java.util.function.Function;

// die Klasse EchoPing implementiert die Funktion, dass der Eingabestring zurückgegeben wird.
public class EchoPing implements Function<String, String> {

	public EchoPing() {
	}

	public String apply(String s) {
		return s;
	}

}
