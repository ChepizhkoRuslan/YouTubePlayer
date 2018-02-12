package com.chepizhko.youtubeplayer;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends YouTubeBaseActivity implements View.OnClickListener{
    public static final String KEY = "AIzaSyABxoTkKiZcUkrJzV5e0qi4jt0hsrBGDK4";
    private YouTubePlayerView mYouTubePlayerView;
    private YouTubePlayer.OnInitializedListener mOnInitializedListener;
    Button mButton;
    Button search;
    EditText editSearch;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.googleapis.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mYouTubePlayerView = (YouTubePlayerView)findViewById(R.id.view);
        mButton = (Button)findViewById(R.id.play);
        mButton.setOnClickListener(this);
        search = (Button)findViewById(R.id.search);
        search.setOnClickListener(this);
        editSearch = (EditText)findViewById(R.id.editSearch);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                 youTubePlayer.loadVideo("s9e9p_nTNMc");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play:
                mYouTubePlayerView.initialize(KEY, mOnInitializedListener);
                break;
            case R.id.search:
                // если editSearch не пустой
                if(!TextUtils.isEmpty(editSearch.getText().toString())){
                    final APIService service = retrofit.create(APIService.class);
                    Call<JsonObject> resp = service.callBack(editSearch.getText().toString());
                    resp.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {

                        }
                    });

                }
                break;
            default:
                break;

        }

    }
}
