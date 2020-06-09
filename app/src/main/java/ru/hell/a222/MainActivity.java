package ru.hell.a222;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Toolbar myToolbar;

    // Генератор случайностей
    private Random random = new Random();
    // Наш адаптер
    private ItemsDataAdapter adapter;
    // Список картинок, которые мы будем брать для нашего списка
    private List<Drawable> images = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        FloatingActionButton fab = findViewById(R.id.fab);
        ListView listView = findViewById(R.id.listView);
        setSupportActionBar(myToolbar);

        fillImages();



        // При тапе по кнопке добавим один новый элемент списка
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateRandomItemData();
            }
        });

        // Создаем и устанавливаем адаптер на наш список
        adapter = new ItemsDataAdapter(this, null);
        listView.setAdapter(adapter);

        // При тапе по элементу списка будем показывать его данные
/*        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Тут мы получаем и отображаем данные,
                // но можно сделать и перейти в новую активити с этими данными
                showItemData(position);
            }
        });*/

        // При долгом тапе по элементу списка будем удалять его
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showItemData(position);
                return true;
            }
        });

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

    // Заполним различными картинками, которые встроены в сам Android
    // ContextCompat обеспечит нам поддержку старых версий Android
    private void fillImages() {
        images.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_report_image));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_add));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_agenda));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_camera));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                android.R.drawable.ic_menu_call));
    }

    // Создадим ну почти случайные данные для нашего списка.
    // random.nextInt(граница_последнего_элемента)
    // Для каждого элемента мы возьмем 1 случайную картинку
    // из 5, которые мы сделали вначале.
    private void generateRandomItemData() {
        adapter.addItem(new ItemData(
                images.get(random.nextInt(images.size())),
                adapter.getCount() + " Оглавление, очень длинный текст который необходимо перенести",
                "Знакомый запах чуя в соцсетях,\n" +
                        "стараюсь я держаться на дистанции.\n" +
                        "Идёт, идёт у многих в головах\n" +
                        "бурление коричневой субстанции."));
    }

    // Покажем сообщение с данными
    private void showItemData(int position) {
        ItemData itemData = adapter.getItem(position);
        Toast.makeText(MainActivity.this,
                "Title: " + itemData.getTitle() + "\n" +
                     "Subtitle: " + itemData.getSubtitle() + "\n",
                Toast.LENGTH_SHORT).show();
    }

}
