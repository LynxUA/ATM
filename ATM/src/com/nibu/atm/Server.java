package com.nibu.atm;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	 
    private String  port;
    
    public Server(String port) {
        this.port = port;
    }
 
    public void runServer() throws IllegalArgumentException, NotBoundException, RemoteException {
        Bank dao = new Bank();
        Registry registry = LocateRegistry.createRegistry(Integer.parseInt(port));
        registry.rebind("Bank", dao);
    }
    
    public static void main(String[] args) throws IllegalArgumentException, RemoteException, NotBoundException {
    	Server server = new Server("1333");
		server.runServer();
    }
}

