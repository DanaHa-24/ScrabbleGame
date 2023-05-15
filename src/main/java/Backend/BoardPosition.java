package Backend;

public class BoardPosition {
    private int row;
    private int col;

    public BoardPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof BoardPosition)) {
            return false;
        }

        BoardPosition other = (BoardPosition) obj;

        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.row;
        hash = 31 * hash + this.col;
        return hash;
    }
}
