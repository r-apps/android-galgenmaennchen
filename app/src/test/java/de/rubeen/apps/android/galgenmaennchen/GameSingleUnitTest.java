package de.rubeen.apps.android.galgenmaennchen;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GameSingleUnitTest {
    GameSingle game;
    @Test
    public void gameTryTest() {
        game = new GameSingle();
        game.newRound("FA");
        assertThat(game.getActualWordToGuess().length(), is(game.getActualGuessed().length()));
        printAll();
        game.guess("F");
        printAll();
        assertTrue(game.getActualGuessed().contains("F"));
        game.guess("l");
        printAll();
        assertThat(game.getActualAvailableRetries(), is(IGame.getRetries() - 1));
        assertTrue(game.guess("A"));
        printAll();


        game.newRound("FI");
        assertFalse(game.guess("F"));
        assertTrue(game.guess("I"));
        printAll();

        game.newRound("YouDon'tKnowMe");
        game.guess("a");
        game.guess("b");
        game.guess("c");
        game.guess("a");
        game.guess("a");
        game.guess("a");
        game.guess("a");
        game.guess("a");
        game.guess("a");
        game.guess("a");
        assertTrue(game.guess("a"));
        printAll();

        game.newRound("B B");
        assertTrue(game.guess("B"));
        printAll();
//        GameSingle game = new GameSingle("guessThisWord");
//        assertThat(game.wordToGuess.length(), is(game.guessed.length()));
//        System.out.println(game.guessed);
//        System.out.println(game.wordToGuess);
//
//        assertTrue(game.newTry("s"));
//        System.out.println(game.guessed);
    }

    private void printAll() {
        System.out.println("Word to guess: " + game.getActualWordToGuess());
        System.out.println("Actual guessed: " + game.getActualGuessed());
        System.out.println("Available retries: " + game.getActualAvailableRetries());
        System.out.println("Guessed: " + game.counterGuessed);
        System.out.println("Failed: " + game.counterFailed);
    }
}