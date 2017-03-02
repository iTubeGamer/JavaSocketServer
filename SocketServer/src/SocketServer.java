import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
		String serverPort = "2048";
		String message;

		// Sockets und Buffer
		ServerSocket socketServer;
		Socket socketClient;
		BufferedReader bufferRead;
		DataOutputStream bufferWrite;

		System.out.println("*** Starting Java Socket Server ***");

		try {

			socketServer = new ServerSocket(Integer.parseInt(serverPort));

			while (true) {
				
				System.out.println("Listening on port " + serverPort);
				
				try {
					
				socketClient = socketServer.accept();
				System.out.println("Connection established to " + socketClient.getRemoteSocketAddress());
				
				//Einlesen der Daten
				bufferRead = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
				message = bufferRead.readLine();
				System.out.println("Receiving " + message);
				
				//Ausgabe der emfangenen Daten
				bufferWrite = new DataOutputStream(socketClient.getOutputStream());
				bufferWrite.writeBytes(message + "\n");
				System.out.println("Send: " + message);
				
				socketClient.close();
				
				} catch (Exception e){
					System.out.println("Error during client connection");
					e.printStackTrace();
				}
				
			}	

		} catch (Exception e) {
			System.out.println("Error establishing server socket");	
		}
		

	}

}
