package de.rubeen.apps.android.galgenmaennchen;

import de.rubeen.apps.android.galgenmaennchen.IGame.Round;

import java.util.LinkedList;
import java.util.List;

public class Player {
    private String name;
    private Round round;
    private List<Round> roundList = new LinkedList<>();

    public String getName() {
        return name;
    }

    public List<String> getGuessedWords() {
        List<String> result = new LinkedList<>();
        for (Round r : roundList)
            if (r.wordToGuess == r.guessed)
                result.add(r.guessed);
        return result;
    }

    public List<String> getFailedWords() {
        List<String> result = new LinkedList<>();
        for (Round r : roundList)
            if (r.availableRetries < 1 && r.wordToGuess != r.guessed)
                result.add(r.wordToGuess);
        return result;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        roundList.add(round);
        this.round = round;
    }
}
