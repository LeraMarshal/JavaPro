package fileio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WritingThread extends Thread {
    private String fileName;
    private Object mutex;

    public WritingThread(String fileName, Object mutex) {
        this.fileName = fileName;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        long count = 0;

        try (OutputStream outputStream = Files.newOutputStream(Paths.get(fileName))) {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (mutex) {
                    outputStream.write(String.format("Hello World %d\n", ++count).getBytes());
                    outputStream.flush();
                }

                Thread.sleep(100L);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
            Thread.currentThread().interrupt();
        }

        System.out.println("Count is " + count);
    }
}
