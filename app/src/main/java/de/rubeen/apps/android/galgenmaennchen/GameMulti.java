package de.rubeen.apps.android.galgenmaennchen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameMulti implements IGame {
    int counterGuessed = 0, counterFailed = 0;
    private List<Round> roundList = new LinkedList<>();
    private List<Player> playerList = new ArrayList<>(2);
    private int actualPlayer = 0;

    public GameMulti() {
    }

    public int getActualAvailableRetries() {
        return playerList.get(actualPlayer).getRound().availableRetries;
    }

    public String getActualGuessed() {
        return playerList.get(actualPlayer).getRound().guessed;
    }

    public String getActualWordToGuess() {
        return playerList.get(actualPlayer).getRound().wordToGuess;
    }

    public void newRound(String word) {
        Round round = new Round(word.toUpperCase());
        roundList.add(round);

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
