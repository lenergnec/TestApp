package com.example.testapp

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.testapp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para cambiar el color de la barra de acciones o actionbar
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.NectarBlue)))

        //Cambiar texto en el action bar
        this.title = "Bienvenido a TestApp"
        //Creamos dos variables de tipo de view
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        //Para crear la accion del drawer layout
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.Abierto, R.string.Cerrado)
        drawerLayout.addDrawerListener(toggle)
        //Para mostrar las 3 barras arriba para abrir el panel de opciones
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Para seleccionar un item del drawer y poder ir a realizasr cualquier accion en una del menu
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.convertirDist -> Toast.makeText(this, "Convertir", Toast.LENGTH_LONG).show()
                R.id.convertirVol -> Toast.makeText(this, "Convertir", Toast.LENGTH_LONG).show()
            }
            true
        }

        intent()


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
           return  true
        }
        return super.onOptionsItemSelected(item)
    }

    fun intent() {
        binding.imageButton.setOnClickListener {
            startActivity(Intent(this, Rendimiento::class.java))

        }
        binding.imageButton2.setOnClickListener {
            startActivity(Intent(this, costosKm::class.java))

        }
        binding.imageButton3.setOnClickListener {
            startActivity(Intent(this, ImpuestosSlv::class.java))

        }
        binding.imageButton4.setOnClickListener {
            startActivity(Intent(this, Mantenimiento::class.java))

        }
        binding.imageButton5.setOnClickListener {
            startActivity(Intent(this, Descuentos::class.java))

        }
        binding.imageButton6.setOnClickListener {
            startActivity(Intent(this, co2::class.java))

        }


    }

}