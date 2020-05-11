package de.rubeen.apps.android.galgenmaennchen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    MainActivity thisActivity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createEventListener();
    }

    private void createEventListener() {
        Intent singlePlayer = new Intent(thisActivity, GameActivity.class);
        Intent multiPlayer = new Intent(thisActivity, GameActivity.class);
        singlePlayer.putExtra(Constants.MODE_KEY, Constants.VALUE_SINGLE);
        multiPlayer.putExtra(Constants.MODE_KEY, Constants.VALUE_MULTI);

        findViewById(R.id.btn_playGame).setOnClickListener(view -> thisActivity.startActivity(singlePlayer));
        findViewById(R.id.btn_playGameMultiPlayer).setOnClickListener(view -> thisActivity.startActivity(multiPlayer));
    }
}