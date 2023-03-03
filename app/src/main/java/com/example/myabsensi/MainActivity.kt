package com.example.myabsensi

//import AdminActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myabsensi.pojo.LoginResponse
import com.example.myabsensi.retrofit.ApiService
import com.example.myabsensi.utils.PrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var inputUSer : EditText
    private lateinit var inputPassword : EditText
    private lateinit var btnLogin : Button
    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        init()
        checkLogin()

        btnLogin.setOnClickListener(this)
    }

    private fun init(){
        inputUSer = findViewById(R.id.login_dataUser)
        inputPassword = findViewById(R.id.login_dataPassword)
        btnLogin = findViewById(R.id.login_btn)
        prefManager = PrefManager(this)
    }

    private fun checkLogin(){
        val login = prefManager.isLogin()
        val type = prefManager.getType()
        if (login == true && type.equals("user")){
            val intent = Intent(this@MainActivity,UserActivity::class.java)
            startActivity(intent)
            finish()
        }else if (login == true && type.equals("admin")){
            val intent = Intent(this@MainActivity,AdminActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login(){
        val email = inputUSer.text.toString()
        val password = inputPassword.text.toString()
        ApiService.endpoint.login(email,password).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val status = response.body()?.status
                val user = response.body()?.data?.name
                val token = response.body()?.meta?.token
                val id = response.body()?.data?.id
                if (status == "success" && user.equals("Admin")){
                    val intent = Intent(this@MainActivity,AdminActivity::class.java)
                    prefManager.setLoggin(true)
                    prefManager.setLogin("admin",token,id!!)
                    prefManager.setName(user, email)
                    startActivity(intent)
                    Toast.makeText(this@MainActivity, "Selamat datang Admin", Toast.LENGTH_SHORT).show()
                    finish()
                }else if (status == "success") {
                    val intent = Intent(this@MainActivity,UserActivity::class.java)
                    prefManager.setLoggin(true)
                    prefManager.setLogin("user",token,id!!)
                    startActivity(intent)
                    Toast.makeText(this@MainActivity, "Selamat datang User", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this@MainActivity, "email /Password tidak sesuai", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Login gagal, coba beberapa saat lagi", Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.login_btn -> {
                login()
            }
        }
    }
}