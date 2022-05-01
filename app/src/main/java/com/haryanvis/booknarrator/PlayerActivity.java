package com.haryanvis.booknarrator;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haryanvis.booknarrator.model.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private int currentPosition;
    public static List<Book> playlist;
    private TextView tvTitle , tvProgress , tvMax;
    private SeekBar seekBar;
    private Handler handler = new Handler();
    private Runnable updateSeekbar = new Runnable() {
        @Override
        public void run() {
            int curr =mediaPlayer.getCurrentPosition() /1000;
            seekBar.setProgress(curr);
            String tvc = String.format("%2d:%2d",curr/60,curr%60);
            tvProgress.setText(tvc);
            handler.postDelayed(this, 1000);
        }
    };
    private ImageView ivPP , ivNext , ivPrev , ivCover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getSupportActionBar().hide();
        ivPP = findViewById(R.id.ivPP);
        ivNext = findViewById(R.id.ivNext);
        ivPrev = findViewById(R.id.ivPrev);
        ivCover = findViewById(R.id.ivCover);
        tvTitle = findViewById(R.id.tvName);
        tvMax = findViewById(R.id.tvMax);
        tvProgress = findViewById(R.id.tvProgress);
        seekBar = findViewById(R.id.seekBar3);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                mediaPlayer.seekTo(progress * 1000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        currentPosition = 0;
        play();
    }

    public void ppClicked(View view) {
        if(mediaPlayer != null){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                ivPP.setImageResource(R.drawable.ic_baseline_play);
                handler.removeCallbacks(updateSeekbar);
            }
            else{
                mediaPlayer.start();
                ivPP.setImageResource(R.drawable.ic_baseline_pause);
                handler.postDelayed(updateSeekbar , 0);
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


            tvTitle.setText(playlist.get(currentPosition).getName());
            Glide.with(this).load(playlist.get(currentPosition).getCoverImage()).into(ivCover);
            mediaPlayer.setDataSource(playlist.get(currentPosition).getSrcLink());
            tvMax.setText((int)playlist.get(currentPosition).getLength()/60+":"+(int)playlist.get(currentPosition).getLength()%60);
            mediaPlayer.prepare();
            seekBar.setMax((int)playlist.get(currentPosition).getLength());
            handler.postDelayed(updateSeekbar , 0);
            mediaPlayer.start();

        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
             finish();
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
        playlist = null;
        mediaPlayer = null;
        handler.removeCallbacks(updateSeekbar);
    }

}
