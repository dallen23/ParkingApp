package us.ParkingApp.CloseGarage;
import us.ParkingApp.FileInput;
import us.ParkingApp.Ticket;
import java.sql.Time;
import java.util.*;

/**
 * This is the main driving class for the report machine of the parking garage ATM.
 * @author David Allen
 * @version 1.0
 */
public class Main {
    /**
     * This class takes ticket information from the .CSV file, and provides a report of activity to date.
     * @param args This class accepts String arguments.
     */
    public static void main(String[] args) {

        //create necessary variables and objects
        ArrayList<Ticket> list = new ArrayList<Ticket>();
        Scanner reader = new Scanner(System.in);
        String line;
        String words[];
        FileInput indata = new FileInput("tickets.csv");
        int lostnum = 0;
        int losttotal = 0;
        int checkinnum = 0;
        int checkintotal = 0;

        //read data from file, create/store Ticket objects in Arraylist
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

        //Compute payments to date
        for (int x = 0; x < list.size(); x++){
            if (list.get(x).isPaid() == true){
                if (list.get(x).isLostticket() == true){
                    lostnum++;
                    losttotal += list.get(x).getAmountpaid();
                } else {
                    checkinnum++;
                    checkintotal += list.get(x).getAmountpaid();
                }
            }
        }

        //Display payments to date in designated format
        System.out.println("Best Value Parking Garage");
        System.out.println("=========================");
        System.out.println("Activity to Date");
        System.out.println();
        System.out.println("$" + checkintotal + ".00 was collected from " + checkinnum + " check-ins.");
        System.out.println("$" + losttotal + ".00 was collected from " + lostnum + " lost tickets.");
        System.out.println();
        System.out.println("$" + (losttotal + checkintotal) + ".00 was collected overall");
    }
}
