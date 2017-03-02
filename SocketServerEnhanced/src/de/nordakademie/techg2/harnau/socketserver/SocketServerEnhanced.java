/*
 *  Der SocketServerEnhanced soll die Architektur von skalierbaren Serverprozessen aehnlich
 *  denen eines Apache Webservers exemplarisch verdeutlichen.
 * 
 *                                        +---------------------+               +---------------------+
 *                                        | ServerSocketThread  |               | SocketThreadRunner  |
 *                              +-------> |                     +-----+-------> |                     |
 *                              |         |                     |     |         | nutzt def. Funktion |
 *                              |         +---------------------+     |         +---------------------+
 *                              |                                     |
 *  +----------------------+    |         +---------------------+     |         +---------------------+
 *  | SocketServerEnhanced |    |         | ServerSocketThread  |     |         | SocketThreadRunner  |
 *  |                      +----+-------> |                     +--   +-------> |                     |
 *  |                      |    |         |                     |     |         | nutzt def. Funktion |
 *  +----------------------+    |         +---------------------+     |         +---------------------+
 *                              |                                     |
 *                              |         +---------------------+     |         +---------------------+
 *                              |         | ServerSocketThread  |     |         | SocketThreadRunner  |
 *                              +-------> |                     +--   +-------> |                     |
 *                                        |                     |               | nutzt def. Funktion |
 *                                        +---------------------+               +---------------------+
 *
 *                                                  ...                                   ...
 *
 * 
 */

package de.nordakademie.techg2.harnau.socketserver;

public class SocketServerEnhanced {

	public static void main(String[] args) {

		// genutzte Portnummern fuer die jeweiligen ServerSockets
		String serverPortEcho = "57913";
		String serverPortUpperCase = "57914";
		String serverPortLowerCase = "57915";
		String serverPortAnswerToTheUltimateQuestionofLife = "57916";

		System.out.println("*** Starting Java Socket Server... ***");

		// Auf Port 57913 starten wir einen ServerSocket, welcher die empfangene
		// Nachricht 1:1 zuruecksendet
		// benutzt Function (EchoPing)
		new Thread(new ServerSocketThread(serverPortEcho, new EchoPing())).start();

		// Auf Port 57914 starten wir einen ServerSocket, welcher die empfangene
		// Nachricht in Grossbuchstaben zuruecksendet
		// benutze Java 8 lambda expressions
		new Thread(new ServerSocketThread(serverPortUpperCase, s -> s.toUpperCase())).start();

		// Auch moeglich waere folgende Notation:
		// new Thread(new ServerSocketThread(serverPortUpperCase, String::toUpperCase)).start();

		// Auf Port 57915 starten wir einen ServerSocket, welcher die empfangene
		// Nachricht in Kleinbuchstaben zuruecksendet
		// benutze Java 8 lambda expressions
		new Thread(new ServerSocketThread(serverPortLowerCase, s -> {
			return s.toLowerCase();
		})).start();

		// Auf Port 57915 starten wir einen ServerSocket, welcher generell mit
		// 42 antwortet
		// benutze Java 8 lambda expressions
		new Thread(new ServerSocketThread(serverPortAnswerToTheUltimateQuestionofLife, s -> "42")).start();

	}

}