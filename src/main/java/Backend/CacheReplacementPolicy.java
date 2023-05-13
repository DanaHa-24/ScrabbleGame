package Backend;

public interface CacheReplacementPolicy{
    void add(String word);
    String remove();
}
