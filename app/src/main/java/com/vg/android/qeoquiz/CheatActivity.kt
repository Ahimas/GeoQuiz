package com.vg.android.qeoquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_ANSWER_IS_SHOWN = "com.vg.android.qeoquiz.answer_is_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.vg.android.qeoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {

    private var answerIsTrue = false
    private lateinit var showAnswerButton : Button
    private lateinit var answerTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        showAnswerButton = findViewById(R.id.show_answer_button)
        answerTextView = findViewById(R.id.answer_text_view)
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_text
                else -> R.string.false_text
            }
            answerTextView.setText(answerText)
            setAnswerIsShown(true)
        }

    }

    private fun setAnswerIsShown(answerIsShown : Boolean) {
        val data = Intent().apply { putExtra(EXTRA_ANSWER_IS_SHOWN, answerIsShown)}

        setResult(RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }


}