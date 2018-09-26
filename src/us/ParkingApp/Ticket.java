package us.ParkingApp;
import java.util.Random;
import java.sql.Time;

/**
 * This class provides the ability to create and modify Ticket Objects
 * @author David Allen
 * @version 1.0
 */
public class Ticket {
    public static int vehicleidnumber = 100;
    private int vehicleid;
    private Time timein;
    private boolean paid;
    private boolean lostticket;
    private int amountpaid;

    /**
     * This is the Constructor of Ticket objects when read from a file.
     * @param vehicleid The integer Id number of the ticket to differentiate between tickets
     * @param timein The time which the car arrives and starts the timer, stored in Time format
     * @param paid The boolean of whether or not this ticket has been paid
     * @param lostticket The boolean of whether or not the ticket has been lost when paid
     * @param amountpaid The integer of the amount paid on the ticket.
     */
    public Ticket(int vehicleid,Time timein,boolean paid,boolean lostticket,int amountpaid){
        this.vehicleid = vehicleid;
        this.timein = timein;
        this.paid = paid;
        this.lostticket = lostticket;
        this.amountpaid = amountpaid;
        vehicleidnumber++;
    }

    /**
     * This is the Constructor of Ticket objects when creating a new Ticket with no previous information
     */
    public Ticket (){

        vehicleid = vehicleidnumber;
        paid = false;
        lostticket = false;
        amountpaid = 0;
        timein = Time.valueOf(((int)(Math.random() * 4) + 7)+":00:00");
        vehicleidnumber++;
    }

    /**
     * This is the "getter" for the Time-In property of a Ticket object.
     * @return returns Time format object of when the vehicle was clocked-in.
     */
    public Time getTimein() {
        return timein;
    }

    /**
     * This is the "getter" for the Vehicle ID property of a Ticket object.
     * @return returns the integer of the vehicle id associated with the Ticket object.
     */
    public int getVehicleID() {
        return vehicleid;
    }

    /**
     * This is the "getter" for the paid property of a Ticket object.
     * @return returns boolean of whether or not the ticket has been paid.
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * This is the "getter" for the lost ticket property of a Ticket object, whether or not the ticket has been lost.
     * @return returns a boolean of whether or not the Ticket was lost upon payment.
     */
    public boolean isLostticket() {
        return lostticket;
    }

    /**
     * This is the "getter" for the amount paid property of a Ticket object.
     * @return returns an integer of the amount paid on the Ticket.
     */
    public int getAmountpaid() {
        return amountpaid;
    }

    /**
     * This is the "setter" for the paid property of a Ticket object for when a customer checks out.
     * @param paid takes a boolean of whether or not the ticket has been paid.
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     * This is the "setter" for the lost ticket property of a Ticket object for when a customer checks out.
     * @param lostticket takes a boolean of whether or not the ticket was lost when checking out.
     */
    public void setLostticket(boolean lostticket) {
        this.lostticket = lostticket;
    }

    /**
     * This is the "setter" for the amount paid property of a Ticket object for when a customer checks out.
     * @param amountpaid takes an integer for how much was paid on this ticket.
     */
    public void setAmountpaid(int amountpaid) {
        this.amountpaid = amountpaid;
    }
}
