package com.vg.android.qeoquiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private lateinit var nextButton : Button
    private lateinit var questionTextView : TextView

    private val questionBank = listOf(
        Question(R.string.question_africa, false),
        Question(R.string.question_oceans, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_australia, true),
        Question(R.string.question_americas, true)
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "Called on Create(Bundle?)")

        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { view : View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Called onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Called onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "called onDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Called onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "called onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Called onResume")
    }

    private fun updateQuestion() {
        var stringResId = questionBank[currentIndex].stringResId
        questionTextView.setText(stringResId)
    }

    private fun checkAnswer(userAnswer : Boolean) {
        var toastText = if (userAnswer == questionBank[currentIndex].answer) {
            R.string.correct_text
        } else {
            R.string.incorrect_text
        }

        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
    }
}