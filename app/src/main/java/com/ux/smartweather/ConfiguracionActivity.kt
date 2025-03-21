package com.ux.smartweather

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.slider.Slider

class ConfiguracionActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)

        // Referencia al slider
        val slider = findViewById<Slider>(R.id.material_slider)

        // Agregar el Listener para mostrar la etiqueta flotante con el valor
        slider.addOnChangeListener { _, value, _ ->
            slider.setLabelFormatter { "%.0f".format(value) } // Muestra el valor sin decimales
        }

        // Referencia a los botones
        val btnCancelar = findViewById<Button>(R.id.cancelar)
        val btnGuardar = findViewById<Button>(R.id.guardar)

        // Al hacer clic en "Cancelar" vuelve a la pantalla principal
        btnCancelar.setOnClickListener {
            finish() // Cierra esta actividad y vuelve atrás
        }

        // Al hacer clic en "Guardar" vuelve a la pantalla principal
        btnGuardar.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
                .setMessage("Guardado")
                .setCancelable(false)
                .create()
            alertDialog.show()

            // Esperar 10 segundos y redirigir a la pantalla de inicio de sesión
            Handler(Looper.getMainLooper()).postDelayed({
                alertDialog.dismiss()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }, 5000)
        }

        drawerLayout = findViewById(R.id.drawer_layout)
        val menuButton: Button = findViewById(R.id.menu_button)
        val navView: NavigationView = findViewById(R.id.nav_view)

        menuButton.setOnClickListener {
            drawerLayout.openDrawer(navView)
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_option1 -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_option2 -> {
                    val intent = Intent(this, HistorialActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_logout -> {
                    showLogoutConfirmationDialog()
                }
            }
            drawerLayout.closeDrawer(navView)
            true
        }
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("¿Estás seguro de que deseas cerrar sesión?")
            .setPositiveButton("Sí") { dialog, id ->
                // Acción para cerrar sesión
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("No") { dialog, id ->
                // Cancelar
                dialog.dismiss()
            }
        builder.create().show()
    }
}
