package br.com.magno.projetofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_imc.*

class ImcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        btnSend.setOnClickListener{
            val weight = edtWeight.text.toString().trim()
            val height = edtHeight.text.toString().trim()

            if(weight.isEmpty()){
                edtWeight.error = "É necessário preencher o peso para fazer o cálculo."
                return@setOnClickListener
            } else if(height.isEmpty()){
                edtHeight.error = "é necesseário preencher a altura para fazer o cálculo."
                return@setOnClickListener
            } else {
                val weightDouble = weight.toDouble()
                val heightDouble = height.toDouble()

                val imc = weightDouble / (heightDouble * heightDouble)

                val rating = when(imc){
                    in 0.0..15.9 -> "Magreza grau III"
                    in 16.0..16.9 -> "Magreza grau II"
                    in 17.0..18.4 -> "Magreza grau I"
                    in 18.5..24.9 ->  "Adequado"
                    in 25.0..29.9 -> "Pré-obeso"
                    in 30.0..34.9 -> "Obesidade I"
                    in 35.0..39.9-> "Obesidade II"
                    in 40.0..Double.MAX_VALUE -> "Obesidade III"
                    else -> "IMC Inválido"
                }

                Toast.makeText(this@ImcActivity, rating, Toast.LENGTH_LONG).show()

                edtHeight.text.clear()
                edtWeight.text.clear()
            }

            btnBack.setOnClickListener{
                finish()
            }

        }
    }
}