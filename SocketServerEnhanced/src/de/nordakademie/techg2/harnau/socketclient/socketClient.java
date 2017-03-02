package de.nordakademie.techg2.harnau.socketclient;

//OutputStream,inputStream, IOException
import java.io.*;
//Basisklasse f�r Client-Socket-Programmierung
import java.net.*;

public class socketClient {

    public static void main(String[] args) {

        // genutzte Portnummer und Nachricht
        String           serverPort;
        String           serverIP;
        String           message;
        Integer          words;

        // Sockets und Puffer
        Socket           socketClient;
        BufferedReader   bufferRead;
        DataOutputStream bufferWrite;

        System.out.println("*** Starting Java Socket Client... ***");

        // Festlegen der Ziel-IP
        if (args.length>0) {
            serverIP = args[0];
        } else {
            serverIP = "127.0.0.1";
        }

        // Festlegen der Ziel-Portnummer
        if (args.length>1) {
            serverPort = args[1];
        } else {
            serverPort = "57913";
        }

        // Festlegen der Nachricht
        if (args.length>2) {
            message="";
            for (words=2;words<args.length;words++) {
                message = message + args[words]+ " ";
            }
            message = message.substring(0, message.length() - 1);
        } else {
            message = "Hello World!";
        }

        System.out.println("Choosen Server : " + serverIP + ":" + serverPort);
	
        try {

            // Aufbau der Verbindung
            socketClient = new Socket(serverIP, Integer.parseInt(serverPort));
			
            // Datenausgabe an Server
            bufferWrite = new DataOutputStream(socketClient.getOutputStream());
            bufferWrite.writeBytes(message + "\n");
            System.out.println("Sending Message: " + message);
		       
            // Einlesen der Daten
            bufferRead = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            message=bufferRead.readLine();
            System.out.println("Receive Message: " + message);

            // Verbindungsabbau
            socketClient.close();

        } catch (IOException e) {
            System.out.println("Error connecting to server!");
            System.out.println(e);
        }
    }
}
