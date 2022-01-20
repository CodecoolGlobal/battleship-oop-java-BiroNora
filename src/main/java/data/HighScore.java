package main.java.data;

import main.java.utility.FileInOut;

public class HighScore {
    private final String pathName = "high score.txt";
    private final int scoreBoardRows = 25;
    private final int scoreBoardCols = 2;

    private String[][] scoreBoard;

    public String[][] getScoreBoard() {
        return scoreBoard;
    }

    public HighScore() {
        if (!readFromFile()) {
            generateScoreBoard();
            writeToFile();
        }
    }

    private void generateScoreBoard() {
        scoreBoard = new String[scoreBoardRows][scoreBoardCols];
        for (int row = 0; row < scoreBoard.length; row++) {
            scoreBoard[row][0] = "---";
            scoreBoard[row][1] = "---";
        }
    }

    public boolean readFromFile() {
        String[] lines = FileInOut.readLines(pathName);
        scoreBoard = new String[scoreBoardRows][scoreBoardCols];
        for (int i = 0; i < lines.length; i++) {
            if (i % 2 == 0) {
                scoreBoard[i / 2][0] = lines[i];
            } else {
                scoreBoard[i / 2][1] = lines[i];
            }
        }
        return 0 < lines.length;
    }

    public void writeToFile() {
        String[] lines = new String[scoreBoard.length * 2];
        for (int i = 0; i < lines.length; i++) {
            if (i % 2 == 0) {
                lines[i] = scoreBoard[i / 2][0];
            } else {
                lines[i] = scoreBoard[i / 2][1];
            }
        }
        FileInOut.writeLinesToFile(lines, pathName);
    }

    //add new entry to scoreboard
    public void newEntry(String name, int score) {
        for (int row = 0; row < scoreBoard.length; row++) {
            int currentRowScore;
            try {
                currentRowScore = Integer.parseInt(scoreBoard[row][1]);
            } catch (NumberFormatException e) {
                currentRowScore = 0;
            }
            if (currentRowScore < score) {
                for (int row2 = scoreBoard.length - 2; row <= row2; row2--) {
                    scoreBoard[row2 + 1][0] = scoreBoard[row2][0];
                    scoreBoard[row2 + 1][1] = scoreBoard[row2][1];
                }
                scoreBoard[row][0] = name;
                scoreBoard[row][1] = String.valueOf(score);
                break;
            }
        }
    }
}
