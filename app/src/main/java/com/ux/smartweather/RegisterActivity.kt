package com.ux.smartweather

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonContinuar = findViewById<Button>(R.id.button2)
        buttonContinuar.setOnClickListener {
            // Crear y mostrar el AlertDialog
            val alertDialog = AlertDialog.Builder(this)
                .setMessage("Cuenta creada")
                .setCancelable(false)
                .create()
            alertDialog.show()

            // Esperar 10 segundos y redirigir a la pantalla de inicio de sesión
            Handler(Looper.getMainLooper()).postDelayed({
                alertDialog.dismiss()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 5000) // 10 segundos
        }
    }
}