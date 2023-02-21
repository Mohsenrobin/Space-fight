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
	private static ScoreSaver scoreSaver = new ScoreSaver();
	

	public void input(){
		try {
			inFile = new FileInputStream("save.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        try {
			in = new ObjectInputStream(inFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
public void output(){
	try {
		outFile = new FileOutputStream("save.txt");
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		out = new ObjectOutputStream(outFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public static ScoreSaver getScoreSaver() {
		return scoreSaver;
	}


	public static void setScoreSaver(ScoreSaver scoreSaver) {
		ScoreSaver.scoreSaver = scoreSaver;
	}

	public FileOutputStream getOutFile() {
		return outFile;
	}

	public void setOutFile(FileOutputStream outFile) {
		this.outFile = outFile;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public FileInputStream getInFile() {
		return inFile;
	}

	public void setInFile(FileInputStream inFile) {
		this.inFile = inFile;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}
}
