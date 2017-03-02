package de.nordakademie.techg2.harnau.socketserver;

// OutputStream,inputStream, IOException
import java.io.*;

//Client-Socket-Programmierung
import java.net.Socket;
import java.util.function.Function;

public class SocketThreadRunner implements Runnable {
	private final Socket clientSocket;

	BufferedReader bufferRead;
	DataOutputStream bufferWrite;
	String message;
	Function<String, String> funktion;

	public SocketThreadRunner(Socket clientSocket, Function<String, String> funktion) {
		super();
		// Speichern des Uebergebenen Sockets in der private final Variable
		// clientSocket
		this.clientSocket = clientSocket;
		this.funktion = funktion;
	}

	public void run() {
		try {
			// Einlesen der Daten
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			message = bufferRead.readLine();

			System.out.println(clientSocket.getLocalPort() + ": Received Message from: "
					+ clientSocket.getRemoteSocketAddress() + " : " + message);

			// Ausgabestream oeffnen
			bufferWrite = new DataOutputStream(clientSocket.getOutputStream());
			// Ausgabe lt. definiertem Funktionsaufruf zurueckgeben
			bufferWrite.writeBytes(funktion.apply(message) + "\n");
			System.out.println(clientSocket.getLocalPort() + ": Answered Message to: "
					+ clientSocket.getRemoteSocketAddress() + " : " + funktion.apply(message));

		} catch (IOException e) {
			System.out.println(clientSocket.getLocalPort() + ": IO Error in StreamReader!");
			e.printStackTrace();
		}
		try {
			// Verbindungsabbau
			clientSocket.close();
		} catch (IOException e) {
			System.out.println(clientSocket.getLocalPort() + ": Error closing Socket!");
			e.printStackTrace();
		}

	}

}
