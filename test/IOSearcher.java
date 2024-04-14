package test;
import java.io.File;
import java.util.Scanner;

public class IOSearcher {

    static boolean stop;

    public IOSearcher() {
        stop = false;
    }

    public static boolean search(String word, String... fileNames) {
        boolean found = false;
        try {
            for (int i = 0; !stop && i < fileNames.length && !found; i++) {
                Scanner s = new Scanner(new File(fileNames[i]));
                while (s.hasNext() && !found && !stop)
                    if (s.next().equals(word))
                        found = true;
                s.close();
            }
        } catch (Exception e) {
        }

        return found;
    }

    public void stop() {
        stop = true;
    }
}
