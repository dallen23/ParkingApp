package us.ParkingApp;

import org.junit.*;

import static org.junit.Assert.*;


public class FileInputTest {



    String line;




    @org.junit.Test
    public void fileReadLine() {
        FileInput indata = new FileInput("test.csv");
        line = indata.fileReadLine();
        indata.fileClose();
        System.out.println(line);
        assertEquals("110,11:00:00,false,false,0",line);
    }


}