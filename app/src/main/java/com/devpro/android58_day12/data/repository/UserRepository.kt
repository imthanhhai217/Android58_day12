package com.devpro.android58_day12.data.repository

import com.devpro.android58_day12.data.model.User

interface UserRepository {
    fun getUser(): User
    fun saveUser(user: User)
}

