package ru.hell.a222;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar myToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setSupportActionBar(myToolbar);

    }

    private void init() {
        myToolbar = findViewById(R.id.my_toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_open_notes) {
            Intent intentNotes = new Intent(MainActivity.this, NotesActivity.class);
            startActivity(intentNotes);
            Toast.makeText(MainActivity.this, "Отркыть записную книжку", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.action_open_mail) {
            Intent intentNotes = new Intent(MainActivity.this, MailActivity.class);
            startActivity(intentNotes);
            Toast.makeText(MainActivity.this, "Оформить подписку", Toast.LENGTH_LONG).show();
            return true;
        }

        if (id == R.id.action_open_cities) {
            Intent intentNotes = new Intent(MainActivity.this, CitiesActivity.class);
            startActivity(intentNotes);
            Toast.makeText(MainActivity.this, "Отркыть города", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
