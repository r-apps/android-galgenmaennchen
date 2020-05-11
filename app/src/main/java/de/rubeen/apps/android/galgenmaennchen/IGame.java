package de.rubeen.apps.android.galgenmaennchen;

public interface IGame {
    static int getRetries() {
        return Constants.VALUE_MAX_RETRIES;
    }

    int getActualAvailableRetries();

    String getActualGuessed();

    String getActualWordToGuess();

    void newRound(String word);

    boolean guess(String ch);

    class Round {
        final String wordToGuess;
        int availableRetries = getRetries();
        String guessed;

        public Round(String wordToGuess) {
            this.wordToGuess = wordToGuess;
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < wordToGuess.length(); i++)
                if (wordToGuess.charAt(i) != ' ')
                    builder.append("_");
                else
                    builder.append(" ");
            guessed = builder.toString();
        }

        public GameSingle.GameStatus getGameStatus() {
            if (availableRetries == 0)
                return GameSingle.GameStatus.wordNotGuessed;
            if (!guessed.contains("_"))
                return GameSingle.GameStatus.wordGuessed;

            return GameSingle.GameStatus.inProgress;
        }

        public boolean newTry(String c) {
            boolean value = false;
            if (wordToGuess.contains(c)) {
                for (int i = 0; i < wordToGuess.length(); i++) {
                    if (wordToGuess.charAt(i) == c.charAt(0)) {
                        guessed = new StringBuilder(guessed).replace(i, i + 1, c).toString();
                        value = true;
                    }
                }
            } else
                availableRetries--;
            return value;
        }
    }

    public enum GameStatus {
        inProgress,
        newGame,
        wordGuessed,
        wordNotGuessed
    }
}
