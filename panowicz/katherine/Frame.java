package panowicz.katherine;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import animal.Animal;
import animal.carnivore.Lion;
import animal.herbivore.Giraffe;

public class Frame extends JFrame implements ActionListener {

// Variables
	private static int input;
	private static Scanner fileReader;
	private static JButton inputBut, outputBut, doneBut;
	private static JTextField lionInput, giraffeInput, threadsInput;
	private static File inputFile, outputFolder;
	private static ArrayList<Animal> animalList = new ArrayList<Animal>();
	private static ArrayList<Run> threadList = new ArrayList<Run>();
	private static boolean isThreadInput, isLionInput, isGiraffeInput, isInputFile, isOutputFolder = false;

// Constructor
	Frame() {
		// JFrame settings
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("AG Final Project");
		this.setLayout(new GridLayout(0, 1));
		// Button to select input (names file)
		inputBut = new JButton("Select File");
		inputBut.addActionListener(this);
		// Button to select output folder for files
		outputBut = new JButton("Select Output Folder");
		outputBut.addActionListener(this);
		// Button to press once all input is entered
		doneBut = new JButton("Done");
		doneBut.addActionListener(this);

		// Text field to get input for number of lions
		lionInput = new JTextField("How many Lions?");
		lionInput.setHorizontalAlignment(JTextField.CENTER);
		// Make UI easier to use and understand
		lionInput.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (lionInput.getText().equalsIgnoreCase("How many Lions?")) {
					lionInput.setText(null);
				}
			}

			public void focusLost(FocusEvent e) {
				if (lionInput.getText().equals("")) {
					lionInput.setText("How many Lions?");
					isLionInput = false;
				} else {
					isLionInput = true;
				}
			}
		});
// Only accept integers
		lionInput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});

// Input for number of giraffes 
		giraffeInput = new JTextField("How many Giraffes?");
		giraffeInput.setHorizontalAlignment(JTextField.CENTER);
		// Make UI easier to use and understand
		giraffeInput.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (giraffeInput.getText().equalsIgnoreCase("How many Giraffes?")) {
					giraffeInput.setText(null);
				}
			}

			public void focusLost(FocusEvent e) {
				if (giraffeInput.getText().equals("")) {
					giraffeInput.setText("How many Giraffes?");
					isGiraffeInput = false;
				} else {
					isGiraffeInput = true;
				}
			}
		});
    
// Only accept integers
		giraffeInput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});

// Input for number of threads
		threadsInput = new JTextField("How many Threads?");
		threadsInput.setHorizontalAlignment(JTextField.CENTER);
		// Make UI easier to use and understand
		threadsInput.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (threadsInput.getText().equalsIgnoreCase("How many Threads?")) {
					threadsInput.setText(null);
				}
			}

			public void focusLost(FocusEvent e) {
				if (threadsInput.getText().equals("")) {
					threadsInput.setText("How many Threads?");
					isThreadInput = false;
				} else {
					isThreadInput = true;
				}
			}
		});
// Only accept integers
		threadsInput.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					e.consume();
				}
			}
		});
// components and JFrame settings
		this.add(inputBut);
		this.add(outputBut);
		this.add(giraffeInput);
		this.add(lionInput);
		this.add(threadsInput);
		this.add(doneBut);
		this.setSize(400, 400);
		this.setVisible(true);
	}

// Event management
	@Override
	public void actionPerformed(ActionEvent e) {
		// Input button clicked
		if (e.getSource() == inputBut) {
			// File chooser with settings
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
			int response = fileChooser.showOpenDialog(null);
			// Check if file chosen is valid and get path
			if (response == JFileChooser.APPROVE_OPTION) {
				inputFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
				inputBut.setText(inputFile.getPath());
				isInputFile = true;
			}
// Check if input has been received
			else {
				isInputFile = false;
				inputBut.setText("Select File");
			}
		}
    
// Output button clicked
		if (e.getSource() == outputBut) {
			// File chooser with settings
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int response = fileChooser.showOpenDialog(null);
			// Check if file chosen is valid and get path
			if (response == JFileChooser.APPROVE_OPTION) {
				outputFolder = new File(fileChooser.getSelectedFile().getAbsolutePath());
				outputBut.setText(outputFolder.getPath());
				isOutputFolder = true;
			}
			
// Check if input has been received
			else {
				isOutputFolder = false;
				outputBut.setText("Select Output Folder");
			}
		}

// Done button clicked
		if (e.getSource() == doneBut) {
			// Clear the list after every use
			animalList.clear();
// Check to make sure all fields are filled out and start main functions
			if (!(isThreadInput == false || isLionInput == false || isGiraffeInput == false || isInputFile == false
					|| isOutputFolder == false)) {
				input = Integer.parseInt(giraffeInput.getText());
				createAnimal("Giraffe");

				input = Integer.parseInt(lionInput.getText());
				createAnimal("Lion");

				input = Integer.parseInt(threadsInput.getText());
				startThreads();
			}
// Test
			else {
				System.out.println("Nothing happened, please fill out all fields!");
			}
		}
	}

// Create animal using names from a file and add to a list of animals
	private static void createAnimal(String animal) {
		try {
			fileReader = new Scanner(inputFile);
			if (animal.equalsIgnoreCase("Giraffe")) {
				for (int i = input; i > 0; i--) {
					animalList.add(new Giraffe(fileReader.nextLine()));
				}
			} else if (animal.equalsIgnoreCase("Lion")) {
				for (int i = input; i > 0; i--) {
					animalList.add(new Lion(fileReader.nextLine()));
				}
			}
			fileReader.close();
		} catch (Exception e) {
			System.out.println("Error creating an animal!");
		}
	}

	// Start threads
	private static void startThreads() {
		for (int i = 0; i < input; i++) {
			Run run = Run.createAndStart("thread" + i, outputFolder.getAbsolutePath() + "/output" + i + ".txt",
					animalList);
			threadList.add(run);
		}

		for (int i = 0; i < input; i++) {
			try {
				threadList.get(i).getThread().join();
			} catch (Exception e) {
				System.out.println("Error creating, running or finishing threads!");
			}
		}
	}
}
