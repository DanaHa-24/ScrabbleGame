package Backend;


import java.util.Arrays;
import java.util.Objects;

public class Word {
    public Tile[] my_word;
    public int row;
    public int col;
    public boolean vertical;

    public Word(Tile[] my_word, int row, int col, boolean vertical) {
        this.my_word = my_word;
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }

    public Tile[] getMy_word() {
        return my_word;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isVertical() {
        return vertical;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
