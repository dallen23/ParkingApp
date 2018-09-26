package us.ParkingApp.CheckIn;
//import java.time.format.DateTimeFormatter;
import java.util.*;
import us.ParkingApp.FileInput;
import us.ParkingApp.FileOutput;
//import java.util.Random;
import java.sql.Time;
import us.ParkingApp.Ticket;

/**This class is the main driving class for the check-in machine of the garage parking ATM.
 * @author David Allen
 * @version 1.0
 */
public class Main {
    /**
     * This is the driver, responsible for accepting check-ins for new vehicles looking to park in the parking garage.
     * It takes input from a .CSV file for existing vehicles, creates new ticket orders, and places them in an ArrayList.
     * @param args This class accepts String arguments
     */
    public static void main(String[] args) {


        //List and instantiate beginning objects and variables
        ArrayList<Ticket> list = new ArrayList<Ticket>();
        Scanner reader = new Scanner(System.in);
        String line;
        String words[];
        FileInput indata = new FileInput("tickets.csv");
        boolean done = false;
        int userinput;

        //Read File and create Arraylist of Ticket objects
        while ((line = indata.fileReadLine()) != null){
            words = line.split(",");
            int vehicleid = Integer.parseInt(words[0]);
            Time timein = Time.valueOf(words[1]);
            boolean paid = Boolean.parseBoolean(words[2]);
            boolean lostticket = Boolean.parseBoolean(words[3]);
            int amountpaid = Integer.parseInt(words[4]);
            Ticket readticket = new Ticket(vehicleid,timein,paid,lostticket,amountpaid);
            list.add(readticket);
        }
        indata.fileClose();

        //provide check-in menu
        while (!done) {
            System.out.println("Best Value Parking Garage");
            System.out.println("=========================");
            System.out.println("1. Check/In");
            System.out.println("2. Close Garage");
            System.out.println();
            System.out.println("==>_");

            userinput = reader.nextInt();

            //Actions for checking-in (creating new ticket) and closing menu
            if (userinput == 1){


                Ticket newticket = new Ticket();
                list.add(newticket);
                System.out.println("Please pick up your ticket.");
                System.out.println("Your ticket number: " + newticket.getVehicleID());
                System.out.println();


            } else if (userinput == 2){
                done = true;
            } else {
                System.out.println("Please enter 1 or 2.");
            }

        }
        //re-write Tickets from Arraylist to the original .csv file for access from different modules
        FileOutput outfile = new FileOutput("tickets.csv");
        for (Ticket aList : list) {
            String putvehicleid = Integer.toString(aList.getVehicleID());
            String puttimein = aList.getTimein().toString();
            String putpaid = Boolean.toString(aList.isPaid());
            String putlostticket = Boolean.toString(aList.isLostticket());
            String putamountpaid = Integer.toString(aList.getAmountpaid());
            String inputline = putvehicleid + "," + puttimein + "," + putpaid + "," + putlostticket + "," + putamountpaid;
            outfile.fileWrite(inputline);
        }
        outfile.fileClose();

    }
}
