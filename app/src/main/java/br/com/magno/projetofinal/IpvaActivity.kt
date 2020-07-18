package br.com.magno.projetofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_imc.*
import kotlinx.android.synthetic.main.activity_imc.btnBack
import kotlinx.android.synthetic.main.activity_imc.btnSend
import kotlinx.android.synthetic.main.activity_ipva.*

class IpvaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ipva)

        btnSend.setOnClickListener {
            val value = edtValue.text.toString().trim()
            val uf = spnUF.selectedItem.toString()

            if (value.isEmpty()) {
                edtValue.error = "É necessário preencher o valor para fazer o cálculo."
                return@setOnClickListener
            } else if (uf.isEmpty()) {
                edtHeight.error = "é necessário selecionar o estado para fazer o cálculo."
                return@setOnClickListener
            } else {
                val valueDouble = value.toDouble()
                val ipvaByUf = when (uf) {
                    "SC" -> 0.02
                    "RS" -> 0.03
                    "PR" -> 0.035
                    "ES" -> 0.02
                    "SP" -> 0.03
                    "RJ" -> 0.04
                    "MG" -> 0.04
                    "SE" -> 0.02
                    "PB" -> 0.02
                    "AL" -> 0.025
                    "PE" -> 0.025
                    "RN" -> 0.025
                    "CE" -> 0.025
                    "PI" -> 0.025
                    "MA" -> 0.025
                    "BH" -> 0.025
                    "AC" -> 0.02
                    "TO" -> 0.02
                    "PA" -> 0.025
                    "AP" -> 0.03
                    "AM" -> 0.03
                    "RR" -> 0.03
                    "RO" -> 0.03
                    "MS" -> 0.025
                    "GO" -> 0.025
                    "MT" -> 0.025
                    "DF" -> 0.03
                    else -> 1.0
                }


                val rating = valueDouble * ipvaByUf

                Toast.makeText(this@IpvaActivity, "Seu IPVA: $rating", Toast.LENGTH_LONG).show()

                edtValue.text.clear()
                spnUF.setSelection(0)

            }
        }

        btnBack.setOnClickListener{
            finish()
        }
    }
}