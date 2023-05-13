package Backend;


import java.util.ArrayList;

public class Board {
    public static Tile[][] game_board = new Tile[15][15];
    final public String[][] bonus_board = {{"TW", "1", "1", "DL", "1", "1", "1", "TW", "1", "1", "1", "DL", "1", "1", "TW"},
            {"1", "DW", "1", "1", "1", "TL", "1", "1", "1", "TL", "1", "1", "1", "DW", "1"},
            {"1", "1", "DW", "1", "1", "1", "DL", "1", "DL", "1", "DL", "1", "1", "1", "DW", "1", "1"},
            {"DL", "1", "1", "DW", "1", "1", "1", "DL", "1", "1", "1", "DW", "1", "1", "DL"},
            {"1", "1", "1", "1", "DW", "1", "1", "1", "1", "1", "DW", "1", "1", "1", "1"},
            {"1", "TL", "1", "1", "1", "TL", "1", "1", "1", "TL", "1", "1", "1", "TL", "1"},
            {"1", "1", "DL", "1", "1", "1", "DL", "1", "DL", "1", "1", "1", "DL", "1", "1"},
            {"TW", "1", "1", "DL", "1", "1", "1", "STAR", "1", "1", "1", "DL", "1", "1", "TW"},
            {"1", "1", "DL", "1", "1", "1", "DL", "1", "DL", "1", "1", "1", "DL", "1", "1"},
            {"1", "TL", "1", "1", "1", "TL", "1", "1", "1", "TL", "1", "1", "1", "TL", "1"},
            {"1", "1", "1", "1", "DW", "1", "1", "1", "1", "1", "DW", "1", "1", "1", "1"},
            {"DL", "1", "1", "DW", "1", "1", "1", "DL", "1", "1", "1", "DW", "1", "1", "DL"},
            {"1", "1", "DW", "1", "1", "1", "DL", "1", "DL", "1", "DL", "1", "1", "1", "DW", "1", "1"},
            {"1", "DW", "1", "1", "1", "TL", "1", "1", "1", "TL", "1", "1", "1", "DW", "1"},
            {"TW", "1", "1", "DL", "1", "1", "1", "TW", "1", "1", "1", "DL", "1", "1", "TW"}};
    public static boolean is_started = false;
    private static Board new_board = null;
    public static int word_amount = 0;
    public static ArrayList<Word> words_in_board = new ArrayList<Word>();
    public Board(Tile[][] game_board, boolean is_started, int word_amount,ArrayList<Word> words_in_board) {
        Board.game_board = game_board;
        Board.is_started = is_started;
        Board.word_amount = word_amount;
        Board.words_in_board = words_in_board;
    }

    public static Board getBoard() {
        if (new_board == null)
            new_board = new Board(game_board, is_started, word_amount,words_in_board);
        return new_board;
    }

    public Tile[][] getTiles() {
        return game_board;
    }

    public boolean boardLegal(Word toCheck) {
        if (!wordInsideBoard(toCheck))
            return false;
        if (word_amount == 0) {
            if (!starInFirstTurn(toCheck))
                return false;
        }
        else{
            if(!isLeanOn(toCheck))
                return false;
        }
        if(!withoutReplace(toCheck))
            return false;
        return true;
    }
    public boolean wordInsideBoard(Word toCheck) {
        int row = toCheck.row;
        int col = toCheck.col;
        for (int i = 0; i < toCheck.my_word.length; i++) {
            if (row >= 15 || col >= 15 || row < 0 || col < 0)
                return false;
            if(toCheck.vertical)
                row++;
            else col++;
        }
        return true;
    }
    public boolean starInFirstTurn(Word toCheck) {
        if(word_amount == 0){
            int row = toCheck.row;
            int col = toCheck.col;
            for(int i = 0; i < toCheck.my_word.length; i++) {
                if (row == 7 && col == 7)
                    return true;
                if (toCheck.vertical)
                    row++;
                else col++;
            }
        }
        return false;
    }
    public boolean isLeanOn(Word toCheck) {
        int row = toCheck.row;
        int col = toCheck.col;
        for(int i = 0; i < toCheck.my_word.length; i++) {
            if(game_board[row][col] != null){
                if(toCheck.my_word[i] == null)
                    return true;
            }
            if(toCheck.vertical){
                row++;
                if(game_board[row][col-1] != null || game_board[row][col+1] != null)
                    return true;
            }
            else {
                col++;
                if(game_board[row-1][col] != null || game_board[row+1][col] != null)
                    return true;
            }
        }
        return false;
    }

    public boolean withoutReplace(Word toCheck){
        int row = toCheck.row;
        int col = toCheck.col;
        for(int i = 0; i < toCheck.my_word.length; i++) {
            if(game_board[row][col] != null && toCheck.my_word[i] != null)
                return false;
            if(toCheck.vertical)
                row++;
            else col++;
        }
        return true;
    }

    public boolean dictionaryLegal(Word toCheck){
        return true;
    }

    public int getScore(Word toCheck){
        int row = toCheck.row;
        int col = toCheck.col;
        int score = 0;
        String option;
        int triple_word = 1;
        int double_word = 1;
        int star = 1;
        for (int i = 0; i < toCheck.my_word.length; i++) {
            option = bonus_board[row][col];
            if (option.equals("DW"))
                double_word = 2;
            if (option.equals("TW"))
                triple_word = 3;
            if(toCheck.my_word[i] != null)
                score += (bonus(option) * toCheck.my_word[i].score);
            if(toCheck.my_word[i] == null && toCheck.vertical)
                score += (bonus(option) * game_board[row][col].score);
            if(toCheck.my_word[i] == null && !toCheck.vertical)
                score += (bonus(option) * game_board[row][col].score);
            if(toCheck.vertical)
                row++;
            else col++;
        }
        if(starInFirstTurn(toCheck))
            star = 2;
        score = score * double_word * triple_word * star;
        return score;
    }
    public int bonus(String option){
        if(option.equals("TL"))
            return 3;
        if(option.equals("DL"))
            return 2;
        return 1;
    }

    public ArrayList<Word> getWords(Word toCheck){
        int row = toCheck.row;
        int col = toCheck.col;
        ArrayList<Word> possible_words = new ArrayList<Word>();
        possible_words.add(toCheck);
        int start, end;
        if(toCheck.vertical) {
            for(int i = 0; i < toCheck.my_word.length; i++) {
                start = col;
                end = col;
                if(toCheck.my_word[i] != null){
                    while(game_board[row][start-1] != null && start > 0)
                        start--;
                    while(game_board[row][end+1] != null && end < 15)
                        end++;
                    if(end-start > 0) {
                        Tile[] new_word = new Tile[end - start + 1];
                        Word w1 = new Word(new_word, row, start, false);
                        for (int j = 0; end >= start; j++) {
                            if (start == toCheck.col)
                                w1.my_word[j] = toCheck.my_word[i];
                            else
                                w1.my_word[j] = game_board[start][col];
                            start++;
                        }
                        possible_words.add(w1);
                    }
                }
                row++;
            }
        }
        else{
            for(int i = 0; i < toCheck.my_word.length; i++) {
                start = row;
                end = row;
                if(toCheck.my_word[i] != null) {
                    while (game_board[start - 1][col] != null && start > 0)
                        start--;
                    while (game_board[end + 1][col] != null && end < 15)
                        end++;
                    if (end - start > 0) {
                        Tile[] new_word = new Tile[end - start + 1];
                        Word w1 = new Word(new_word, start, col, true);
                        for (int j = 0; end >= start; j++) {
                            if (start == toCheck.row)
                                w1.my_word[j] = toCheck.my_word[i];
                            else
                                w1.my_word[j] = game_board[start][col];
                            start++;
                        }
                        possible_words.add(w1);
                    }
                }
                col++;
            }
        }
        return possible_words;
    }

    public int tryPlaceWord(Word toCheck){
        ArrayList<Word> possible_words = new ArrayList<Word>();
        int score = 0;
        int row = toCheck.row;
        int col = toCheck.col;
        if(boardLegal(toCheck)){
            possible_words = getWords(toCheck);
            for(int i = 0; i < possible_words.size(); i++){
                if(dictionaryLegal(possible_words.get(i))){
                    words_in_board.add(possible_words.get(i));
                    score += getScore(possible_words.get(i));
                    word_amount++;
                }
            }
            for(int i = 0; i < toCheck.my_word.length; i++){
                if(game_board[row][col] == null)
                    game_board[row][col] = toCheck.my_word[i];
                // new Tile(toCheck.my_word[i].letter,toCheck.my_word[i].score);
                if(toCheck.vertical)
                    row++;
                else col++;
            }
        }
        return score;
    }
}
