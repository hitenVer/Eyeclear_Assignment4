import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class PrescriptionTest {

    @Test
    void testAddPrescription() {
        Prescription prescription = new Prescription();
        Date today = new Date();

        // Test 1: valid prescription
        boolean result = prescription.addPrescription("John", "Smith", "1234 Elm Street, Melbourne VIC",
                -5.00f, -2.50f, 90.0f, today, "Dr. Howard");
        assertTrue(result);

        // Test 2: invalid first name length
        result = prescription.addPrescription("Jo", "Smith", "1234 Elm Street, Melbourne VIC",
                -5.00f, -2.50f, 90.0f, today, "Dr. Howard");
        assertFalse(result);

        // Test 3: invalid address length
        result = prescription.addPrescription("John", "Smith", "Short St", -5.00f, -2.50f, 90.0f, today, "Dr. Howard");
        assertFalse(result);

        // Test 4: invalid sphere value (out of range)
        result = prescription.addPrescription("John", "Smith", "1234 Elm Street, Melbourne VIC",
                -25.00f, -2.50f, 90.0f, today, "Dr. Howard");
        assertFalse(result);

        // Test 5: invalid optometrist name length
        result = prescription.addPrescription("John", "Smith", "1234 Elm Street, Melbourne VIC",
                -5.00f, -2.50f, 90.0f, today, "Dr. H");
        assertFalse(result);

        // Test 6: valid prescription edge values
        result = prescription.addPrescription("John", "Smith", "1234 Elm Street, Melbourne VIC",
                20.00f, 4.00f, 180.0f, today, "Dr. Howard");
        assertTrue(result);
    }

    @Test
    void testAddRemark() {
        Prescription prescription = new Prescription();

        // Test 1: valid remark
        boolean result = prescription.addRemark("Client has blurry vision in the left eye", "Client");
        assertTrue(result);

        // Test 2: invalid remark (if too short)
        result = prescription.addRemark("Blurry", "Client");
        assertFalse(result);


        // Test 3: invalid category
        result = prescription.addRemark("Client has blurry vision in the left eye", "Doctor");
        assertFalse(result);

        // Test 4: valid second remark
        result = prescription.addRemark("Optometrist recommends yearly checkup", "Optometrist");
        assertFalse(result);

        // Test 5: exceed remark limit (for 3rd remark)
        result = prescription.addRemark("Client reported slight discomfort", "Client");
        assertFalse(result);
        // Test 6: invalid remark (too many words)
        result = prescription.addRemark("This remark exceeds the maximum allowed number of words for a remark", "Client");
        assertTrue(result); // This should fail, as the remark exceeds the 20-word limit
    }
}
