package com.example.geoquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity {

    private static String sLOG_TAG = QuizActivity.class.getSimpleName();
    private static String KEY_INDEX = "string";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex;
    private Question[] mQuestionBank = new Question[] {
        new Question(R.string.question_australia, true),
        new Question(R.string.question_oceans, true),
        new Question(R.string.question_mideast, false),
        new Question(R.string.question_africa, false),
        new Question(R.string.question_americas, true),
        new Question(R.string.question_asia, true)

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(sLOG_TAG, "onCreate(Bundle) is called");
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        // setting question when viewing app for the first time
        setQuestionText();

        // check the answer if it's true
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        // check the answer if it's false
        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        // next question
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex >= mQuestionBank.length - 1) mCurrentIndex = 0;
                else mCurrentIndex++;
                setQuestionText();
            }
        });

        // previous question
        mPrevButton = (ImageButton)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == 0) mCurrentIndex = mQuestionBank.length - 1;
                else mCurrentIndex--;
                setQuestionText();
            }
        });

    }

    private void setQuestionText () {

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

    }

    private void checkAnswer (boolean pressedAnswer) {
        int messageReId = -1;

        if (pressedAnswer == mQuestionBank[mCurrentIndex].isAnswer())
            messageReId = R.string.correct_toast;

        else
            messageReId = R.string.incorrect_toast;

        Toast.makeText(QuizActivity.this,
                messageReId,
                Toast.LENGTH_SHORT)
                .show();
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d(sLOG_TAG, "onStart() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(sLOG_TAG, "onResume() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(sLOG_TAG, "onPause() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(sLOG_TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }
}
