package com.example.kewaapp.home.data

import androidx.annotation.DrawableRes
import com.example.kewaapp.R

data class Quiz(
    val title: String,
    val description: String,
    val numberOfQuestions: Int,
    val timeLimit: String?,
    val author: String,
    val difficultyLevel: String?,
    val publicationDate: String?,
    val rating: Double?,
    val reviews: List<String>?,
    val categoryTags: List<String>,
    val coverImageURL: String?
)

fun createFakeQuizList(): List<Quiz> {
    val quizList = mutableListOf<Quiz>()

    // Quiz 1
    quizList.add(
        Quiz(
            "History Quiz: World War II",
            "Test your knowledge of World War II history.",
            10,
            "Time Limit: 20 minutes",
            "JohnDoe",
            "Medium",
            "August 15, 2023",
            4.5,
            listOf(
                "Great quiz!",
                "Challenging questions.",
                "Learned a lot."
            ),
            listOf("History", "War"),
            "https://example.com/quiz1_cover.jpg"
        )
    )

    // Quiz 2
    quizList.add(
        Quiz(
            "Mathematics Basics",
            "A quiz to test your basic math skills.",
            15,
            "No time limit",
            "MathGeek",
            "Easy",
            "September 5, 2023",
            4.0,
            listOf(
                "Good for beginners.",
                "Enjoyed solving these problems."
            ),
            listOf("Math", "Education"),
            "https://example.com/quiz2_cover.jpg"
        )
    )

    // Quiz 3
    quizList.add(
        Quiz(
            "Science Quiz: Planets",
            "Explore the planets in our solar system.",
            8,
            "Time Limit: 15 minutes",
            "ScienceBuff",
            "Medium",
            "July 25, 2023",
            4.2,
            listOf(
                "Informative quiz.",
                "Loved the visuals."
            ),
            listOf("Science", "Astronomy"),
            "https://example.com/quiz3_cover.jpg"
        )
    )

    // Quiz 4
    quizList.add(
        Quiz(
            "Geography Challenge",
            "Test your knowledge of world geography.",
            20,
            "Time Limit: 30 minutes",
            "GeoExplorer",
            "Hard",
            "August 1, 2023",
            3.8,
            listOf(
                "Challenging questions.",
                "Learned about new places."
            ),
            listOf("Geography", "Travel"),
            "https://example.com/quiz4_cover.jpg"
        )
    )

    return quizList
}


val fakeQuizzes = createFakeQuizList()



val subjects = listOf(
    Subject(
        id = "1",
        name = "Mathematics",
        description = "Study the world of numbers, equations, and calculations.",
        image = R.drawable.math
    ),
    Subject(
        id = "2",
        name = "Science",
        description = "Explore the mysteries of the natural world.",
        image = R.drawable.science
    ),
    Subject(
        id = "3",
        name = "History",
        description = "Learn about the past events and civilizations.",
        image = R.drawable.history
    ),
    Subject(
        id = "4",
        name = "English",
        description = "Improve your language and communication skills.",
        image = R.drawable.english
    ),
    Subject(
        id = "5",
        name = "Computer Science",
        description = "Discover the world of programming and technology.",
        image = R.drawable.computer
    ),
    Subject(
        id = "6",
        name = "Physics",
        description = "Explore the fundamental principles of the physical world.",
        image = R.drawable.physics
    ),
    Subject(
        id = "7",
        name = "Art",
        description = "Express yourself through creative forms of art and design.",
        image = R.drawable.art
    ),
    Subject(
        id = "8",
        name = "Geography",
        description = "Study the Earth's landscapes, environments, and cultures.",
        image = R.drawable.geography
    )
    // Add more subjects as needed
)


data class Subject(
    val id: String,
    val name: String,
    val description: String,
    @DrawableRes val image: Int,
)







