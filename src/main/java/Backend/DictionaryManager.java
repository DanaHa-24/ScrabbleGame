package Backend;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {
    public static Map<String, Dictionary> map;
    private static DictionaryManager dm = null;
    private String[] bookList;

    public DictionaryManager() {
        map = new HashMap<String, Dictionary>();
    }

    public int getSize() {
        return map.size();
    }

    public static DictionaryManager get() {
        if (dm == null)
            return new DictionaryManager();
        else
            return dm;
    }

    public static boolean query(String... bookList) {
        String key = bookList[bookList.length - 1];
        bookList = Arrays.copyOf(bookList, bookList.length - 1);
        boolean flag = false;
        if (map.containsKey(key))
            return true;
        for (String s: bookList) {
            map.put(s,new Dictionary(s));
            if(map.get(s).query(key))
                flag = true;
        }
        return flag;
}

    public static boolean challenge(String... bookList) {
        if(bookList.length < 1)
            return false;
        String key = bookList[bookList.length - 1];
        bookList = Arrays.copyOf(bookList, bookList.length - 1);
        boolean flag = false;
        for (String s: bookList) {
            map.put(s,new Dictionary(s));
            if(map.get(s).challenge(key))
                flag = true;
        }
        return flag;
    }
}
