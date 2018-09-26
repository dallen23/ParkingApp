package us.ParkingApp;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is responsible for accepting all input from the .CSV files for all three driver classes for each machine.
 * @author David Allen
 * @version 1.0
 */
public class FileInput {

        private BufferedReader in = null;
        private String fileName;

    /**
     * This constructor provides the ability to create an object to read from
     * @param fileName The name of the file to be read
     */
    public FileInput(String fileName) {
            this.fileName = fileName;
            try {
                in = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                System.out.println("File Open Error: " + fileName + " " + e);
            }
        }

    /**
     * This method does not return a String read from the file, but prints it to the console.
     * This method is not used in this program, but was kept for testing purposes.
     */
    public void fileRead() {
            String line;
            try {
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                System.out.println("File Write Error: " + fileName + " " + e);
            }
        }

    /**
     * This method directs the FileInput object to read a line from the file, create a string, and return said string.
     * @return This method returns a String
     */
        public String fileReadLine() {
            try {
                String line = in.readLine();
                return line;
            } catch (Exception e) {
                System.out.println("File Write Error: " + fileName + " " + e);
                return null;
            }
        }

    /**
     * This method securely closes the file opened by the FileInput object.
     */
    public void fileClose() {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
