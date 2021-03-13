package com.example.android.trainingapplication;

import androidx.appcompat.app.AppCompatActivity;
;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillList();
    }
    int currentLine = -1;
    List<String> lista;

    public void prebaraj(View view) throws FileNotFoundException {

        EditText editTextZemiIme = (EditText) findViewById(R.id.editTextZemiIme);
        String vneseno = editTextZemiIme.getText().toString();

        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setText(sporedi(vneseno));

    }

    private String sporedi(String zbor) {

        if (lista.size() == 0) {
            return "Recnikot e prazen!";
        } else {
            for(String e: lista) {
                String[] str = e.split("\t");
                if (str[0].equals(zbor)) {
                    return str[1];
                }
            }
        }
            return "Nema Takov Zbor";
    }

    public void dodavanje(View view) {

        EditText EditTextDodajMkd = (EditText) findViewById(R.id.EditTextDodajMkd);
        EditText EditTextDodajAng = (EditText) findViewById(R.id.EditTextDodajAng);

        lista.add(EditTextDodajMkd.getText().toString() + "\t" + EditTextDodajAng.getText().toString());
        dumpToFile();

    }


    public void izbrisi(View view) throws FileNotFoundException {

        if (lista.size() == 0) {
            return;
        }

        TextView textViewMakedonski = (TextView) findViewById(R.id.textViewMakedonski);
        TextView textViewAngliski = (TextView) findViewById(R.id.textViewAngliski);


        lista.remove(currentLine);
        currentLine --;


        if (currentLine == - 1) {
            textViewMakedonski.setText("");
            textViewAngliski.setText("");
        } else {

            String[] str = lista.get(currentLine).split("\t");
            textViewMakedonski.setText(str[0]);
            textViewAngliski.setText(str[1]);
        }


        dumpToFile();

    }

    public void prikaziSledenZbor(View view) throws FileNotFoundException {

        if (lista.size() == 0) {
            return;
        }
        currentLine++;
        if (currentLine == lista.size()) {
            currentLine = 0;
        }

        TextView textViewMakedonski = (TextView) findViewById(R.id.textViewMakedonski);
        TextView textViewAngliski = (TextView) findViewById(R.id.textViewAngliski);

        String[] str = lista.get(currentLine).split("\t");

        textViewMakedonski.setText(str[0]);
        textViewAngliski.setText(str[1]);
    }


    public void zacuvaj(View view) throws FileNotFoundException {

        if (lista.size() == 0) {
            return;
        }

        TextView textViewMakedonski = (TextView) findViewById(R.id.textViewMakedonski);
        TextView textViewAngliski = (TextView) findViewById(R.id.textViewAngliski);

        EditText EditTextDodajMkd = (EditText) findViewById(R.id.EditTextDodajMkd);
        EditText EditTextDodajAng = (EditText) findViewById(R.id.EditTextDodajAng);

        lista.set(currentLine, EditTextDodajMkd.getText().toString() + "\t" + EditTextDodajAng.getText().toString());

        textViewMakedonski.setText(EditTextDodajMkd.getText());
        textViewAngliski.setText(EditTextDodajAng.getText());

        dumpToFile();

    }

    public void edit(View view) {

        TextView textViewMakedonski = (TextView) findViewById(R.id.textViewMakedonski);
        TextView textViewAngliski = (TextView) findViewById(R.id.textViewAngliski);

        EditText EditTextDodajMkd = (EditText) findViewById(R.id.EditTextDodajMkd);
        EditText EditTextDodajAng = (EditText) findViewById(R.id.EditTextDodajAng);

        EditTextDodajMkd.setText(textViewMakedonski.getText());
        EditTextDodajAng.setText(textViewAngliski.getText());
    }

    public void dumpToFile() {

        try(PrintStream output = new PrintStream(openFileOutput("dictionary.txt", MODE_PRIVATE))) {
            for (String e: lista) {
                output.println(e);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void fillList() {
        lista = new ArrayList<>();


        try (Scanner sc = new Scanner(openFileInput("dictionary.txt"))) {
            while (sc.hasNextLine()) {
                lista.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}


    //prikazuvanje, izmenuvanje, izbrisi


