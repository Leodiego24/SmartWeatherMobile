package com.ux.smartweather

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val buttonContinuar = findViewById<Button>(R.id.button2)
        buttonContinuar.setOnClickListener {
            // Crear y mostrar el AlertDialog
            val alertDialog = AlertDialog.Builder(this)
                .setMessage("Contraseña enviada a tu correo")
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