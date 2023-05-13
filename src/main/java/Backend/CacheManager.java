package Backend;

import java.util.HashSet;

public class CacheManager{
    public int size;
    public CacheReplacementPolicy crp;
    public static HashSet<String> wordsInCache = new HashSet<>();
    public CacheManager(int size, CacheReplacementPolicy crp) {
        this.size = size;
        this.crp = crp;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public CacheReplacementPolicy getCrp() {
        return crp;
    }

    public void setCrp(CacheReplacementPolicy crp) {
        this.crp = crp;
    }

    public boolean query(String word){
        if(wordsInCache.contains(word))
            return true;
        else return false;
    }

    public void add (String word){
        String tmp;
        crp.add(word);
        if (this.size <= wordsInCache.size()){
            tmp = crp.remove();
            wordsInCache.remove(tmp);
        }
        wordsInCache.add(word);
    }
}
