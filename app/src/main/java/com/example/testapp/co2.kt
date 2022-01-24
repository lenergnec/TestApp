package com.example.testapp

import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog

class co2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_co2)
        //Para cambiar el color de la barra de acciones o actionbar
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.NectarBlue)))


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.rendEfic -> {
                startActivity(Intent(this, Rendimiento::class.java))
                return super.onOptionsItemSelected(item)
            }
            R.id.regresar ->{

                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Con cuidado")

                dialog.setMessage("¿Deseas volver al menu principal?")

                dialog.setPositiveButton("Si")
                { dialogInterface: DialogInterface, _: Int ->
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

                dialog.setNegativeButton("No") { dialogInterface: DialogInterface, _: Int ->
                }
                dialog.show()

                return super.onOptionsItemSelected(item)

            }
            R.id.costoKm -> {
                startActivity(Intent(this, costosKm::class.java))
                finish()
                return super.onOptionsItemSelected(item)
            }
            R.id.impSlv -> {
                startActivity(Intent(this, ImpuestosSlv::class.java))
                finish()
                return super.onOptionsItemSelected(item)
            }
            R.id.mntto -> {
                startActivity(Intent(this, Mantenimiento::class.java))
                finish()
                return super.onOptionsItemSelected(item)
            }
            R.id.descuentos -> {
                startActivity(Intent(this, Descuentos::class.java))
                finish()
                return super.onOptionsItemSelected(item)
            }
            R.id.co2 -> {
                startActivity(Intent(this, co2::class.java))
                finish()
                return super.onOptionsItemSelected(item)
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }
}