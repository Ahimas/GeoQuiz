package com.vg.android.qeoquiz

import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {

    var currentIndex = 0
    var isCheater = false

    private val questionBank = listOf(
        Question(R.string.question_africa, false),
        Question(R.string.question_oceans, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_australia, true),
        Question(R.string.question_americas, true)
    )

    val currentQuestionAnswer : Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText : Int
        get() = questionBank[currentIndex].stringResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

}