package battleship;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;


/*
 * Minimum:
 * TODO: Comment. Oh THE COMMENT!
 *  
 * TODO: Make the game follow teh rulez
 * [X] boats at least placed 1 square away from each other - fixed for human and computer alike
 * 	[X] Create a way to compare two coordinates
 * 		*** Created the isCoordinate (better name?) functions in coordinate ***
 * 
 * TODO: super: Player
 * [X] draw board for players
 * [X] MakeMove - a super one - should probably be the same for both human and computer
 * 		***works for the human player***
 * 		***computerplayer got a randomized version***
 * 
 * 
 * 
 * TODO: Human Player:
 *	[X] Draw player entered ship on board.
 * 	[X]: Make a move
  	* 	[X] Handle collisions (if we with collision meant "the same player hitting on the same square twice" or something like that)
 * 		[X] Basic textinput (x,y)
 * 			[X] incorrect input should be fixed
 * 			(TODO: Advanced placement w keyarrows)
 * 
 * TODO: Computer player.
 * 	[X] Place ships (random)
 * 
 * 	TODO: Make a move (pure random, peeking or something with memory?)
 * 		[X] random
 * 
 * [X] Create a functioning game-loop (not over internet)
 * 		***should probably not be a static thing. Alpha down below in main.
 * 		***should it be it's own object/class or something?
 * 
 * TODO: Determine winner.
 * TODO: Count misses
 * TODO: Count hits.
 * 	(Keep track of number of # hits and # turns? )
 * 
 * TODO: Design
 *	TODO: Make UML
 * 
 * 
 * If time allows for it:
 * TODO: more rules? OwO
 * 		alternatives:
 * 		[ ] you may fire once per ship in your fleet that has yet to be damaged, or once if all ship has been damaged
 * 		[ ] may fire once per unsunk ship
 * 		[ ] may fire three times per turn, but may not know what shot hit what (like "you fired at (0,0),(1,1),(2,2). You had 2 hits and 1 miss")
 * 		[ ] if you sink a ship you may fire again
 * 
 * [X] Add support for more shiptypes <- begun
 *	***can choose a different fleet***
 *	[X] display all the ships in the active fleet
 *	[X] create own fleet
 * 
 * 
 * TODO: Network-play !!! Will probably have to rewrite a lot !!!
 * TODO: Highscore
 * 	TODO: Store permanently on file.
 * 		TODO: Encryption
 * 			TODO: Store on server. SSL. Mums!
 * TODO: Graphics
 * 	TODO: Tweak the drawing of game board for better placements.

 */


/**
 * Battleship, the game based on the movie with the same name.
 * To create your own fleet to use add a new file in the BattleShip directory containing the lengths of all ships you want to use separated by commas
 * (For example: a file containing "1,1,1,2,2,3,4,5" will create a fleet of 3 ships with length 1, two ships with length 2 and one ship each of length 3,4 and 5)
 * 
 * @author Victor,Wiktor
 *
 */
public class Main {
		static int size = 10;
		static String source = "defaultFleet.txt";
		static int nofHumanPlayers = 0;
		static int nofComputerPlayers = 0;
		static int computerSkill = 0;
	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		printLogo();
		
		boolean quitMenu = false;
		while(!quitMenu){
			printMenu();
			
			try{
				Scanner scanner = new Scanner(System.in);
				int inNumber = scanner.nextInt();
				//scanner.close(); // Will close close ALL system.in reading??
				switch (inNumber) {
				
				case 1: //Spelplan
					System.out.println("Ange ny storlek på spelplan: ");
					try{
						// Need local instance of scanner so that the main-scanner won't 
						// think of this input as an unvalid menu-option
						Scanner intScanner = new Scanner(System.in);
						size = intScanner.nextInt();
						//intScanner.close();
					}
					catch(InputMismatchException err){
						printNumberInputError();
						
					}
					break;
					
				case 2: //nrHumanplayers
					System.out.println("Ange antal mänskliga spelare: ");
					try{
						Scanner intScanner = new Scanner(System.in);
						nofHumanPlayers = intScanner.nextInt();
					}
					catch(InputMismatchException err){
						printNumberInputError();
					}
					break;
					
				case 3: //nrComputerPlayers
					System.out.println("Ange antal datorspelare: ");
					try{
						Scanner intScanner = new Scanner(System.in);
						nofComputerPlayers = intScanner.nextInt();
					}
					catch(InputMismatchException err){
						printNumberInputError();
					}
					break;
					
				case 4: //choose which fleet to use
					chooseFleet();
					break;
					
				case 5: //show ships in fleet
					System.out.println("Visar skepp i flotta:");
					showFleet();
					System.out.println("Varje # representerar en ruta på spelplanen");
					break;
					
				case 6://show highscore
					System.out.println("Highscore.");


					ArrayList<HighscoreEntry> highscore = new ArrayList<HighscoreEntry>();
					try {
						BufferedReader br = new BufferedReader(new FileReader("highscore.txt"));
						String line = br.readLine();

						while(line != null){
							String[] score = line.split(";");
							highscore.add(new HighscoreEntry(score[0],Integer.parseInt(score[1]),Long.parseLong(score[2])));
							line = br.readLine();
						}
						br.close();
						Collections.sort(highscore);
						int i = 1;
						for (HighscoreEntry entry : highscore) {
							java.util.Date time=new java.util.Date((long)entry.timestamp());
							System.out.println(i + ": " + entry.nofTurns() + " <- " + entry.alias() + " @ " + time);
							i++;
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					break;
					
				case 7://start game
					// Non-interactive way of starting the game. Just does not care about 
					// menu-options..
					Player player1 = new ComputerPlayer(size,"HAL",source);
					Player player2 = new ComputerPlayer(size,"BMO",source);
					new GameLoop(size, player1, player2);
					break;
					
				case 8://define fleet
					createFleet();
					break;
					
				case 9://quit
					System.out.println("Tråkigt att du vill avsluta, bye!");
					quitMenu = true;
					break;
					
				// Wrong menu-option (as integer)
				default:
					printNumberInputError();
					break;
				}
			}
			catch(InputMismatchException err){
				// Not a number entered as menu-option
				printNumberInputError();
				}
		}
		
	}
	

	/**
	 * print the logo
	 */
	private static void printLogo() {
		// TODO Auto-generated method stub
		System.out.println("  ____        _   _   _      ____  _     _       ");
		System.out.println(" | __ )  __ _| |_| |_| | ___/ ___|| |__ (_)_ __  ");
		System.out.println(" |  _ \\ / _` | __| __| |/ _ \\___ \\| '_ \\| | '_ \\ ");
		System.out.println(" | |_) | (_| | |_| |_| |  __/___) | | | | | |_) |");
		System.out.println(" |____/ \\__,_|\\__|\\__|_|\\___|____/|_| |_|_| .__/ ");
		System.out.println("                                          |_|    ");
		System.out.println("	-Based on a true story");
	}

	/**
	 * Print the menu
	 */
	private static void printMenu(){
		System.out.println();
		System.out.println("MENY:");
		System.out.println("1. Ändra storlek på spelplan. Nuvarande: " + size);
		System.out.println("2. Ändra antal mänskliga spelare. Nuvarande: "+ nofHumanPlayers);
		System.out.println("3. Ändra antal datorspelare. Nuvarande: " + nofComputerPlayers);
		System.out.println("4. Välj flotta. Nuvarande:" + source);
		System.out.println("5. Lista skepp i flottan");
		System.out.println("6. Se highscore");
		System.out.println("7. Starta spelet!");
		System.out.println("8. Skapa en egen flotta ");
		System.out.println("9. Avsluta");
		System.out.println("Ange ditt val som ett heltal, bekräfta med enter:");
	}
	
	/**
	 * print the menu of the createFleet function
	 */
	private static void printCreateFleetMenu(){
		System.out.println("Är du nöjd med denna flotta?");
		System.out.println("1. \"Flottan var dålig, jag vill börja om!\"");
		System.out.println("2. Spara flotta och återgå till huvudmenyn");
		System.out.println("3. Återgå till huvudmenyn utan att spara flottan");
		System.out.println("Ange ditt val som ett heltal, bekräfta med enter:");
	}

	/**
	 * print a message to the user if the input is incorrect
	 */
	private static void printNumberInputError() {
		// TODO Auto-generated method stub
		System.out.println("Du måste ange ett giltigt nummer, var god försök igen.");
	}
	
	/**
	 * function to change the source-file and thereby change the fleet used in the game
	 */
	private static void chooseFleet() {
		// TODO Auto-generated method stub
		System.out.println("Valbara flottor:");
		@SuppressWarnings("resource")
		Scanner stringScanner = new Scanner(System.in);
		Boolean correct = false;
		//list fleets
		listFleets();
		String newSource;
		try {
			while(!correct){
				System.out.println("Välj vilken flotta du vill använda - skriv in fullständiga namnet, inkulsive Fleet.txt ");
				System.out.println("Trycker du på enter utan att skriva in namnet på någon flotta kommer defaultFleet.txt att användas.");
				newSource = stringScanner.nextLine();
				if(newSource.equals("") || newSource == null){
					source = "defaultFleet.txt";
					break;
				}
				correct = checkFleet(newSource);
				if(correct){
					source=newSource;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Function used to list the fleets (= files in the directory whose name ends with Fleet.txt)
	 */
	private static void listFleets() {
		String path = "."; //current directory
		String fileName;
		File folder = new File(path); //get folder from directory
		File[] listOfFiles = folder.listFiles(); //get list files from folder 
		for (int i = 0; i < listOfFiles.length; i++){ //for each file in the list
			if (listOfFiles[i].isFile()) { //if it's a file...
				fileName = listOfFiles[i].getName(); //get name of file
				if (fileName.endsWith("Fleet.txt")){ //if it's a Fleet
					System.out.println("> "+fileName); //print name
					}
				}
			}
		System.out.println();
		}
	
	/**
	 * Checks if the name of the fleet is among the files that end with Fleet.txt 
	 * @param fleetName - the name of the fleet the user want to use.
	 * @return true if the name is among the Fleets.txt:s
	 */
	private static boolean checkFleet(String fleetName) {
		Boolean correctName=false;
		String path = "."; //current directory
		File folder = new File(path); //get folder from directory
		File[] listOfFiles = folder.listFiles(); //get list files from folder 
		for (int i = 0; i < listOfFiles.length; i++){ //for each file in the list
			if (listOfFiles[i].isFile()) { //if it's a file...
				if (listOfFiles[i].getName().endsWith("Fleet.txt")){ //if it's a Fleet
					if(listOfFiles[i].getName().equals(fleetName)){ //if the chosen fleetname is among the files
						correctName = true; //the name is correct - break the loop
						break;
						}
					}
				}
			}
		if(!correctName){
			System.out.println("Felaktigt namn, var god försök igen");
			}
		return correctName;
		}
	
	/**
	 * Show the ship in the current active fleet.
	 */
	private static void showFleet(){
		try {
			//create reader
			BufferedReader in = new BufferedReader(new FileReader(source)); 
			
			while(!in.ready()){
				//wait until reader is ready.
			}
			//import the first line and split it
			String importString =in.readLine();
			String[] importFleet = importString.split(",");
			
			//for every ship-type in the "split string"
			for (int i = 0; i < importFleet.length; i++) {
				 for (int j = 0; j < Integer.parseInt(importFleet[i]); j++) {
					 System.out.print('#');					
				}
				 System.out.println();
			}
		} catch(Exception e){
			//should not happen
		}
	}
	
	/**
	 * Function that let the user define his or her own fleet.
	 */
	private static void createFleet() {
		String createdFleet="";
		String input;
		int lenght;
		int amount;
		Scanner scanner = new Scanner(System.in);
		while(true){
			try {
				System.out.println("Hur lång vill du att skeppstypen skall vara? För att avsluta, tryck på enter utan att skriva i något.");
				input = scanner.nextLine();
				if (input.equals("")) {
					break;
				}else{
					lenght = Integer.parseInt(input);
				}
				System.out.println("Hur många skepp du vill ha av den längden?");
				amount = Integer.parseInt(scanner.nextLine());
				//Add to string
				for(int i = 0; i < amount; i++){
					createdFleet = createdFleet + lenght+",";
				}
				} catch (InputMismatchException e) {
					printNumberInputError();
				} catch (NumberFormatException e){
					System.out.println("Endast siffror!");
				}
			}
		if(createdFleet.length()>0){
			createdFleet = createdFleet.substring(0, createdFleet.length()-1);
		}
		System.out.println();
		System.out.println("Flottan ser ut på följande sätt:");
		String[] createdFleetArray = createdFleet.split(",");
		for (int i = 0; i < createdFleetArray.length; i++) {
			 for (int j = 0; j < Integer.parseInt(createdFleetArray[i]); j++) {
				 System.out.print('#');					
			}
			 System.out.println();
		}
		printCreateFleetMenu();
		boolean quit = false;
		int inNumber;
		while(!quit){
			inNumber = scanner.nextInt();
			
			switch (inNumber) {
			case 1:
				quit = true;
				createFleet(); //create a new fleet
				break;
			case 2:
				saveFleet(createdFleet,scanner);
			case 3:
				quit = true;
				break;
			default:
				break;
				}
			}
		}

	/**
	 * save the fleet. get a name from the user and save the fleet as [name]Fleet.txt
	 * @param createdFleetArray
	 * @param scanner
	 */
	private static void saveFleet(String createdFleet, Scanner scanner) {
		System.out.println("Spara flottan!");
		System.out.println("Skriv in flottans namn:");
		System.out.println("(Om du ger flottan samma namn som en redan existerande flotta skrivs den över)");
		String name = scanner.next();
		name = name+"Fleet.txt";
		System.out.println("Flottan sparas i filen " +name);
		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(name, false)));
			writer.print(createdFleet);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
}
