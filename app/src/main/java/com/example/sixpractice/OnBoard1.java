package com.example.sixpractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OnBoard1 extends AppCompatActivity implements View.OnClickListener {

    private GestureDetectorCompat lSwipeDetector;
    private ConstraintLayout mainLayout;
    private TextView tvTitle;
    private TextView tvDescription;
    private ImageView ivIllustration;
    private ImageView ivPoints;
    private TextView btnSkip;

    private int currentPage = 0;

    private final String[] titleList = {"Анализы", "Уведомления", "Мониторинг"};
    private final String[] descriptionList = {
            "Экспресс сбор и получение проб",
            "Вы быстро узнаете о результатах",
            "Наши врачи всегда наблюдают за вашими показателями здоровья"
    };


    private final int[] illustrationRes = {
            R.drawable.illustration,
            R.drawable.illustration2,
            R.drawable.illustration3
    };

    private final int[] pointsRes = {
            R.drawable.group1,
            R.drawable.group2,
            R.drawable.group3
    };

    private static final int SWIPE_MIN_DISTANCE = 130;
    private static final int SWIPE_MAX_DISTANCE = 300;
    private static final int SWIPE_MIN_VELOCITY = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_on_board1);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lSwipeDetector = new GestureDetectorCompat(this, new MyGestureListener());

        // Привязка к ВАШИМ id
        mainLayout = findViewById(R.id.main_layout);
        tvTitle = findViewById(R.id.textView3);
        tvDescription = findViewById(R.id.textView4);
        ivIllustration = findViewById(R.id.imageView3);
        ivPoints = findViewById(R.id.imageView2);
        btnSkip = findViewById(R.id.button1);

        updatePageContent(currentPage);

        btnSkip.setOnClickListener(this);

        mainLayout.setOnTouchListener((v, event) -> lSwipeDetector.onTouchEvent(event));
    }

    private void updatePageContent(int page) {
        tvTitle.setText(titleList[page]);
        tvDescription.setText(descriptionList[page]);
        ivIllustration.setImageResource(illustrationRes[page]);
        ivPoints.setImageResource(pointsRes[page]);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_DISTANCE)
                return false;


            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_MIN_VELOCITY) {
                if (currentPage < titleList.length - 1) {
                    currentPage++;
                    updatePageContent(currentPage);
                }
                return true;
            }
            else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_MIN_VELOCITY) {
                if (currentPage > 0) {
                    currentPage--;
                    updatePageContent(currentPage);
                }
                return true;
            }
            return false;
        }
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(OnBoard1.this, Registration.class);
        startActivity(intent);
        finish();
    }
}