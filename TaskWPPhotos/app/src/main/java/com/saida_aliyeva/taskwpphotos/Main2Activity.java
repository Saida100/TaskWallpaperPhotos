package com.saida_aliyeva.taskwpphotos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView imageView;
    Button btnDownloadImage;
    Button btnShowInfo;
    LinearLayout linearLayoutContainer, linearButtons;
    boolean checkClikedBtnShowInfo=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = findViewById(R.id.raw);
        btnDownloadImage = findViewById(R.id.downloadImage);
        btnShowInfo = findViewById(R.id.showInfo);
        linearLayoutContainer = findViewById(R.id.container);
        linearButtons = findViewById(R.id.linear_buttons);
        Bundle bundle = getIntent().getExtras();
        final String photoUrl = bundle.getString("image");
        Log.e("photoUrl", photoUrl);
        final String created_at = bundle.getString("created_at");
        final String update_At = bundle.getString("updated_at");
        final String color = bundle.getString("color");
        final String width = bundle.getString("width");
        final String height = bundle.getString("height");
        final String alt_description = bundle.getString("alt_description");


        Picasso.get().load(photoUrl).into(imageView);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());
        btnDownloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadImage(photoUrl);
            }
        });

        btnShowInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkClikedBtnShowInfo=true;
                setOnlyFragmentVisible();
                FragmentInfo fragmentInfo = new FragmentInfo();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, fragmentInfo);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Bundle bundle = new Bundle();
                bundle.putString("created_at", created_at);
                bundle.putString("updated_at", update_At);
                bundle.putString("color", color);
                bundle.putString("width", width);
                bundle.putString("height", height);
                bundle.putString("alt_description", alt_description);
                fragmentInfo.setArguments(bundle);

            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleGestureDetector.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            //      linearLayoutContainer.setVisibility(View.GONE);
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f,
                    Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }

    private void downloadImage(String photoUrl) {
        // fayl yuklenir
        DownloadManager downloadManager;
        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(photoUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadManager.enqueue(request);

    }


    public void setButtonsVisible() {
        linearLayoutContainer.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
        linearButtons.setVisibility(View.VISIBLE);
    }


    public void setOnlyFragmentVisible() {
        linearLayoutContainer.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
      linearButtons.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setButtonsVisible();
    }
}

