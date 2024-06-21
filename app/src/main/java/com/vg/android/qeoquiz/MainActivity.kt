package com.vg.android.qeoquiz

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton : Button
    private lateinit var falseButton : Button
    private lateinit var nextButton : Button
    private lateinit var cheatButton : Button
    private lateinit var questionTextView : TextView

    private val quizViewModel : QuizViewModel by lazy {
        ViewModelProviders.of(this)[QuizViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "Called on Create(Bundle?)")

        setContentView(R.layout.activity_main)

        quizViewModel.currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0

        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result : ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                quizViewModel.isCheater = result.data?.getBooleanExtra(EXTRA_ANSWER_IS_SHOWN, false) ?: false
            }
        }

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        cheatButton = findViewById(R.id.cheat_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { view : View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener{
            quizViewModel.moveToNext()
            updateQuestion()
        }

        cheatButton.setOnClickListener() {
            val answerIsTrue = quizViewModel.currentQuestionAnswer;
            val intent = CheatActivity.newIntent(this, answerIsTrue)

            startForResult.launch(intent)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt(KEY_INDEX, quizViewModel.currentIndex)
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
        var stringResId = quizViewModel.currentQuestionText
        questionTextView.setText(stringResId)
    }

    private fun checkAnswer(userAnswer : Boolean) {

        val textID = when {
            quizViewModel.isCheater -> R.string.judgment_toast
            quizViewModel.currentQuestionAnswer == userAnswer -> R.string.correct_text
            else -> R.string.incorrect_text
        }

        Toast.makeText(this, textID, Toast.LENGTH_SHORT).show()
    }
}