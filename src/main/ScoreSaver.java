package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ScoreSaver {
	FileOutputStream outFile;
	ObjectOutputStream out;
	FileInputStream inFile;
	ObjectInputStream in;
	private static ScoreSaver scoreSaver = new ScoreSaver();
	

	public void input(){
		try {
			inFile = new FileInputStream("save.data");
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
		outFile = new FileOutputStream("save.data");
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
	
}
