package com.capstone.guideme

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.guideme.databinding.ActivityMainBinding
import com.capstone.guideme.model.User
import com.capstone.guideme.ui.camera.CameraActivity
import com.capstone.guideme.ui.home.HomeFragment
import com.capstone.guideme.ui.preview.PreviewActivity
import com.capstone.guideme.ui.profile.ProfileFragment
import com.capstone.guideme.ui.welcome.WelcomeActivity
import com.capstone.guideme.utils.UserPreference
import com.capstone.guideme.utils.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private lateinit var bottomNav : BottomNavigationView
    private lateinit var user: User

    @Suppress("DEPRECATION")
    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        setupViewModel()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun getMyData(): User {
        return this.user
    }

    private fun setupViewModel() {
        val pref = UserPreference.getInstance(dataStore)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(pref, this)
        )[MainViewModel::class.java]

        mainViewModel.getUser().observe(this) {
            if (it.isLogin) {
                this.user = it
                navigation(it)
            } else {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.changeLanguage -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                true
            }
            R.id.logout -> {
                mainViewModel.logOutUser()
                val i = Intent(this, WelcomeActivity::class.java)
                startActivity(i)
                finish()
                true
            }
            else -> true
        }
    }

    @Suppress("DEPRECATION")
    private fun navigation(user: User) {
        val userId = Bundle()
        Log.e("mainAct", user.userid.toString())
        Log.e("mainAct", user.token)
        Log.e("mainAct", user.fullname)
        userId.putString(EXTRA_TOKEN, user.token)
        userId.putInt(EXTRA_USERID, user.userid)

        loadFragment(HomeFragment())
        bottomNav = findViewById(R.id.nav_view)
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_camera -> {
                    val moveToCamera = Intent(this, PreviewActivity::class.java)
                    startActivity(moveToCamera)
                    return@setOnNavigationItemSelectedListener false
                }
                R.id.navigation_profile -> {
                    loadFragment(ProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }else -> false
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object{
        const val EXTRA_USERID = "userId"
        const val EXTRA_TOKEN = "token"
    }
}