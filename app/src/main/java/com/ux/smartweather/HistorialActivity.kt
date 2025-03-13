package com.ux.smartweather

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import java.util.Calendar

class HistorialActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial)

        val fechaDesde = findViewById<EditText>(R.id.fechadesde)
        val fechaHasta = findViewById<EditText>(R.id.fechahasta)

        fechaDesde.setOnClickListener {
            showDatePickerDialog(fechaDesde)
        }

        fechaHasta.setOnClickListener {
            showDatePickerDialog(fechaHasta)
        }


        val btnGuardar = findViewById<Button>(R.id.boton20)
        btnGuardar.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
                .setMessage("Generado")
                .setCancelable(false)
                .create()
            alertDialog.show()

            // Esperar 10 segundos y redirigir a la pantalla de inicio de sesión
            Handler(Looper.getMainLooper()).postDelayed({
                alertDialog.dismiss()
                val intent = Intent(this, HistorialActivity::class.java)
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

    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                editText.setText(selectedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
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