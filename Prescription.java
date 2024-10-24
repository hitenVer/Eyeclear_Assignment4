import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {

    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float cylinder;
    private float axis;
    private Date examinationDate;
    private String optometrist;
    private String[] remarkTypes = {"Client", "Optometrist"};
    private ArrayList<String> postRemarks = new ArrayList<>();

    //static final to make the file name constants
    private static final String PRESCRIPTION_FILE = "presc.txt";
    private static final String REMARK_FILE = "remark.txt";

    // add prescription to a TXT file
    public boolean addPrescription(String firstName, String lastName, String address,
                                   float sphere, float cylinder, float axis, Date examDate, String optometrist) {
        // Validate the prescription's information
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
            return false; // Invalid first or last name
        }
        if (address.length() < 20) {
            return false; // if invalid address returns false
        }
        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
            return false; // if invalid sphere, cylinder, or axis values returns fals
        }
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false; // for invalid optometrist name this will return false
        }

        // for writing the prescription details to the TXT file
        try (FileWriter writer = new FileWriter(PRESCRIPTION_FILE, true)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            writer.write("Prescription: " + firstName + " " + lastName + ", Address: " + address +
                    ", Sphere: " + sphere + ", Cylinder: " + cylinder + ", Axis: " + axis +
                    ", Exam Date: " + dateFormat.format(examDate) + ", Optometrist: " + optometrist + "\n");
            return true; // returns true means the data has been successfully added
        } catch (IOException e) {
            e.printStackTrace();
            return false; // failed to write to the file
        }
    }

    // adding a remark to the TXT file
    public boolean addRemark(String remark, String category) {
        // to check if remark category is valid and remark is within the required length
        String[] words = remark.split(" ");
        if (words.length < 6 || words.length > 20 || !Character.isUpperCase(words[0].charAt(0))) {
            return false; // if invalid remark text return false
        }
        if (!category.equals("Client") && !category.equals("Optometrist")) {
            return false; // if invalid category, it will return false
        }
        if (postRemarks.size() >= 2) {
            return false; // limit to two remarks
        }

        // adds remark to the list and write to the remark file
        postRemarks.add(remark);
        try (FileWriter writer = new FileWriter(REMARK_FILE, true)) {
            writer.write("Remark: " + remark + " (Category: " + category + ")\n");
            return true; // successfully added
        } catch (IOException e) { // implemented try-catch to handle file opening/closing and writing issues
            e.printStackTrace();
            return false; // failed to write to the file
        }
    }
}
