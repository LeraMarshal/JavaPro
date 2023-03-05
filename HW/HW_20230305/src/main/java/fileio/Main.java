package fileio;

public class Main {
   /*
    Один поток обращается к файлу и записывает в него данные,
    второй поток открывает файл и выводит его содержимое на консоль.
    Реализовать потокобезопасным образом.
    */
   public static void main(String[] args) throws Exception {
       String fileName = "HW/HW_20230305/file";
       Object mutex = new Object();

       WritingThread writingThread = new WritingThread(fileName, mutex);
       writingThread.start();

       Thread.sleep(1000L);

       ReadingThread readingThread = new ReadingThread(fileName, mutex);
       readingThread.start();

       Thread.sleep(5000L);

       writingThread.interrupt();
       writingThread.join();

       readingThread.interrupt();
       readingThread.join();
   }
}
