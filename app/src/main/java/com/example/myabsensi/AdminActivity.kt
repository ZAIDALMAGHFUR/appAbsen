package com.example.myabsensi

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.myabsensi.databinding.ActivityIntiBinding
import com.example.myabsensi.utils.PrefManager

class AdminActivity : AppCompatActivity() {

    private lateinit var prefManager: PrefManager

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityIntiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIntiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarInti.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_inti)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



        //memanggil nama dan email yang login dari shared preference
        prefManager = PrefManager(this)
        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.textUserName).text = prefManager.getName()
        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.textUserEmail).text = prefManager.getEmail()

        //membuat logout
        binding.navView.menu.findItem(R.id.nav_logout).setOnMenuItemClickListener {
            // Clear all data from prefManager
            prefManager.clear()
            // Redirect to LoginActivity
            startActivity(Intent(this, MainActivity::class.java))
            masege()
            finish()
            true
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_inti)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun masege(){
        Toast.makeText(this, "Anda Berhasil logout", Toast.LENGTH_SHORT).show()
    }

}