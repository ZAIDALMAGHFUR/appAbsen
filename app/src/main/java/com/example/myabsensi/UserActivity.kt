    package com.example.myabsensi

    import android.content.Intent
    import android.os.Bundle
    import android.view.MenuItem
    import android.widget.ImageView
    import android.widget.TextView
    import android.widget.Toast
    import androidx.appcompat.app.AppCompatActivity
    import com.example.myabsensi.databinding.ActivityUserBinding
    import com.example.myabsensi.utils.PrefManager
    import com.google.android.material.navigation.NavigationView

    class UserActivity : AppCompatActivity(){

        private lateinit var prefManager: PrefManager
        private lateinit var binding: ActivityUserBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityUserBinding.inflate(layoutInflater)
            setContentView(R.layout.activity_user)
    //        setContentView(binding.root)

            //memanggil nama dan email yang login dari shared preference
            prefManager = PrefManager(this)
            val headerView = binding.NameUser.findViewById<TextView>(R.id.NameUser)
            headerView.text = prefManager.getName()

            //membuat logout
            //menggunakan fungsi onNavigationItemSelected
            val btnLogout = findViewById<ImageView>(R.id.btn_logout)
            btnLogout.setOnClickListener {
                // Clear all data from prefManager
                prefManager.clear()
                // Redirect to LoginActivity
                val intent = Intent(this@UserActivity, MainActivity::class.java)
                startActivity(intent)
                masege()
                finish()
            }


        }

        fun masege(){
            Toast.makeText(this, "Anda Berhasil logout", Toast.LENGTH_SHORT).show()
        }

    }
