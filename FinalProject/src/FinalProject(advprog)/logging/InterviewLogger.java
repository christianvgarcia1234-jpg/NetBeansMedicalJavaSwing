package FinalProject.advprog.logging;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import FinalProject.advprog.model.Patient;

public class InterviewLogger implements Closeable {

    private BufferedWriter writer;

    public InterviewLogger(Patient patient) {
        String fileName = "interview_" + patient.getPatientID() + "_" + LocalDate.now() + ".txt";

        try {
            writer = Files.newBufferedWriter(
                    Paths.get(fileName),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

            writer.write("Patient ID: " + patient.getPatientID());
            writer.newLine();
            writer.write("Patient Name: " + patient.getFirstName() + " " + patient.getLastName());
            writer.newLine();
            writer.write("Interview Date: " + LocalDateTime.now());
            writer.newLine();
            writer.write("--------------------------------------------------");
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String question, boolean answer) {
        try {
            writer.write("Q: " + question);
            writer.newLine();
            writer.write("A: " + (answer ? "YES" : "NO"));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
