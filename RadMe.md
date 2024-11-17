# Smartcard Travel Management System 

### Overview

This project is a Java-based Smartcard Travel Management System designed to handle journeys and associated costs for different types of smartcards: Child (C), Adult (A), and Senior (S). The system provides functionalities for managing smartcards, tracking journeys, calculating fares, and providing summaries. It is designed for scalability and clarity with object-oriented principles.

### Features

	1.	Smartcard Management
	•	Add smartcards with unique IDs.
	•	Assign types: Child (C), Adult (A), or Senior (S).
	•	Maintain a minimum balance of $5.00.
	2.	Journey Management
	•	Add journeys to smartcards (with transport mode validation: train, bus, tram).
	•	Track start and end locations (within valid range 1-10).
	•	Calculate distance for each journey.
	3.	Fare Calculation
	•	Calculate journey costs based on smartcard type and distance.
	4.	Listing and Summaries
	•	List all smartcards and journeys.
	•	Filter journeys by transport mode.
	•	Summarize total journey costs.
	5.	Error Handling
	•	Validates inputs such as transport mode, balance, and journey start/end points.
	•	Prevents adding duplicate IDs or exceeding allowed journeys.

### File Structure

	•	Journey.java: Defines the Journey class to encapsulate details of a journey, including ID, transport mode, start and end locations, and distance.
	•	SmartCard.java: Defines the SmartCard class to manage smartcard details, including ID, type, balance, and associated journeys.
	•	SystemInterface.java: Implements the main interface for managing smartcards and journeys. It handles user input, executes commands, and provides summary outputs.

## Class Descriptions

### Journey.java

	•	Attributes:
	•	journeyID: Unique identifier for the journey.
	•	transportMode: Mode of transport (train, bus, tram).
	•	startOfJourney, endOfJourney: Start and end points (1-10).
	•	distanceOfJourney: Calculated distance between start and end.
	•	Methods:
	•	Getters/Setters for all attributes.
	•	calculateDistance(): Computes the distance based on start and end points.

### SmartCard.java

	•	Attributes:
	•	cID: Unique smartcard ID.
	•	type: Smartcard type (‘C’, ‘A’, ‘S’).
	•	balance: Account balance.
	•	journey1, journey2, journey3: Holds up to three journeys based on type.
	•	Methods:
	•	Getters/Setters for all attributes.
	•	Validations for smartcard type and balance.
	•	Rules for assigning journeys based on card type.

### SystemInterface.java

	•	Functionalities:
	•	User menu with options to add/delete smartcards and journeys.
	•	Validates input and prevents duplicate entries.
	•	Lists smartcards and journeys.
	•	Summarizes journey costs for all cards.

### How to Use

	1.	Clone the repository and open the project in any Java IDE (e.g., IntelliJ IDEA, Eclipse, or VSCode).
	2.	Run the SystemInterface.java file.
	3.	Interact with the program via the console:
	•	Add smartcards and journeys.
	•	View journey details and summaries.
	•	Delete or list smartcards/journeys.

### Prerequisites

	•	Java Development Kit (JDK) 8 or later.
	•	IDE or terminal to compile and run Java programs.

### Input Validation

	•	Smartcard:
	•	Type must be ‘C’, ‘A’, or ‘S’.
	•	Minimum balance: $5.00.
	•	Journey:
	•	Start and end points must be integers between 1-10.
	•	Transport mode must be ‘train’, ‘bus’, or ‘tram’.
