package panowicz.katherine;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import animal.Animal;

public class Run implements Runnable {

// Run class variables
	public String threadName;
	private String filePathWithName;
	private ArrayList<Animal> animalList;
	private Thread thread;

// File variables
	private File outputFile;
	private BufferedWriter fileWriter;

// Constructor
	Run(String threadName, String filePathWithName, ArrayList<Animal> animalList) {
		this.threadName = threadName;
		this.filePathWithName = filePathWithName;
		this.animalList = animalList;
		thread = new Thread(this, threadName);
	}

// Code to be executed
	@Override
	public void run() {
		writeToFile();
	}

 // Write to file
	private void writeToFile() {
		try {
			outputFile = new File("" + filePathWithName);
			fileWriter = new BufferedWriter(new FileWriter(outputFile));
			for (Animal thisAnimal : animalList) {
				fileWriter.write("" + thisAnimal.getName() + " is a " + thisAnimal.getType() + "\n");
			}
			fileWriter.close();
		} catch (Exception e) {
			System.out.println("Error writing to file!");
		}
	}

// Static factory method
	public static Run createAndStart(String threadName, String filePathWithName, ArrayList<Animal> animalList) {
		Run myRun = new Run(threadName, filePathWithName, animalList);
		myRun.thread.start();
		return myRun;
	}

// Allow Main class to join the threads
	public Thread getThread() {
		return thread;
	}
}
