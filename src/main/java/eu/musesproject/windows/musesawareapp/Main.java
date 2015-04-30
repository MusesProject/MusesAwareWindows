package eu.musesproject.windows.musesawareapp;

import java.rmi.RemoteException;

public class Main {

	public static void main(String[] args) {
		System.out.println("Aware app started ..");
		RMIClient rmiClient;
		try {
			rmiClient = new RMIClient();
			rmiClient.sendActionToMuses("test");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
