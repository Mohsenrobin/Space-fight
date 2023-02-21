package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ScoreSaver {
	private FileOutputStream outFile;
	private ObjectOutputStream out;
	private FileInputStream inFile;
	private ObjectInputStream in;
	private static final ScoreSaver scoreSaver = new ScoreSaver();
	

	public void input(){
		try {
			inFile = new FileInputStream("save.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
        try {
			in = new ObjectInputStream(inFile);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
public void output(){
	try {
		outFile = new FileOutputStream("save.txt");
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		out = new ObjectOutputStream(outFile);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	public static ScoreSaver getScoreSaver() {
		return scoreSaver;
	}


	public FileOutputStream getOutFile() {
		return outFile;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public FileInputStream getInFile() {
		return inFile;
	}

	public ObjectInputStream getIn() {
		return in;
	}

}
