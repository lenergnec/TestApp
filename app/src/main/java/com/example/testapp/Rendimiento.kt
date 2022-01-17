package com.example.testapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.testapp.databinding.ActivityRendimientoBinding
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_rendimiento.*

class Rendimiento : AppCompatActivity() {
    private lateinit var binding: ActivityRendimientoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRendimientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*recorrido = this.findViewById(R.id.recorridoEditext)
        litros = this.findViewById(R.id.litrosEditext)*/
        calcRendimiento()
        calcEficiencia()
    }
    fun calcRendimiento(){
        binding.calcuRendimiento.setOnClickListener {
            var recorrido =  binding.recorridoEditext.text.toString().trim()
            var litros =  binding.litrosEditext.text.toString().trim()


            if(binding.recorridoEditext.text.toString().isNotEmpty() || binding.litrosEditext.text.toString().isNotEmpty())
            {
                var calcRend = (recorrido.toFloat()) * (litros.toFloat())

                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Resultado")

                dialog.setMessage("El rendimiento calculado fue $calcRend Km/Lts")

                dialog.setPositiveButton("Perfecto") { dialogInterface: DialogInterface, _: Int ->
                    //binding.recorridoEditext.text?.clear()
                    recorridoEditext.setText("")
                    litrosEditext.setText("")
                    recorridoEditext.requestFocus()
                }
                dialog.show()
            }
            else
            {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Error en datos")

                dialog.setMessage("Debe llenar todos los campos para realizar la operacion")

                dialog.setPositiveButton("Entendido") { dialogInterface: DialogInterface, _: Int ->
                    //binding.recorridoEditext.text?.clear()
                    recorridoEditext.requestFocus()
                }
                dialog.show()
            }

        }
    }
    fun calcEficiencia(){
        binding.calcuEficiencia.setOnClickListener{
            var rendObtenido = binding.rendimientoEditext.text.toString().trim()
            var rendEsperado = binding.rendEsperadoEditext.text.toString().trim()

            if(binding.rendimientoEditext.text.toString().isNotEmpty() || binding.rendEsperadoEditext.text.toString().isNotEmpty())
            {
                var calcEfic = ((rendObtenido.toFloat()) / (rendEsperado.toFloat())) * 100

                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Resultado")

                dialog.setMessage("La Eficiencia calculada fue $calcEfic Km/Lts")

                dialog.setPositiveButton("Perfecto") { dialogInterface: DialogInterface, _: Int ->
                    //binding.recorridoEditext.text?.clear()
                    binding.rendimientoEditext.text?.clear()
                    binding.rendEsperadoEditext.text?.clear()
                    recorridoEditext.requestFocus()
                }
                dialog.show()
            }
            else
            {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Error en datos")

                dialog.setMessage("Debe llenar todos los campos para realizar la operacion")

                dialog.setPositiveButton("Entendido") { dialogInterface: DialogInterface, _: Int ->
                    //binding.recorridoEditext.text?.clear()
                    rendimientoEditext.requestFocus()
                }
                dialog.show()
            }


        }
    }



    /*fun calcRend(){
        var kmrecorrido: String = recorrido.text.toString()
        var litrosconsumidos: String = litros.text.toString()
        var calcRendimiento = (kmrecorrido.toFloat() * litrosconsumidos.toFloat())

        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Resultado")
        dialog.setMessage("El rendimiento calculado fue  $calcRendimiento Km/Lts")
        dialog.setPositiveButton("Perfecto") { dialogInterface: DialogInterface, _: Int ->
            //Hacer una limpia al presionar el boton ok
        }
        dialog.show()

    }*/

}

