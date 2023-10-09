package com.example.kewaapp.home.presentaion.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class QuizViewModel @Inject constructor() : ViewModel() {

    private val _questionsState = mutableStateOf<List<Question>>(emptyList())
    var questionsState: State<List<Question>> =   _questionsState


    init {
        _questionsState.value =  getQuestions()
    }





    fun answerQuestion(questionId: String , answer : Int){
         _questionsState.value.map {question ->
            if (question.questionId == questionId ){
                question.selectedAnswer = answer
            }
        }
    }


}

data class Question(
    val questionId: String,
    val question: String,
    val answers: List<String>,
    val numberOfCorrectAnswer: Int,
    val initialAnswer: Int = -1,

    ){
    var selectedAnswer by mutableIntStateOf(initialAnswer)



}

fun getQuestions(): List<Question> {
    return listOf(
        Question(
            "q1",
            "What does API stand for in Android development?",
            listOf(
                "Application Programming Interface",
                "Android Programming Interface",
                "Application Program Interface",
                "Android Program Interface"
            ),
            0
        ),
        Question(
            "q2",
            "Which layout is used to align the children of a RelativeLayout relative to each other?",
            listOf("LinearLayout", "FrameLayout", "GridLayout", "ConstraintLayout"),
            3
        ),
        Question(
            "q3",
            "What is the purpose of the 'adb' tool in Android development?",
            listOf(
                "Android Debug Bridge",
                "Application Development Bridge",
                "Android Development Builder",
                "Application Debugging Bridge"
            ),
            0
        ),
        Question(
            "q4",
            "In Android, what is an Intent used for?",
            listOf(
                "To display an activity",
                "To perform an action",
                "To declare permissions",
                "To define layouts"
            ),
            1
        ),
        Question(
            "q5",
            "What is the purpose of the 'AndroidManifest.xml' file?",
            listOf(
                "Define the app's layout",
                "Specify app permissions",
                "Declare activities",
                "Handle user input"
            ),
            2
        ),
        Question(
            "q6",
            "Which component is responsible for managing the app's lifecycle in Android?",
            listOf("Activity", "Fragment", "Service", "Application"),
            3
        ),
        Question(
            "q7",
            "What is the minimum Android API level required for using Jetpack Compose?",
            listOf("API level 21", "API level 19", "API level 14", "API level 26"),
            0
        ),
        Question(
            "q8",
            "In Android, what is the purpose of the 'ViewModel' class?",
            listOf(
                "Handle UI-related data",
                "Perform network operations",
                "Define app layouts",
                "Handle user input"
            ),
            0
        ),
        Question(
            "q9",
            "Which Gradle file is used to configure dependencies in an Android project?",
            listOf(
                "build.gradle (Project)",
                "build.gradle (Module)",
                "settings.gradle",
                "gradle.properties"
            ),
            1
        ),
        Question  (
            "q10",
            "What is the purpose of the 'R' class in Android?",
            listOf(
                "Handle resources",
                "Define layouts",
                "Manage permissions",
                "Run background tasks"
            ),
            0
        )
    )
}















