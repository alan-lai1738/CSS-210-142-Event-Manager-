// Alan Lai
// 8/9/2019 - 8/14/2019
// Assignment 4: Event Manager
package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EventManager {
	public static void main(String[] args) {
			File file = new File ("data.csv");
	// STEP 1: Count the number of lines in data.csv
    	try{Scanner sc = new Scanner (file);
			int numberOfLines = 0;								//
			while (sc.hasNextLine()) {
				numberOfLines++;
				sc.nextLine();
			}	
			sc.close();
			// Number of Lines Including Header Line: 530616
			// Number of Events = numberOfLines-1
			// This array will have numberOfLines-1 amount of elements.
			Event[] events = new Event [numberOfLines-1];	// default:null / Events Array.
			
	// STEP 2: Tokenize the elements of each event line and store into the Event object array.
			sc = new Scanner (file);
			sc.nextLine();
			int i = 0;										// We are storing into index 0 to start.
			// This while loop stores the values of each line in to an events array. 
			while (sc.hasNextLine()){
				String line = sc.nextLine();
				Scanner lineScanner = new Scanner(line);
				lineScanner.useDelimiter(",");				// Data.csv uses "," as a separator
				events[i] = new Event();
				events[i].id = lineScanner.nextInt();
				events[i].type = lineScanner.next();
				events[i].dateTime= lineScanner.next();
				events[i].address = lineScanner.next();
				events[i].sector = lineScanner.next();
				events[i].zone = lineScanner.next();
				i++;
				lineScanner.close();
			}
			sc.close();
			
	// STEP 3: Count for #of events in each year.
			int count2015 = 0;	
			int count2016 = 0;
			int count2017 = 0;
				// For Loop processes every event[].date and find the year, then increments the year
				for (int j = 0; j < events.length; j++) {		
				Scanner dateTimeScanner = new Scanner(events[j].dateTime);
				String date = dateTimeScanner.next();
				events[j].date = date;
				String time = dateTimeScanner.next();
				events[j].time = time;
				Scanner dateScanner = new Scanner (date);
				dateScanner.useDelimiter("/");
				dateScanner.nextInt();
				dateScanner.nextInt(); 
				int year = dateScanner.nextInt(); 
					if (year==15) {
						count2015++;}
					if (year==16) {
						count2016++;}
					if (year==17) {
						count2017++;}
				}
			
			System.out.println("Total Events: " + (events.length));
			System.out.println("2015: " + count2015 + " events");
			System.out.println("2016: " + count2016 + " events");
			System.out.println("2017: " + count2017 + " events");
			System.out.println("Total Events: " + (events.length));
			
	// STEP 4: Menu Launch
			// Menu operation that accepts the Event parameter and the event.length
			menu(events, events.length);					
			
    	}catch(FileNotFoundException e) {
			System.out.print("File not found");
    		} 
	}
	
	// Method menu opens the search menu.
	private static void menu(Event[] events, int eventsCount) {
		Scanner chosenOperation = new Scanner(System.in);
		System.out.println("\n++++++++++++++++++++++++++++++");
		System.out.println("Seattle 911 Event Search Manager:"
				+ "\n1- Search by Date"
				+ "\n2- Search by Type"
				+ "\n3- Quit");
		System.out.print("Choose a search Operation: ");
	try {	int operation = chosenOperation.nextInt();

		// If User Chooses Operation 1, we search by date.
			if (operation == 1){
				Scanner dateInput = new Scanner(System.in);
				System.out.print("Enter date (dd/mm/yy):");
				String eventDate = dateInput.next();
				int eventCount = 0;
				// We use a for loop to check the date from every line on data.csv
					for (int j = 0; j < events.length; j++) {
						String b = eventDate;
						// If the contents in event[j] equals the inputed date, the Event's contents will be printed (and counted).
						if ((events[j].date.equals(b))) {
							System.out.print("Event-" + eventCount + "------------------------"
							+	"\nType:" + events[j].type 
							+	"\nDate:" + events[j].date
							+	"\nTime:" + events[j].time
							+	"\nAddress:" + events[j].address
							+	"\nSector:" + events[j].sector
							+	"\nZone:" + events[j].zone + "\n");
						eventCount++;}
				}
				System.out.println("There are " + (eventCount-1) + " events.");
				// The search menu keeps running.
					menu(events, events.length);
			}
		
		// If User Chooses Operation 2, we search by keyword and sector.
			if (operation == 2) {
				Scanner typeInput = new Scanner(System.in);
				System.out.print("Enter keyword for type: ");
				String eventType = typeInput.next();
				eventType = eventType.toUpperCase();
				System.out.print("Enter sector: ");
				String eventSector = typeInput.next();
				eventSector = eventSector.toUpperCase();
				int eventCount = 1;
				// We use a loop to check the type AND sector from every line on data.csv
					for (int j = 0; j < events.length; j++) {
						Scanner strScanner = new Scanner (events[j].type);
						String a = strScanner.next();
						String b = eventType;
						String c = eventSector;
						// If the first word in event[].type matches the type keyword AND sector[].type matches the sector, print the event.
						if (a.equals(b) && events[j].sector.equals(c)) {
							System.out.print("Event-" + (eventCount) + "------------------------"
							+	"\nType:" + events[j].type 
							+	"\nDate:" + events[j].date
							+	"\nTime:" + events[j].time
							+	"\nAddress:" + events[j].address
							+	"\nSector:" + events[j].sector			
							+	"\nZone:" + events[j].zone + "\n");
						eventCount++;}
				}
				System.out.println("There are " + (eventCount-1) + " events.");
				// The search menu keeps running.
					menu(events, events.length);}
		// If User chooses Operation 3, Quit.
			if (operation == 3){
			System.out.print("Stay safe!");}
			
			}catch(InputMismatchException e) {
				System.out.print("Invalid Input: Please enter a value from 1 to 3");
				menu(events, events.length);}
	}
}
	
	


