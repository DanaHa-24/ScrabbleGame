package Backend;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.BitSet;

public class BloomFilter {
    private int size;
    public ArrayList<String> algsList;
    public ArrayList<byte[]> arrBytes;
    public static ArrayList<MessageDigest> mds;
    private BitSet bitArray;
    public BloomFilter(int size, String...algs) {
        this.size = size;
        this.algsList = new ArrayList<>();
        this.bitArray = new BitSet(this.size);
        this.mds = new ArrayList<>();
        this.arrBytes = new ArrayList<>();

        for(String alg: algs)
        {
            algsList.add(alg);
            try{
                mds.add(MessageDigest.getInstance(alg));
            }
            catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void add(String word){
        int[] places = calculateIndex(word);

        this.bitArray.set(places[0]);
        this.bitArray.set(places[1]);
    }

    public boolean contains(String word){
        int[] places = calculateIndex(word);
        if (this.bitArray.get(places[0]) && this.bitArray.get(places[1]))
            return true;
        else return false;

    }

    public int[] calculateIndex(String word){
        int [] places = new int[algsList.size()];
        byte[] resultBytes;
        BigInteger number;
        for(int i = 0; i < algsList.size(); i++)
        {
            resultBytes = getBytes(word,algsList.get(i));
            number = new BigInteger(resultBytes);
            places[i] = Math.abs(number.intValue()) % this.size;
        }
        return places;
    }

    public static byte[] getBytes(String input, String alg) {
        byte[] resultBytes = new byte[0];
        BigInteger num;
        for(MessageDigest message : mds) {
            if (message.getAlgorithm().equals(alg)){
                resultBytes = message.digest(input.getBytes());
                num = new BigInteger((resultBytes));
            }
        }
        return resultBytes;
    }

    @Override
    public String toString(){
        String result = new String();
        for(int i = 0; i < this.bitArray.length(); i++)
        {
            if (this.bitArray.get(i))
                result+="1";
          else result+="0";
        }
        return result;
    }
}
