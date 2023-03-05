package fileio;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadingThread extends Thread{
    private String fileName;
    private Object mutex;

    public ReadingThread(String fileName, Object mutex) {
        this.fileName = fileName;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (mutex) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }

                Thread.sleep(100L);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
