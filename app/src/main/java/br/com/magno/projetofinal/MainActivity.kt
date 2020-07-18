package br.com.magno.projetofinal

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mailIntent = intent.getStringExtra("mail")

        val sharedPreferences = getSharedPreferences("final-$mailIntent", Context.MODE_PRIVATE)

        val name = sharedPreferences.getString("name", "Chave não encontrada")
        val color = sharedPreferences.getString("color", "Chave não encontrada")



        txvWelcome.setText("Bem vindo(a) $name")

        btnChangeColor.setOnClickListener{
            if (color != null) {
                changeBackgroundColor(color)
            }
        }

        btnBiscuit.setOnClickListener{
            val phrases = arrayOf("Já acabou Jéssica?", "Nunca nem vi", "Sextou", "Todo dia um 7x1 diferente", "Que coisa não?")
            val randomInt = Random.nextInt(0, 4)
            Toast.makeText(this@MainActivity, phrases[randomInt], Toast.LENGTH_LONG).show()
        }

        btnIMC.setOnClickListener{
            startActivity(Intent(this@MainActivity, ImcActivity::class.java))
        }

        btnIPVA.setOnClickListener {
            startActivity(Intent(this@MainActivity, IpvaActivity::class.java))
        }

        btnLogout.setOnClickListener{
            AlertDialog.Builder(this@MainActivity)
                .setTitle("Atenção")
                .setMessage("Realmente deseja sair?")
                .setPositiveButton(HtmlCompat.fromHtml("<font color='green'>Sim</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)) { _, _ ->
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java ))
                    finish()
                }
                .setNegativeButton(HtmlCompat.fromHtml("<font color='red'>Não</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)) { _, _ -> }
                .create()
                .show()
        }
    }

    private fun changeBackgroundColor(color: String){
        ctlBackground.setBackgroundColor(Color.parseColor("#$color"))
    }
}