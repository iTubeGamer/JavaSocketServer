package de.nordakademie.techg2.harnau.socketserver;

import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class ServerSocketThread implements Runnable {

	private final String serverPort;

	// Sockets und Puffer
	ServerSocket socketServer;
	Socket socketClient;
	String serverIP;
	Function<String, String> funktion;
	
	// Fuer die wirklich arbeitenden Threads benutzen wir einen Threadpool.
	// Threaderzeugung ist teuer, durch den Pool koennen Threads wiederverwendet werden
	ExecutorService threadpool = Executors.newFixedThreadPool(10);

	public ServerSocketThread(String serverPort, Function<String, String> funktion) {
		super();
		this.serverPort = serverPort;
		this.funktion = funktion;
	}

	public void run() {
		try {

			// Abhoeren des Ports starten
			socketServer = new ServerSocket(Integer.parseInt(serverPort));
			serverIP = Inet4Address.getLocalHost().getHostAddress();
			System.out.println("Server is reachable: " + serverIP + ":" + serverPort);

			while (true) {

				System.out.println(serverIP + ":" + serverPort + ": Listening for new socketconnections...");

				try {

					// Verbindung zum Client wird hergestellt
					socketClient = socketServer.accept();
					System.out.println(serverIP + ":" + serverPort + ": Connected client from "
							+ socketClient.getRemoteSocketAddress());

					// Uebergeben des Sockets an einen nebenlaeufigen Thread im Threadpool
					System.out.println(serverIP + ":" + serverPort + ": Delegate client to new thread "
							+ socketClient.getRemoteSocketAddress());
					threadpool.execute(new SocketThreadRunner(socketClient, funktion));

				} catch (Exception e) {
					System.out.println(serverIP + ":" + serverPort + ": Error while client connection!");
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			System.out.println(serverIP + ":" + serverPort + ": Error establishing server socket!");
			e.printStackTrace();
		}

	}

}
