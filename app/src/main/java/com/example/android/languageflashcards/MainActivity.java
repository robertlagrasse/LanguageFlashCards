package com.example.android.languageflashcards;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // position is a global variable so the buttons can update it and display can use it.

    int position = -1;

    // display() throws all of the text and picture on the screen each time it's called.

    private void display() {
        // setup some views
        TextView textNewWord = (TextView) findViewById(R.id.textNewWord);
        TextView textNativeWord = (TextView) findViewById(R.id.textNativeWord);
        TextView textPosition = (TextView) findViewById(R.id.textPosition);
        ImageView vocabPicture = (ImageView) findViewById(R.id.vocabPicture);

        MediaPlayer house = MediaPlayer.create(this, R.raw.house);
        MediaPlayer family = MediaPlayer.create(this, R.raw.family);
        MediaPlayer friend = MediaPlayer.create(this, R.raw.friend);
        MediaPlayer hand = MediaPlayer.create(this, R.raw.hand);
        MediaPlayer world = MediaPlayer.create(this, R.raw.world);
        // Two dimentional array for english and spanish word pairs

        String vocabWords[][]={
                {"House", "Casa"},
                {"Friend", "Amigo/Amiga"},
                {"World", "Mundo"},
                {"Hand", "Mano"},
                {"Family", "Familia"}};

        // This is a two dimensional array, so rows and columns. vocabWords[row][column]
        // The position variable identifies the row we want to look at.
        // Then we pick column 0 for the native word, or column 1 for the new language.

        textNewWord.setText(vocabWords[position][1]);
        textNativeWord.setText(vocabWords[position][0]);

        // This keeps track of the position we're in, vs the number of elements there are.
        // This allows the array to be of any size, without modification to this code.

        textPosition.setText((position + 1) + "/" + vocabWords.length);

        // I should be able to set a third column up in the vocabWords array to contain
        // an http:// web resource, and send that graphic resource to the vocabPicture view.
        // I'd then be able to eliminate all of this if/then/else stuff and keep it clean and
        // tight by just referencing the right element in the array and calling .setImage once
        // I'll get you next time, Gadget. Next time!

        if (position == 0) {
            vocabPicture.setImageResource(R.drawable.house);
            house.start();
        } else
        if (position == 1) {
            vocabPicture.setImageResource(R.drawable.friend);
            friend.start();
        } else
        if (position == 2) {
            vocabPicture.setImageResource(R.drawable.world);
            world.start();
        } else
        if (position == 3) {
            vocabPicture.setImageResource(R.drawable.hand);
            hand.start();
        } else
        if (position == 4) {
            vocabPicture.setImageResource(R.drawable.family);
            family.start();
        }
    }

    // Buttons increment and decrement global counter, and call display();

    public void nextButton(View view) {
        if (position == 4) {
            position = -1;
        }
        position += 1;
        Log.v("Next Button", "position: " + position);
        display();
    }
    public void backButton(View view) {
        if (position == 0) {
            position = 5;
        }
        position -= 1;
        Log.v("Back Button", "position: " + position);

        display();
    }
}
