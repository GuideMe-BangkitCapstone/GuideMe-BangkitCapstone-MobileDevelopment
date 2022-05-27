package com.capstone.guideme

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StringRes
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.capstone.guideme.databinding.ActivityMainBinding
import com.capstone.guideme.ui.camera.CameraActivity
import com.capstone.guideme.ui.home.HomeFragment
import com.capstone.guideme.ui.profile.ProfileFragment
import com.capstone.guideme.ui.profile.ProfileViewModel
import com.capstone.guideme.ui.welcome.WelcomeActivity
import com.capstone.guideme.utils.UserPreference
import com.capstone.guideme.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    lateinit var bottomNav : BottomNavigationView

    @Suppress("DEPRECATION")
    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        setupViewModel()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupViewModel() {
        val pref = UserPreference.getInstance(dataStore)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(pref, this)
        )[MainViewModel::class.java]

        mainViewModel.getUser().observe(this) {
            if (it.isLogin) {
                navigation()
                val userId = Bundle()
                Log.e("mainAct", it.userid.toString())
                Log.e("mainAct", it.token)
                Log.e("mainAct", it.fullname)
                userId.putString(EXTRA_USERID, it.userid.toString())
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
    private fun navigation() {
        loadFragment(HomeFragment())
        bottomNav = findViewById<BottomNavigationView>(R.id.nav_view)
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_camera -> {
                    val moveToCamera = Intent(this, CameraActivity::class.java)
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
    }
}