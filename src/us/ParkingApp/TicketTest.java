package us.ParkingApp;

import org.junit.Before;
import org.junit.Test;

import java.sql.Time;

import static org.junit.Assert.*;

public class TicketTest {

    Ticket testticket;
    Ticket testtickettwo;


    @Before
    public void setUp() throws Exception {
        Time testtime;
        testtime = Time.valueOf("10:00:00");
        testticket = new Ticket(105,testtime,false,false,0);
        testtickettwo = new Ticket(106,testtime,true,true,25);
    }


    @Test
    public void getTimein() {
        assertEquals("10:00:00",testticket.getTimein().toString());
    }

    @Test
    public void getVehicleID() {
        assertEquals(105,testticket.getVehicleID());
    }

    @Test
    public void isPaid() {
        assertFalse(testticket.isPaid());
        assertTrue(testtickettwo.isPaid());


    }

    @Test
    public void isLostticket() {
        assertFalse(testticket.isLostticket());
        assertTrue(testtickettwo.isLostticket());
    }

    @Test
    public void getAmountpaid() {
        assertEquals(0,testticket.getAmountpaid());
        assertEquals(25,testtickettwo.getAmountpaid());
        assertNotEquals(25,testticket.getAmountpaid());
        assertNotEquals(0,testtickettwo.getAmountpaid());
    }
}