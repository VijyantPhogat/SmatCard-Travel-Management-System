package javaxass1;


import java.sql.SQLOutput;
import java.util.Scanner;

public class SystemInterface {private static Scanner ab = new Scanner(System.in);
    public static SmartCard SCard1, SCard2, SCard3;
 static int a=0, b=0, c=0;
    public static void main (String[] args) {
        int option;
        do {
            System.out.println("* * * * || Smartcard Travel Management System || * * * *");
            System.out.println("1. Add Smartcard");
            System.out.println("2. Add Journey");
            System.out.println("3. Delete Smartcard");
            System.out.println("4. Delete Journey");
            System.out.println("5. List Smartcards");
            System.out.println("6. List Journeys on a Smartcard");
            System.out.println("7. List Journeys by Transport Mode");
            System.out.println("8. Summary of Fares");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            option = ab.nextInt();

            switch (option) {
                case 1:
                    addSmartCard(); // for adding smart card
                    break;
                case 2:
                    addJourney(); // to add journey
                    break;
                case 3:
                    deleteSmartCard(); // to delete a smartcard
                    break;
                case 4:
                    deleteJourney(); // to delete a journey
                    break;
                case 5:
                    listSmartCards(); // to list smart card
                    break;
                case 6:
                    listJourneysOnCard(); // to list journey on smartcards
                    break;
                case 7:
                    listJourneysByTransportMode(); // list journey by the transport mode used
                    break;
                case 8:
                    summarizeCosts();//to view fares
                    break;
                case 9:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 9.");
            }
        } while (option != 9);
    }

//adding a smartcard
    private static void addSmartCard() {
        if (SCard1 != null && SCard2 != null && SCard3 != null)//if card is not null then displays a message
        {
            System.out.println("Maximum number of smartcards reached. Cannot add more.");
            return;
        }
        System.out.print("Enter Smartcard ID: ");
        int id = ab.nextInt();
//checking if the id already exists or not
        if ((SCard1 != null && SCard1.getCardID() == id) ||
                (SCard2 != null && SCard2.getCardID() == id) ||
                (SCard3 != null && SCard3.getCardID() == id)) {
            System.out.println("Smartcard ID already exists. Please use a different ID.");
            return;
        }
//asking user to enter thr type of smartcard (C for child),(A for adult),(S for seniour)
        System.out.print("Enter Smartcard Type (C/A/S) 'C' for Child, 'A' for Adult, or 'S' for Senior : ");
        char type = ab.next().toUpperCase().charAt(0);
        while (type != 'C' && type != 'A' && type != 'S') {
            System.out.println("Invalid type. Please enter 'C' for Child, 'A' for Adult, or 'S' for Senior.");
            type = ab.next().toUpperCase().charAt(0);
        }
//asking user to enter balance
        System.out.print("Enter balance (minimum $5.00): ");
        float balance = ab.nextFloat();
        while (balance < 5.0f) {
            System.out.println("Balance too low. Please enter a balance of at least $5.00.");
            balance = ab.nextFloat();
        }
//creating and adding a new smart card
        SmartCard newCard = new SmartCard();
        newCard.setCardID(id);
        newCard.setType(type);
        newCard.setBalance(balance);
        if (SCard1 == null)
        {
            SCard1 = newCard;
        }
        else if (SCard2 == null)
        {
            SCard2 = newCard;
        }
        else if (SCard3 == null)
        {
            SCard3 = newCard;
        }
        System.out.println("Smartcard added successfully.");
    }
    //findind a smartcard using its card id
    //if found then returns the id otherwise null
    private static SmartCard findCardById(int cId) {
        if (SCard1 != null && SCard1.getCardID() == cId) {
            return SCard1;
        }
        if (SCard2 != null && SCard2.getCardID() == cId) {
            return SCard2;
        }
        if (SCard3 != null && SCard3.getCardID() == cId) {
            return SCard3;
        }
        return null;
    }
    //adding a journey on card
    private static void addJourney() {

        System.out.println("Enter Smartcard ID for the journey:");
        int cId = ab.nextInt();
        SmartCard card = findCardById(cId);
        if (card == null) {
            System.out.println("No smartcard found with ID: " + cId);
            return;
        }

        System.out.print("Enter Journey ID: ");
        int journeyId = ab.nextInt();
        if (card.getJourney1() != null && card.getJourney1().getJourneyID() == journeyId ||
                card.getJourney2() != null && card.getJourney2().getJourneyID() == journeyId ||
                card.getJourney3() != null && card.getJourney3().getJourneyID() == journeyId) {
            System.out.println("Journey ID already exists for this card. Please use a different ID.");
            return;
        }

        System.out.print("Enter transport mode (train/bus/tram): ");
        String mode = ab.next().toLowerCase();
        while (!mode.equals("train") && !mode.equals("bus") && !mode.equals("tram")) {
            System.out.println("Invalid transport mode. Please enter 'train', 'bus', or 'tram'.");
            mode = ab.next().toLowerCase();
        }

        System.out.print("Enter start of journey (1-10): ");
        int start = ab.nextInt();
        while (start < 1 || start > 10) {
            System.out.println("Invalid start point. Please enter a number between 1 and 10.");
            start = ab.nextInt();
        }

        System.out.print("Enter end of journey (1-10, not equal to start): ");
        int end = ab.nextInt();
        while (end < 1 || end > 10 || end == start) {
            System.out.println("Invalid end point. Must be different from start and between 1 and 10.");
            end = ab.nextInt();
        }
//adding a new journey on card
        Journey newJourney = new Journey();
        newJourney.setJourneyID(journeyId);
        newJourney.setTransportMode(mode);
        newJourney.setStartOfJourney(start);
        newJourney.setEndOfJourney(end);
newJourney.calculateDistance();
        if (card.getType() == 'C') {
            if (card.getJourney1() == null) {
                card.setJourney1(newJourney);
                System.out.println("Journey added successfully.");
                a+=1;
            } else {
                System.out.println("Maximum number of journeys reached for this card type.");
            }
        } else if (card.getType() == 'A') {
            if (card.getJourney1() == null) {
                card.setJourney1(newJourney);
                System.out.println("Journey added successfully.");
                b+=1;
            } else if (card.getJourney2() == null) {
                card.setJourney2(newJourney);
                System.out.println("Journey added successfully.");
                b+=1;
            } else {
                System.out.println("Maximum number of journeys reached for this card type.");
            }
        } else if (card.getType() == 'S') {
            if (card.getJourney1() == null) {
                card.setJourney1(newJourney);
                System.out.println("Journey added successfully.");
                c+=1;
            } else if (card.getJourney2() == null) {
                card.setJourney2(newJourney);
                System.out.println("Journey added successfully.");
                c+=1;
            } else if (card.getJourney3() == null) {
                card.setJourney3(newJourney);
                System.out.println("Journey added successfully.");
                c+=1;
            } else {
                System.out.println("Maximum number of journeys reached for this card type.");
            }
        }
    }
//deleting a smartcard using cardid
    //if cardid not found then returns a message no smartcard present
    private static void deleteSmartCard() {
        System.out.print("Enter the Smartcard ID to delete: ");
        int id = ab.nextInt();
        if (SCard1 != null && SCard1.getCardID() == id) {
            SCard1 = null;
            System.out.println("Smartcard deleted successfully.");
        } else if (SCard2 != null && SCard2.getCardID() == id) {
            SCard2 = null;
            System.out.println("Smartcard deleted successfully.");
        } else if (SCard3 != null && SCard3.getCardID() == id) {
            SCard3 = null;
            System.out.println("Smartcard deleted successfully.");
        } else {
            System.out.println("No smartcard found with ID: " + id);
        }
    }
//deleting a journey on card using journey id and card id
    private static void deleteJourney() {
        System.out.print("Enter Smartcard ID from which to delete the journey: ");
        int cId = ab.nextInt();
        SmartCard card = findCardById(cId);
        if (card == null) {
            System.out.println("No smartcard found with ID: " + cId);
            return;
        }

        System.out.print("Enter Journey ID to delete: ");
        int journeyId = ab.nextInt();
        if (card.getJourney1() != null && card.getJourney1().getJourneyID() == journeyId) {
            card.setJourney1(null);
            System.out.println("Journey deleted successfully.");
        } else if (card.getJourney2() != null && card.getJourney2().getJourneyID() == journeyId) {
            card.setJourney2(null);
            System.out.println("Journey deleted successfully.");
        } else if (card.getJourney3() != null && card.getJourney3().getJourneyID() == journeyId) {
            card.setJourney3(null);
            System.out.println("Journey deleted successfully.");
        } else {
            System.out.println("No journey found with ID: " + journeyId + " on this card.");
        }
    }
//view the list of all smartcards with their details and no of journey done on them
    private static void listSmartCards() {
        if (SCard1 == null && SCard2 == null && SCard3 == null) {
            System.out.println("No smartcards.");
        } else {
            if (SCard1 != null) {
                System.out.println("Smartcard ID: " + SCard1.getCardID() + ", Type: " + SCard1.getType() + ", no. of journey " + a);
            }
            if (SCard2 != null) {
                System.out.println("Smartcard ID: " + SCard2.getCardID() + ", Type: " + SCard2.getType() + ", no. of journey " + b);
            }
            if (SCard3 != null) {
                System.out.println("Smartcard ID: " + SCard3.getCardID() + ", Type: " + SCard3.getType() + ", no. of journey " + c);
            }
        }
    }
//view the list of journeys on smartcard using card and journey id
    private static void listJourneysOnCard() {
        System.out.print("Enter Smartcard ID to list journeys: ");
        int cId = ab.nextInt();
        SmartCard card = findCardById(cId);
        if (card == null) {
            System.out.println("No smartcard found with ID: " + cId);
        } else {
            System.out.println("Journeys on Smartcard " + cId + ":");
            if (card.getJourney1() != null) {
                printJourneyDetails(card.getJourney1());
            }
            if (card.getJourney2() != null) {
                printJourneyDetails(card.getJourney2());
            }
            if (card.getJourney3() != null) {
                printJourneyDetails(card.getJourney3());
            }
            if (card.getJourney1() == null && card.getJourney2() == null && card.getJourney3() == null) {
                System.out.println("No journeys.");
            }
        }
    }
//view journeys on the code by the mode of transportation used
    private static void listJourneysByTransportMode() {
        System.out.print("Enter here the transport mode to list the journeys (train/bus/tram): ");
        String mode = ab.next().toLowerCase();
        boolean found = false;
        System.out.println("here is all journeys by transport mode: " + mode);

        if (SCard1 != null) {
            found |= printJourneyByMode(SCard1, mode);
        }
        if (SCard2 != null) {
            found |= printJourneyByMode(SCard2, mode);
        }
        if (SCard3 != null) {
            found |= printJourneyByMode(SCard3, mode);
        }

        if (!found) {
            System.out.println("No journeys with that transport mode.");
        }
    }
//view charges or fare for the complete journeys done on a smartcard
    private static void summarizeCosts() {
        float totalCost = 0;
        System.out.println("Summary of fare for all journeys:");

        if (SCard1 != null) {
            totalCost += summarizeCostsForCard(SCard1);
        }
        if (SCard2 != null) {
            totalCost += summarizeCostsForCard(SCard2);
        }
        if (SCard3 != null) {
            totalCost += summarizeCostsForCard(SCard3);
        }

        System.out.println("Total cost for all journeys: $ " + totalCost);
    }

    private static boolean printJourneyByMode(SmartCard card, String mode) {
        boolean found = false;
        if (card.getJourney1() != null && card.getJourney1().getTransportMode().equalsIgnoreCase(mode)) {
            printJourneyDetails(card.getJourney1());
            found = true;
        }
        if (card.getJourney2() != null && card.getJourney2().getTransportMode().equalsIgnoreCase(mode)) {
            printJourneyDetails(card.getJourney2());
            found = true;
        }
        if (card.getJourney3() != null && card.getJourney3().getTransportMode().equalsIgnoreCase(mode)) {
            printJourneyDetails(card.getJourney3());
            found = true;
        }
        return found;
    }

    private static float summarizeCostsForCard(SmartCard card) {
        float total = 0;
        if (card.getJourney1() != null) {
            total += calculateFare(card.getJourney1(), card.getType());
        }
        if (card.getJourney2() != null) {
            total += calculateFare(card.getJourney2(), card.getType());
        }
        if (card.getJourney3() != null) {
            total += calculateFare(card.getJourney3(), card.getType());
        }
        return total;
    }

    private static float calculateFare(Journey journey, char cardType) {
        float baseFare = 1.5f;
        float costPerStation = 0;
        switch (cardType) {
            case 'C':
                costPerStation = 1.86f;
                break;
            case 'A':
                costPerStation = 2.24f;
                break;
            case 'S':
                costPerStation = 1.60f;
                break;
        }
        return baseFare + (costPerStation * journey.getDistanceOfJourney());
    }

    private static void printJourneyDetails(Journey journey) {
        System.out.println("Journey ID: "+ journey.getJourneyID()+" Transport Mode: "+journey.getTransportMode()+
                " Start: "+journey.getStartOfJourney()+" End: "+journey.getEndOfJourney()+" Distance: "+journey.getDistanceOfJourney());

    }


}
