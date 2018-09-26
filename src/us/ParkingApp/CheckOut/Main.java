package us.ParkingApp.CheckOut;
import us.ParkingApp.FileInput;
import us.ParkingApp.FileOutput;
import us.ParkingApp.Ticket;

import java.sql.Time;
import java.util.*;

/**
 * This is the main driving class for the check-out machine of the parking garage ATM
 * @author David Allen
 * @version 1.0
 */
public class Main {
    /**
     * This class drives the check-out machine for the parking garage.
     * It is responsible for taking the .CSV input and altering it for tickets which have been paid.
     * @param args this class accepts string arguments.
     */
    public static void main(String[] args) {

        //list necessary beginning objects and variables
        ArrayList<Ticket> list = new ArrayList<Ticket>();
        Scanner reader = new Scanner(System.in);
        String line;
        String words[];
        FileInput indata = new FileInput("tickets.csv");
        boolean done = false;
        int userinput;
        int ticketnum;
        boolean matched = false;
        int ticketindex = 0;
        int hours;

        //read data from file and create/store Ticket objects in an Arraylist
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

        //provide menu for checking out of the parking garage
        while (!done) {
            System.out.println("Best Value Parking Garage");
            System.out.println("=========================");
            System.out.println("1. Check/Out");
            System.out.println("2. Lost Ticket");
            System.out.println("3. Exit");
            System.out.println();
            System.out.println("==>_");

            userinput = reader.nextInt();

            //read action input to determine how to handle tickets
            if (userinput == 1){
                System.out.println("Please enter your ticket number: ");
                try{
                    ticketnum = reader.nextInt();
                } catch (Exception e){
                    ticketnum = 99999;
                }

                //match supplied ID with ID from list
                for (int x = 0; x < list.size(); x++){
                    if (ticketnum == list.get(x).getVehicleID()){
                        matched = true;
                        ticketindex = x;
                    }
                }
                //Determine payment if matched, redisplay screen if invalid number entered
                if (matched){
                    done = true;
                    list.get(ticketindex).setPaid(true);
                    Time checkouttime = Time.valueOf(((int)(Math.random() * 10) + 13)+":00:00");
                    hours = checkouttime.getHours() - list.get(ticketindex).getTimein().getHours();
                    if (hours<=3){
                        list.get(ticketindex).setAmountpaid(5);
                    } else if (hours<=4){
                        list.get(ticketindex).setAmountpaid(6);
                    } else if (hours<=5){
                        list.get(ticketindex).setAmountpaid(7);
                    } else if (hours<=6){
                        list.get(ticketindex).setAmountpaid(8);
                    } else if (hours<=7){
                        list.get(ticketindex).setAmountpaid(9);
                    } else if (hours<=8){
                        list.get(ticketindex).setAmountpaid(10);
                    } else if (hours<=9){
                        list.get(ticketindex).setAmountpaid(11);
                    } else if (hours<=10){
                        list.get(ticketindex).setAmountpaid(12);
                    } else if (hours<=11){
                        list.get(ticketindex).setAmountpaid(13);
                    } else if (hours<=12){
                        list.get(ticketindex).setAmountpaid(14);
                    } else {
                        list.get(ticketindex).setAmountpaid(15);
                    }
                    //provide receipt of ticket returned
                    System.out.println();
                    System.out.println("Best Value Parking Garage");
                    System.out.println("=========================");
                    System.out.println("Receipt for vehicle id " + ticketnum);
                    System.out.println();
                    System.out.println(hours + " hours parked " + list.get(ticketindex).getTimein().getHours()+"am - " + (checkouttime.getHours()-12) + "pm");
                    System.out.println("$" + list.get(ticketindex).getAmountpaid() + ".00");

                } else {
                    System.out.println("Invalid ticket number.");
                }


            } else if (userinput == 2){
                //lost ticket handler
                done = true;
                System.out.println("Please enter your ticket number: ");
                try{
                    ticketnum = reader.nextInt();
                } catch (Exception e){
                    ticketnum = 99999;
                }
                //Match supplied ID number with ID in ticket list
                for (int x = 0; x < list.size(); x++){
                    if (ticketnum == list.get(x).getVehicleID()){
                        matched = true;
                        ticketindex = x;
                    }
                }
                //Create receipt for a lost ticket
                if (matched){
                    list.get(ticketindex).setPaid(true);
                    list.get(ticketindex).setLostticket(true);
                    list.get(ticketindex).setAmountpaid(25);
                    System.out.println();
                    System.out.println("Best Value Parking Garage");
                    System.out.println("=========================");
                    System.out.println("Receipt for vehicle id " + ticketnum);
                    System.out.println();
                    System.out.println("Lost Ticket");
                    System.out.println("$25.00");

                } else {
                    System.out.println("Invalid ticket number.");
                }

            } else if (userinput == 3) {
                System.out.println("Please enter 1, 2, or 3.");
            }


        }
        //output updated information back to the file
        FileOutput outfile = new FileOutput("tickets.csv");
        for (int x = 0; x < list.size(); x++){
            String putvehicleid = Integer.toString(list.get(x).getVehicleID());
            String puttimein = list.get(x).getTimein().toString();
            String putpaid = Boolean.toString(list.get(x).isPaid());
            String putlostticket = Boolean.toString(list.get(x).isLostticket());
            String putamountpaid = Integer.toString(list.get(x).getAmountpaid());
            String inputline = putvehicleid+","+puttimein+","+putpaid+","+putlostticket+","+putamountpaid;
            outfile.fileWrite(inputline);
        }
        outfile.fileClose();

    }
}
