package us.ParkingApp;
import java.io.*;

/**
 * This class is the basis for allowing file Output for the CheckIn and CheckOut Packages and their driver classes.
 * @author David Allen
 * @version 1.0
 */
public class FileOutput  {
    Writer out = null;
    private String fileName;

    /**
     * This constructor creates an object to allow for file output to a designated file.
     * @param fileName The file name to have data output to.
     */
    public FileOutput(String fileName) {
        this.fileName = fileName;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
        }
        catch(FileNotFoundException e) {
            System.out.println("File Open Error: " + fileName + " "  + e);
        }
    }

    /**
     * This method is used to write a line to the file decided by the FileOutput constructor
     * @param line The line to be output to the file
     */
    public void fileWrite(String line) {
        try {
            out.write(line+"\n");
        }
        catch(Exception e) {
            System.out.println("File Write Error: " + fileName + " "  + e);
        }
    }

    /**
     * This method securely closes the file being written to.
     */
    public void fileClose() {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
