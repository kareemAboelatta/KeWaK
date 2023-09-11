package com.example.kewaapp.di

import com.example.kewaapp.domain.entities.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideUser(): User = User(
        firstName = "Kareem",
        lastName = "Kamal",
        followers = 144,
        email = "kareem@gmail.com",
        id = "User_id"
    )



}