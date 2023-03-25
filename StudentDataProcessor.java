import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDataProcessor {

    public static void main(String[] args) throws IOException {

        // Read the CSV file
        CSVReader reader = new CSVReader(new FileReader("student_data.csv"));
        List<String[]> rows = reader.readAll();

        // Calculate the average of each student's marks
        List<String[]> updatedRows = new ArrayList<>();
        for (String[] row : rows) {
            String[] updatedRow = new String[row.length + 2];
            double totalMarks = 0;
            for (int i = 1; i < row.length; i++) {
                totalMarks += Double.parseDouble(row[i]);
                updatedRow[i - 1] = row[i];
            }
            double avgMarks = totalMarks / (row.length - 1);
            updatedRow[row.length - 1] = String.format("%.2f", avgMarks);
            updatedRow[row.length] = "95"; // Add new subject marks
            updatedRows.add(updatedRow);
        }

        // Write the updated rows back to the CSV file
        CSVWriter writer = new CSVWriter(new FileWriter("student_data.csv"));
        writer.writeAll(updatedRows);
        writer.close();

        System.out.println("Student data processed successfully.");
    }
}
