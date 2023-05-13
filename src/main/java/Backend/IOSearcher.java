package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IOSearcher {
    public static boolean search(String toFind, String...fileNames) {
        for(String file: fileNames)
        {
            try {
                File file1 = new File(file);
                Scanner scan1 = new Scanner(file1);
                String tmp;

                while (scan1.hasNext()) {
                    tmp = scan1.next();
                    if (tmp.equals(toFind)) {
                        return true;
                    }
                }
                scan1.close();
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found.");
            }
        }
        return false;
    }
}
