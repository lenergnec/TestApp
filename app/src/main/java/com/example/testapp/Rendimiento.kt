package com.example.testapp

import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.text.method.DigitsKeyListener
import android.text.method.TextKeyListener.clear
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import com.example.testapp.databinding.ActivityRendimientoBinding
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_rendimiento.*
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

class Rendimiento : AppCompatActivity() {
    private lateinit var binding: ActivityRendimientoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRendimientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Para cambiar el color de la barra de acciones o actionbar
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.NectarBlue)))

        //Cambiar texto en el action bar
        this.title = "Rendimiento y Eficiencia"

        /*recorrido = this.findViewById(R.id.recorridoEditext)
        litros = this.findViewById(R.id.litrosEditext)*/

        calcRendimiento()
        calcEficiencia()
        calcLostGain()
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
            R.id.regresar -> {

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

    fun calcRendimiento() {
        binding.calcuRendimiento.setOnClickListener {
            var recorrido = binding.recorridoEditext.text.toString().trim()
            var litros = binding.litrosEditext.text.toString().trim()


            if (binding.recorridoEditext.text.toString()
                    .isNotEmpty() && binding.litrosEditext.text.toString().isNotEmpty() && recorrido.toDouble() > 0 && litros.toDouble() > 0
            ) {
                var calcRend = (recorrido.toDouble()) * (litros.toDouble())

                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Resultado")

                var df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING

                dialog.setMessage("- El rendimiento calculado fue: ${df.format(calcRend)} Km/Lts")

                dialog.setPositiveButton("Perfecto") { dialogInterface: DialogInterface, _: Int ->
                    //binding.recorridoEditext.text?.clear()
                    recorridoEditext.setText("")
                    litrosEditext.setText("")
                    recorridoEditext.requestFocus()
                }
                dialog.show()
            } else {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Error en datos")

                dialog.setMessage("1. Debe llenar todos los campos para realizar la operacion.\n\n2. El recorrido y litros deben ser mayor a 0.")

                dialog.setPositiveButton("Entendido") { dialogInterface: DialogInterface, _: Int ->
                    //binding.recorridoEditext.text?.clear()
                    binding.recorridoEditext.requestFocus()
                }
                dialog.show()
            }

        }
    }

    fun calcEficiencia() {
        binding.calcuEficiencia.setOnClickListener {
            var rendObtenido = binding.rendimientoEditext.text.toString().trim()
            var rendEsperado = binding.rendEsperadoEditext.text.toString().trim()

            if (binding.rendimientoEditext.text.toString()
                    .isNotEmpty() && binding.rendEsperadoEditext.text.toString().isNotEmpty() && rendObtenido.toDouble() > 0 && rendEsperado.toDouble() > 0
            ) {
                var calcEfic = ((rendObtenido.toDouble()) / (rendEsperado.toDouble())) * 100
                var df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING

                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Resultado")

                dialog.setMessage("- La Eficiencia calculada fue: ${df.format(calcEfic)} Km/Lts")

                dialog.setPositiveButton("Perfecto") { dialogInterface: DialogInterface, _: Int ->
                    //binding.recorridoEditext.text?.clear()
                    binding.rendimientoEditext.text?.clear()
                    binding.rendEsperadoEditext.text?.clear()
                    recorridoEditext.requestFocus() }
                dialog.show()

            } else
            {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Error en datos")

                dialog.setMessage("1. Debe llenar todos los campos para realizar la operacion.\n\n2. El esperado y obtenido deben ser mayor a 0.")

                dialog.setPositiveButton("Entendido") { dialogInterface: DialogInterface, _: Int ->
                    //binding.recorridoEditext.text?.clear()
                    rendimientoEditext.requestFocus()
                }
                dialog.show()
            }


        }
    }

    fun calcLostGain() {
        binding.calcGananciaPerdida.setOnClickListener {
            var kmRecorridos = binding.KmRecorridoEditext.text.toString().trim()
            var rendimientoEsperado = binding.RendimientoEsEditext.text.toString().trim()
            var volConsumido = binding.LitrosConsumidosEditext.text.toString().trim()
            var precioLts = binding.PrecioEditext.text.toString().trim()

            if (binding.KmRecorridoEditext.text.toString()
                    .isNotEmpty() && binding.RendimientoEsEditext.text.toString()
                    .isNotEmpty() && binding.LitrosConsumidosEditext.text.toString().isNotEmpty() &&
                binding.PrecioEditext.text.toString().isNotEmpty() && kmRecorridos.toDouble() > 0 && rendimientoEsperado.toDouble() > 0 && volConsumido.toDouble() > 0 && precioLts.toDouble() > 0
            )
            {
                var resultado1 = kmRecorridos.toDouble() / rendimientoEsperado.toDouble()
                var resultado2 = resultado1 - volConsumido.toDouble()
                var resultado3 = resultado2 * precioLts.toDouble()

                var df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING

                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Resultado")

                dialog.setMessage("- El calculo ganado o perdido fue de ${df.format(resultado3)} MN")

                dialog.setPositiveButton("Perfecto") { dialogInterface: DialogInterface, _: Int ->
                    //binding.recorridoEditext.text?.clear()
                    binding.KmRecorridoEditext.text?.clear()
                    binding.RendimientoEsEditext.text?.clear()
                    binding.LitrosConsumidosEditext.text?.clear()
                    binding.PrecioEditext.text?.clear()
                    KmRecorridoEditext.requestFocus()
                }
                dialog.show()
            }
            else
            {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Error en datos")

                dialog.setMessage("1. Debe llenar todos los campos para realizar la operacion.\n\n2. Los valores deben ser mayor a 0.")

                dialog.setPositiveButton("Entendido") { dialogInterface: DialogInterface, _: Int ->
                    //binding.recorridoEditext.text?.clear()
                    KmRecorridoEditext.requestFocus()
                }
                dialog.show()
            }



        }
    }

}








