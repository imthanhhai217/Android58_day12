package com.devpro.android58_day12.data.repository

import com.devpro.android58_day12.data.model.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    private var cachedUser = User()

    override fun getUser(): User = cachedUser

    override fun saveUser(user: User) {
        cachedUser = user
    }
}

