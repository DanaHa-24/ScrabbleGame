package Backend;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class LRU implements CacheReplacementPolicy {
    public LinkedHashSet<String> set;
    public LRU() {
        this.set = new LinkedHashSet<String>();
    }
    public void add(String word) {
        if(set.contains(word)) {
            set.remove(word);
        }
        set.add(word);
    }
    public String remove() {
        String tmp = null;
        if(!set.isEmpty()) {
            Iterator<String> iter = set.iterator();
            tmp =  iter.next();
            set.remove(iter);
        }
        return tmp;
    }
}
