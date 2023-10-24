package com.example.chat.domain.model



data class User(
    val id: String,               // Unique identifier for the user
    val username: String,         // Display name or nickname in the chat
    val email: String,            // Email for communication or account recovery
    val profileImageUrl: String?, // Optional URL for the user's profile image
    val role: UserRole,           // Role to distinguish between student and teacher
    val lastSeen: Long,           // Timestamp of last activity, can be used to show online/offline status
    val dateJoined: Long,         // Timestamp of when the user joined the platform
    val bio: String? = null       // Optional short bio or description about the user
)

enum class UserRole {
    STUDENT,
    TEACHER
}


