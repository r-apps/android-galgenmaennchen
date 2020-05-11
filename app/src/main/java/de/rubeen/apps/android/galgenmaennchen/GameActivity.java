package de.rubeen.apps.android.galgenmaennchen;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GAME-ACTIVITY";
    final Random random = new Random();
    final List<LinearLayout> layouts = new LinkedList<>();
    GameSingle game;
    List<Integer> imageResources = new ArrayList<>(12);
    List<String> words = new LinkedList<>();
    String gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        fillImageResourceList();
        fillLayoutsList();
        readWords();
        game = new GameSingle();

        gameMode = getIntent().getStringExtra(Constants.MODE_KEY);

        newRound();
        ((TextView) findViewById(R.id.textViewGuessed)).setText(game.getActualGuessed());
    }

    private void newRound() {
        game.newRound(getRandomWord());
        setImage(R.mipmap.ic_galgen0);
    }

    private void fillLayoutsList() {
        layouts.add(findViewById(R.id.spellerFirst));
        layouts.add(findViewById(R.id.spellerSecond));
        layouts.add(findViewById(R.id.spellerThird));
    }

    private void fillImageResourceList() {
        imageResources.add(R.mipmap.ic_galgen12);
        imageResources.add(R.mipmap.ic_galgen11);
        imageResources.add(R.mipmap.ic_galgen10);
        imageResources.add(R.mipmap.ic_galgen9);
        imageResources.add(R.mipmap.ic_galgen8);
        imageResources.add(R.mipmap.ic_galgen7);
        imageResources.add(R.mipmap.ic_galgen6);
        imageResources.add(R.mipmap.ic_galgen5);
        imageResources.add(R.mipmap.ic_galgen4);
        imageResources.add(R.mipmap.ic_galgen3);
        imageResources.add(R.mipmap.ic_galgen2);
        imageResources.add(R.mipmap.ic_galgen1);
        imageResources.add(R.mipmap.ic_galgen0);
    }

    public void buttonClick(View view) {
        if (game.guess(((Button) view).getText().toString())) {
            if (game.getActualGuessed().equals(game.getActualWordToGuess())) {
                ((TextView) findViewById(R.id.textViewGuessed)).setText(game.getActualGuessed());
                makeWin();
            } else {
                ((TextView) findViewById(R.id.textViewGuessed)).setText(game.getActualGuessed());
                makeLose();
            }
        } else {
            ((TextView) findViewById(R.id.textViewGuessed)).setText(game.getActualGuessed());
            Log.i(TAG, "buttonClick: Guessed:\t" + game.getActualGuessed());
            Log.i(TAG, "buttonClick: Retries:\t" + game.getActualAvailableRetries());
        }
        //view.setBackgroundColor(R.color.colorPrimaryDark);
        view.setEnabled(false);
        //findViewById(R.id.galgenImageView).setForeground(getDrawable(imageResources.get(0)));
        setImage(imageResources.get(game.getActualAvailableRetries()));
    }

    private void makeLose() {
        Toast.makeText(this, "Spiel verloren.", Toast.LENGTH_SHORT).show();
        makeEndGame();
    }

    private void makeWin() {
        Toast.makeText(this, "Spiel gewonnen!", Toast.LENGTH_SHORT).show();
        makeEndGame();
    }

    private void makeEndGame() {
        ((TextView) findViewById(R.id.textViewGuessed)).setText(game.getActualWordToGuess());
        new Handler().postDelayed(() -> {
            newRound();
            ((TextView) findViewById(R.id.textViewGuessed)).setText(game.getActualGuessed());
            layouts.forEach(layout -> {
                for (int i = layout.getChildCount() - 1; i >= 0; i--) {
                    layout.getChildAt(i).setEnabled(true);
                }
            });
        }, 3000);
    }

    private void setImage(int resource) {
        findViewById(R.id.galgenImageView).setForeground(getDrawable(resource));
    }

    public void debugButtonClick(View view) {
        game.newRound(getRandomWord());
        makeWin();
    }

    public void readWords() {
        BufferedReader reader = null;
        try {
            for (String s : getAssets().list(""))
                Log.i(TAG, "readWords: " + s);
            reader = new BufferedReader(new InputStreamReader(getAssets().open("words.list", AssetManager.ACCESS_STREAMING), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }
}
