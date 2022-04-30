package com.haryanvis.booknarrator;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;

import com.haryanvis.booknarrator.model.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private int currentPosition;
    private ArrayList<String> playlist;
    private ImageView ivPP , ivNext , ivPrev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getSupportActionBar().hide();
        ivPP = findViewById(R.id.ivPP);
        ivNext = findViewById(R.id.ivNext);
        ivPrev = findViewById(R.id.ivPrev);
       // playlist = (ArrayList) getIntent().getParcelableArrayListExtra("books");
        playlist = new ArrayList<>();
       String url = "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3";// your URL here
        playlist.add(url);
        currentPosition = 0;
        play();
    }

    public void ppClicked(View view) {
        if(mediaPlayer != null){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                ivPP.setImageResource(R.drawable.ic_baseline_play);
            }
            else{
                mediaPlayer.start();
                ivPP.setImageResource(R.drawable.ic_baseline_pause);
            }
        }
    }

    public void nextClicked(View view) {
        if(mediaPlayer != null){
            if(playlist.size() - 1 > currentPosition) {
                mediaPlayer.stop();
                mediaPlayer = null;
                currentPosition++;
                play();
            }
            else
                Toast.makeText(getApplicationContext(), "Queue is Empty", Toast.LENGTH_SHORT).show();
        }
    }

    public void play(){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes( new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        );
        try {
            mediaPlayer.setDataSource(playlist.get(currentPosition));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
//            finish();
        }
    }

    public void prevClicked(View view) {
        if(mediaPlayer != null){
            mediaPlayer.stop();
            if(currentPosition > 0) {
                mediaPlayer = null;
                currentPosition--;
                play();
            }else{
                mediaPlayer = null;
                currentPosition = 0;
                play();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer = null;
    }
}