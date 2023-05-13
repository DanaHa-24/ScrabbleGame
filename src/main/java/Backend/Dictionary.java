package Backend;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
    public CacheManager lruCache;
    public CacheManager lfuCache;
    public BloomFilter blf;
    public ArrayList<String> files;

    public Dictionary(String... fileNames) {

        this.lruCache = new CacheManager(400, new LRU());
        this.lfuCache = new CacheManager(100, new LFU());
        this.blf = new BloomFilter(256, "MD5", "SHA1");
        this.files = new ArrayList<>();

        for (String file : fileNames) {
            files.add(file);
            addWords(file);
        }
    }

    public void addWords(String fileName) {
        try {
            File file = new File(fileName);
            Scanner line = new Scanner(file);
            while (line.hasNextLine()) {
                Scanner word = new Scanner(line.nextLine());
                while (word.hasNext()) {
                    blf.add(word.next());
                }
                word.close();
            }
            line.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }

    public boolean query(String word) {
        if (lruCache.query(word)) {
            return true;
        } else if (lfuCache.query(word)) {
            return false;
        } else if (blf.contains(word)) {
            lruCache.add(word);
            return true;
        }
        return false;
    }

    public boolean challenge(String word)  {
        try{
            for(String file: files){
                boolean res = IOSearcher.search(word, file);
                if(res){
                    lruCache.add(word);
                    return true;
                }
                else{
                    lfuCache.add(word);
                    return false;
                }
            }
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
