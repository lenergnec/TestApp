package com.example.testapp

import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.example.testapp.databinding.ActivityDescuentosBinding
import com.example.testapp.databinding.ActivityImpuestosSlvBinding
import kotlinx.android.synthetic.main.activity_impuestos_slv.*
import java.math.RoundingMode
import java.text.DecimalFormat

class ImpuestosSlv : AppCompatActivity() {
    private lateinit var binding: ActivityImpuestosSlvBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImpuestosSlvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Para cambiar el color de la barra de acciones o actionbar
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.NectarBlue)))

        //Cambiar texto en el action bar
        this.title = "Impuestos del R.Dinamico"

        impuestos()


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

    fun impuestos() {
        binding.calcTaxes.setOnClickListener {
            var galones = binding.galonesText.text.toString().trim()
            var precioGls = binding.precioGalonText.text.toString().trim()
            val fovial: Double = 0.20
            val cotrans: Double = 0.10


            if (binding.galonesText.text.toString()
                    .isNotEmpty() && binding.precioGalonText.text.toString()
                    .isNotEmpty() && galones.toDouble() > 0 && precioGls.toDouble() > 0
            ) {

                var sinCotraNiFovial = precioGls.toDouble() - fovial - cotrans
                var precioSinIva = sinCotraNiFovial / 1.13
                var compraSinImpuestos = precioSinIva * galones.toDouble()
                var impIva = ((precioSinIva * galones.toDouble()) * 13) / 100
                var impFovial = galones.toDouble() * fovial
                var impCotran = galones.toDouble() * cotrans
                var totalCompra = (compraSinImpuestos + impIva) + (impFovial + impCotran)
                var totalImpuestos = ((precioSinIva * galones.toDouble()) * 13) /100
                var otrosImpuestos = impFovial + impCotran

                //Formatear los decimales
                var df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING

                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Resultado")

                dialog.setMessage(
                    "----------------------------------------------------------\n" +
                            "\n- Total de la compra: $${df.format(totalCompra)}\n\n- Impuestos de Venta: $${df.format(totalImpuestos)}\n\n- Otros Impuestos: $${df.format(otrosImpuestos)}\n\n----------------------------------------------------------\n\n Otros Datos:\n\n" +
                            "1. Precion Sin Cot+Fv: $${df.format(sinCotraNiFovial)}\n\n2. Precio sin IVA: $${df.format(precioSinIva)}\n\n3. Compra sin impuestos: $${df.format(compraSinImpuestos)}\n\n" +
                            "4. Calculo de IVA: $${df.format(impIva)}\n\n5. Calculo de Fovial: $${df.format(impFovial)}\n\n6. Calculo de Cotrans: $${df.format(impCotran)}\n\n----------------------------------------------------------"
                )

                dialog.setPositiveButton("Perfecto") { dialogInterface: DialogInterface, _: Int ->
                    binding.galonesText.text?.clear()
                    binding.precioGalonText.text?.clear()
                    binding.galonesText.requestFocus()
                }
                dialog.show()

            } else {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Error en datos")

                dialog.setMessage(
                    "1. Debe llenar todos los campos para realizar la operacion.\n\n2. El volumen y el precio debe ser mayor 0."

                )

                dialog.setPositiveButton("Entendido") { dialogInterface: DialogInterface, _: Int ->
                    //binding.recorridoEditext.text?.clear()
                    binding.galonesText.requestFocus()
                }
                dialog.show()
            }

        }

    }
}