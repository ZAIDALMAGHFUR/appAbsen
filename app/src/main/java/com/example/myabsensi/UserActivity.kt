package com.example.myabsensi

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myabsensi.databinding.ActivityUserBinding
import com.example.myabsensi.utils.PrefManager
import com.google.android.material.navigation.NavigationView

class UserActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var prefManager: PrefManager
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_user)
//        setContentView(binding.root)

        prefManager = PrefManager(this)

//        binding.btnLogout.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_logout -> {
                logout()
            }
        }
        return true
    }

    //membuat logout
    private fun logout() {
        // Clear all data from prefManager
        prefManager.clear()
        // Redirect to LoginActivity
        startActivity(Intent(this, MainActivity::class.java))
        showMessage("Berhasil logout")
        finish()
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
