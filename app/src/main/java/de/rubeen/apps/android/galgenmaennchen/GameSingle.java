package de.rubeen.apps.android.galgenmaennchen;

import java.util.LinkedList;
import java.util.List;

public class GameSingle implements IGame {
    int counterGuessed = 0, counterFailed = 0;
    private List<Round> roundList = new LinkedList<>();
    private Round actualRound;

    public GameSingle() {
    }

    public int getActualAvailableRetries() {
        return actualRound.availableRetries;
    }

    public String getActualGuessed() {
        return actualRound.guessed;
    }

    public String getActualWordToGuess() {
        return actualRound.wordToGuess;
    }

    public void newRound(String word) {
        Round round = new Round(word.toUpperCase());
        roundList.add(round);
        actualRound = round;
    }

    public boolean guess(String ch) {
        if (actualRound.getGameStatus() == GameStatus.inProgress && ch.length() == 1)
            actualRound.newTry(ch.toUpperCase());
        if (actualRound.getGameStatus() == GameStatus.wordGuessed) {
            counterGuessed++;
        }
        if (actualRound.getGameStatus() == GameStatus.wordNotGuessed) {
            counterFailed++;
        }
        return actualRound.getGameStatus() != GameStatus.inProgress;
    }
}
