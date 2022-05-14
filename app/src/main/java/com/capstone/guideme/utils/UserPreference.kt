package com.capstone.guideme.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.capstone.guideme.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    fun getUser(): Flow<User> {
        return dataStore.data.map { preferences ->
            User(
                preferences[FULLNAME].toString(),
                preferences[USER_ID].toString(),
                preferences[EMAIL].toString(),
                preferences[TOKEN].toString(),
                preferences[USERNAME].toString(),
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
            preferences[USERNAME] = user.username
            preferences[ISLOGIN] = true
        }
    }

    suspend fun logOutUser() {
        dataStore.edit { preferences ->
            preferences[FULLNAME] = ""
            preferences[USER_ID] = ""
            preferences[EMAIL] = ""
            preferences[TOKEN] = ""
            preferences[USERNAME] = ""
            preferences[ISLOGIN] = false
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val FULLNAME = stringPreferencesKey("fullname")
        private val USERNAME = stringPreferencesKey("username")
        private val USER_ID = stringPreferencesKey("userid")
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