package com.example.testapp

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.example.testapp.databinding.ActivityDescuentosBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_descuentos.*
import kotlinx.android.synthetic.main.activity_rendimiento.*
import java.math.RoundingMode
import java.text.DecimalFormat

class Descuentos : AppCompatActivity() {
    private lateinit var binding: ActivityDescuentosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescuentosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Para cambiar el color de la barra de acciones o actionbar
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.NectarBlue)))

        //Cambiar texto en el action bar
        this.title = "Descuentos"
        dPorcentual()
        dAbsoluto()
        dFijo()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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
            R.id.rendEfic -> {
                startActivity(Intent(this, Rendimiento::class.java))
                finish()
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

    fun dPorcentual() {
        binding.calcPorcentual.setOnClickListener {
            var monto = binding.porcentualMontotxt.text.toString().trim()
            var valorD = binding.porcentualValortxt.text.toString().trim()


            if (binding.porcentualMontotxt.text.toString()
                    .isNotEmpty() && binding.porcentualValortxt.text.toString()
                    .isNotEmpty() && valorD.toDouble() <= 100 && valorD.toDouble() > 0 && monto.toDouble() > 0
            ) {

                var descuento = (monto.toDouble()) * (valorD.toDouble()) / 100
                var montoFinal = (monto.toDouble()) - (descuento)

                //Formatear los decimales
                var df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING

                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Resultado")

                dialog.setMessage(
                    "- El descuento aplicado es: $${df.format(descuento)} \n\n- El monto a pagar es: $${
                        df.format(
                            montoFinal
                        )
                    }"
                )

                dialog.setPositiveButton("Perfecto") { dialogInterface: DialogInterface, _: Int ->
                    binding.porcentualMontotxt.text?.clear()
                    binding.porcentualValortxt.text?.clear()
                    binding.porcentualMontotxt.requestFocus()
                }
                dialog.show()

            } else {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Error en datos")

                dialog.setMessage(
                    "1. Debe llenar todos los campos para realizar la operacion.\n\n2. El descuento no debe ser mayor a $100 ni igual a $0. " +
                            "\n\n3. El monto debe ser mayor a $0."
                )

                dialog.setPositiveButton("Entendido") { dialogInterface: DialogInterface, _: Int ->
                    //binding.recorridoEditext.text?.clear()
                    binding.porcentualMontotxt.requestFocus()
                }
                dialog.show()
            }

        }

    }

    fun dAbsoluto() {
        binding.calcAbsoluto.setOnClickListener {
            var volumen = binding.absolutoVolumenText.text.toString().trim()
            var valorD = binding.absolutoValortext.text.toString().trim()
            var precio = binding.absolutoPreciotxt.text.toString().trim()

            if (binding.absolutoVolumenText.text.toString()
                    .isNotEmpty() && binding.absolutoValortext.text.toString()
                    .isNotEmpty() && binding.absolutoPreciotxt.text.toString()
                    .isNotEmpty() && valorD.toDouble() <= 100 && valorD.toDouble() > 0 && volumen.toDouble() > 0 && precio.toDouble() > 0
            ) {

                var subTotal = (volumen.toDouble() * precio.toDouble())
                var montoFinal = (volumen.toDouble()) * (valorD.toDouble())
                var descuento = (subTotal - montoFinal)

                var df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING

                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Resultado")

                dialog.setMessage(
                    "- El monto sin descuento es: $${df.format(subTotal)} \n\n- El monto con descuento a pagar es: $${
                        df.format(
                            montoFinal
                        )
                    }. \n\n- El descuento aplicado fue de: $${df.format(descuento)}"
                )

                dialog.setPositiveButton("Perfecto") { dialogInterface: DialogInterface, _: Int ->
                    binding.absolutoVolumenText.text?.clear()
                    binding.absolutoValortext.text?.clear()
                    binding.absolutoPreciotxt.text?.clear()
                    binding.absolutoVolumenText.requestFocus()
                }
                dialog.show()
            } else {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Error en datos")

                dialog.setMessage(
                    "1. Debe llenar todos los campos para realizar la operacion.\n\n2. El descuento no debe ser mayor a $100 ni igual a $0." +
                            "\n\n3. El precio debe ser mayor a $0.\n\n4.El volumen debe ser mayor a 0"
                )

                dialog.setPositiveButton("Entendido") { dialogInterface: DialogInterface, _: Int ->
                    //binding.recorridoEditext.text?.clear()
                    binding.absolutoVolumenText.requestFocus()
                }
                dialog.show()
            }

        }

    }

    fun dFijo() {
        binding.calcFijo.setOnClickListener {
            var volumen = binding.fijolitrostext.text.toString().trim()
            var valorD = binding.fijopreciotext.text.toString().trim()
            var precio = binding.fijovalortext.text.toString().trim()

            if (binding.fijolitrostext.text.toString()
                    .isNotEmpty() && binding.fijopreciotext.text.toString()
                    .isNotEmpty() && binding.fijovalortext.text.toString()
                    .isNotEmpty() && valorD.toDouble() <= 100 && valorD.toDouble() > 0 && volumen.toDouble() > 0 && precio.toDouble() > 0 && valorD.toDouble() < precio.toDouble()
            ) {

                var subTotal = (volumen.toDouble() * precio.toDouble())
                var precioDescuento = (precio.toDouble()) - (valorD.toDouble())
                var montoFinal = volumen.toDouble() * precioDescuento
                var descuento = (subTotal - montoFinal)

                var df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING

                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Resultado")

                dialog.setMessage(
                    "- El monto sin descuento es: $${df.format(subTotal)} \n\n- El monto con descuento a pagar es: $${
                        df.format(
                            montoFinal
                        )
                    } \n\n- El descuento aplicado fue de: $${df.format(descuento)}"
                )

                dialog.setPositiveButton("Perfecto") { dialogInterface: DialogInterface, _: Int ->
                    binding.fijolitrostext.text?.clear()
                    binding.fijopreciotext.text?.clear()
                    binding.fijovalortext.text?.clear()
                    binding.fijolitrostext.requestFocus()
                }
                dialog.show()
            } else {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Error en datos")

                dialog.setMessage(
                    "1. Debe llenar todos los campos para realizar la operacion.\n\n2. El descuento no debe ser mayor a $100 ni igual a $0." +
                            "\n\n3. El precio debe ser mayor a $0 y mayor al descuento.\n\n4. El volumen debe ser mayor a 0"
                )

                dialog.setPositiveButton("Entendido") { dialogInterface: DialogInterface, _: Int ->
                    //binding.recorridoEditext.text?.clear()
                    binding.fijolitrostext.requestFocus()
                }
                dialog.show()
            }

        }

    }


}
