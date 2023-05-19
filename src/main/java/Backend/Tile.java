package Backend;


import java.util.Objects;
import java.lang.Math;

public class Tile {
    final public char letter;
    final public int score;

    Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    public char getLetter() {
        return letter;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static class Bag {
        public static int[] letters_amount = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
        public static Tile[] letters_tiles = new Tile[26];
        final public int[] score_letters = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
        public static int current_letters = 98;
        private static Bag new_bag = null;

        private Bag(int[] letters_amount,int current_letters) {
            Bag.letters_amount = letters_amount;
            for(int i = 0; i < 26; i++){
                char letter = (char)(i + 'A');
                Bag.letters_tiles[i] = new Tile(letter,score_letters[i]);
            }
            Bag.letters_tiles = letters_tiles;
            Bag.current_letters = current_letters;
        }

        public static Bag getBag(){
            if (new_bag == null)
                new_bag = new Bag(letters_amount, current_letters);
            return new_bag;
        }

        public Tile getRand() {
            if (current_letters != 0){
                int rand = (int)(Math.random() * 25);
                while (letters_amount[rand] == 0)
                    rand = (int) (Math.random() * 25);
                letters_amount[rand]--;
                current_letters--;
                return letters_tiles[rand];
            }
            return null;
        }
        public Tile getTile(char l){
            int position = l - 'A';
            if(position >= 0 && position < 26){
                if(letters_amount[position] != 0) {
                    letters_amount[position]--;
                    current_letters--;
                    return letters_tiles[position];
                }
            }
            return null;
        }
        public void put(Tile t){
            if(current_letters < 98){
                int position = (int)(t.letter - 'A');
                letters_amount[position]++;
                current_letters++;
            }
        }

        public int size(){
            return current_letters;
        }

        public int[] getQuantities(){
            int [] c_letters_amount = new int[26];
            for(int i = 0; i < 26; i++)
                c_letters_amount[i] = letters_amount[i];
            return c_letters_amount;
        }
    }
}

