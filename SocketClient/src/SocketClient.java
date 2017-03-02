import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClient {

	public static void main(String[] args) {
		String serverPort = "2048";
		String serverIP = "127.0.0.1";
		String message = "Halli hallo Welt!";

		// Sockets und Buffer
		Socket socketClient;
		BufferedReader bufferRead;
		DataOutputStream bufferWrite;

		System.out.println("*** Starting Java Socket Client ***");

		try {
			// Aufbau der Verbindung zum Server
			System.out.println("Trying to connect to " + serverIP + ":" + serverPort);
			socketClient = new Socket(serverIP, Integer.parseInt(serverPort));

			// Datenausgabe
			bufferWrite = new DataOutputStream(socketClient.getOutputStream());
			bufferWrite.writeBytes(message + "\n");
			System.out.println("Send: " + message);

			// Einlesen der Antwort
			bufferRead = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
			message = bufferRead.readLine();
			System.out.println("Received: " + message);
			
			socketClient.close();

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}

}
