package br.com.magno.projetofinal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener{
            val mail = edtMail.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if(mail.isEmpty()){
                edtMail.error = "Campo obrigatório"
                edtMail.requestFocus()
                return@setOnClickListener
            } else if(password.isEmpty()){
                edtPassword.error = "Campo obrigatório"
                edtPassword.requestFocus()
                return@setOnClickListener
            }

            val sharedPreferences = getSharedPreferences("final-$mail", Context.MODE_PRIVATE)

            if(sharedPreferences == null){
                Toast.makeText(this@LoginActivity, "E-mail ou senha incorretos", Toast.LENGTH_SHORT).show()
            }

            val mailSharedPreferences = sharedPreferences.getString("mail", "Chave não encontrada")
            val passwordSharedPreferences = sharedPreferences.getString("password", "Chave não encontrada")

            if(mail == mailSharedPreferences && password == passwordSharedPreferences){
                Toast.makeText(this@LoginActivity, "Usuário logado com sucesso!", Toast.LENGTH_LONG).show()
                startActivity(Intent(this@LoginActivity, MainActivity::class.java).apply{
                    putExtra("mail", mail)
                })
                finish()
            } else {
                Toast.makeText(this@LoginActivity, "E-mail ou senha incorretos", Toast.LENGTH_LONG).show()
            }
        }

        btnSignup.setOnClickListener{
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
    }
}