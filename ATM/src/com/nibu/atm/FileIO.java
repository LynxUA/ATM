package com.nibu.atm;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class FileIO {
	/**
	 * Loads accounts information from the file
	 * @param fileName
	 */
	public static Object loadFile(String fileName) {
		Object result = new Object[2];
		try(FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn)) {
			result = in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Unable to load accounts from " + fileName);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Saves accounts information to local file
	 * @param fileName
	 * @throws IOException 
	 */
	public static void saveFile(Serializable[] s, String fileName){
		try {
			 FileOutputStream fileOut = new FileOutputStream(fileName);
			 ObjectOutputStream out = new ObjectOutputStream(fileOut);
			 out.writeObject(s);
			 out.close();
			 fileOut.close();
		} catch (IOException e) {
			System.out.println("Unable to save accounts to " + fileName);//
			e.printStackTrace();
		}
		
	}
}
