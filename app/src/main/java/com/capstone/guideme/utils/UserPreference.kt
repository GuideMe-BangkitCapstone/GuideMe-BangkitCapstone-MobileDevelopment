package com.capstone.guideme.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.capstone.guideme.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    fun getUser(): Flow<User> {
        return dataStore.data.map { preferences ->
            User(
                preferences[FULLNAME].toString(),
                preferences[USER_ID] ?: 0,
                preferences[EMAIL].toString(),
                preferences[TOKEN].toString(),
                preferences[ISLOGIN] ?: false
            )
        }
    }

    suspend fun setUser(user: User) {
        dataStore.edit { preferences ->
            preferences[FULLNAME] = user.fullname
            preferences[USER_ID] = user.userid
            preferences[EMAIL] = user.email
            preferences[TOKEN] = user.token
            preferences[ISLOGIN] = true
        }
    }

    suspend fun logOutUser() {
        dataStore.edit { preferences ->
            preferences[FULLNAME] = ""
            preferences[USER_ID] = 0
            preferences[EMAIL] = ""
            preferences[TOKEN] = ""
            preferences[ISLOGIN] = false
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val FULLNAME = stringPreferencesKey("fullname")
        private val USER_ID = intPreferencesKey("user_id")
        private val EMAIL = stringPreferencesKey("email")
        private val TOKEN = stringPreferencesKey("token")
        private val ISLOGIN = booleanPreferencesKey("is_login")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}