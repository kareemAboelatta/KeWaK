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
    var questionsState: State<List<Question>> = _questionsState


    var valid = mutableStateOf(true)


    init {
        _questionsState.value = getQuestions()
    }


    fun answerQuestion(questionId: String, answer: Int) {
        _questionsState.value.map { question ->
            if (question.questionId == questionId) {
                question.selectedAnswer = answer
            }
        }
    }

    fun isAllQuestionsAnswered(): Boolean {
        return _questionsState.value.any {
            it.selectedAnswer == -1
        }.not()
    }

    fun getResult(): ResultOfExam {
        val total = _questionsState.value.size //20
        var result = 0 // 18

        var incorrectAnswers = mutableListOf<String>()

        _questionsState.value.forEach {
            if (it.selectedAnswer == it.numberOfCorrectAnswer)
                result++
            else
                incorrectAnswers.add(it.questionId)
        }

        val percent = (result / total ) * 100

        val numberOfCoinsForEachQuestion : Float = 500f / total  // 25

        val earnCoins : Float = result * numberOfCoinsForEachQuestion
        return  ResultOfExam(
            total =  total,
            studentResult = result,
            idsOfIncorrectAnswers = incorrectAnswers,
            percent =percent,
            earnedCoins =earnCoins.toInt()

        )
    }

}


data class ResultOfExam(
    val total: Int,
    val studentResult: Int,
    val idsOfIncorrectAnswers: List<String>,
    val percent:Int,
    val earnedCoins:Int
)

data class Question(
    val questionId: String,
    val question: String,
    val answers: List<String>,
    val numberOfCorrectAnswer: Int,
    val initialAnswer: Int = -1,

    ) {
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
/*
        Question(
            "q10",
            "What is the purpose of the 'R' class in Android?",
            listOf(
                "Handle resources",
                "Define layouts",
                "Manage permissions",
                "Run background tasks"
            ),
            0
        ), Question(
            "q11",
            "What is the purpose of the 'RecyclerView' in Android?",
            listOf(
                "Display a list of items efficiently",
                "Manage app permissions",
                "Handle UI events",
                "Define app layouts"
            ),
            0
        ),
        Question(
            "q12",
            "In Android, what is the role of the 'FragmentManager'?",
            listOf(
                "Manage fragments and their transactions",
                "Handle background tasks",
                "Define animations",
                "Manage app preferences"
            ),
            0
        ),
        Question(
            "q13",
            "Which component is used for background processing in Android?",
            listOf("Activity", "Service", "BroadcastReceiver", "ContentProvider"),
            1
        ),
        Question(
            "q14",
            "What is the purpose of the 'SQLiteOpenHelper' class in Android?",
            listOf(
                "Manage app permissions",
                "Handle UI events",
                "Database creation and version management",
                "Define app layouts"
            ),
            2
        ),
        Question(
            "q15",
            "Which XML file is used for defining the layout of an Android activity?",
            listOf("styles.xml", "manifest.xml", "layout.xml", "activity_main.xml"),
            3
        ),
        Question(
            "q16",
            "What is the purpose of the 'Handler' class in Android?",
            listOf(
                "Manage app permissions",
                "Handle UI events",
                "Manage threads and runnables",
                "Define app layouts"
            ),
            2
        ),
        Question(
            "q17",
            "In Android, what does the term 'ANR' stand for?",
            listOf(
                "Application Not Responding",
                "Android Notification Receiver",
                "Activity Navigation Resolver",
                "App Network Request"
            ),
            0
        ),
        Question(
            "q18",
            "Which layout is best suited for evenly distributing child views in a row or column?",
            listOf("LinearLayout", "RelativeLayout", "GridLayout", "ConstraintLayout"),
            0
        ),
        Question(
            "q19",
            "What is the purpose of the 'SharedPreferences' in Android?",
            listOf(
                "Manage app permissions",
                "Store and retrieve key-value pairs",
                "Handle UI events",
                "Define app layouts"
            ),
            1
        ),
        Question(
            "q20",
            "In Android, what is the purpose of the 'IntentFilter'?",
            listOf(
                "Define the app's layout",
                "Specify which components can respond to an intent",
                "Declare app permissions",
                "Handle user input"
            ),
            1
        )
*/



    )
}















