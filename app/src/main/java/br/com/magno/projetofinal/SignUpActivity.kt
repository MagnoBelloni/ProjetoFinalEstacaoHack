package br.com.magno.projetofinal

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var hex: String = ""

        btnPickColor.setOnClickListener{
            ColorPickerDialogBuilder
                .with(this@SignUpActivity)
                .setTitle("Choose color")
                .initialColor(-50)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener { selectedColor ->
                    hex = Integer.toHexString(selectedColor)
                }
                .setPositiveButton(
                    "ok"
                ) { dialog, selectedColor, allColors -> }
                .setNegativeButton(
                    "cancel"
                ) { dialog, which -> }
                .build()
                .show()
        }

        btnBack.setOnClickListener{
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            finish()
        }

        btnSignUp.setOnClickListener{
            val name = edtName.text.toString().trim()
            val mail = edtMail.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if(name.isEmpty()){
                edtName.error = "Nome é obrigatório"
                edtName.requestFocus()
                return@setOnClickListener
            } else if(mail.isEmpty()){
                edtMail.error = "E-mail é obrigatório"
                edtMail.requestFocus()
                return@setOnClickListener
            } else if(password.isEmpty()){
                edtPassword.error = "Senha é obrigatório"
                edtPassword.requestFocus()
                return@setOnClickListener
            } else if(hex.isEmpty()){
                AlertDialog.Builder(this@SignUpActivity)
                    .setTitle("Atenção")
                    .setMessage("Você precisa selecionar uma cor favorita.")
                    .setPositiveButton("OK") {
                            _, _ ->
                    }
                    .setCancelable(false)
                    .create()
                    .show()
            } else {
                getSharedPreferences("final-$mail", Context.MODE_PRIVATE).edit().apply{
                    putString("name", name)
                    putString("mail", mail)
                    putString("password", password)
                    putString("color", hex)
                }.apply()

                Toast.makeText(this@SignUpActivity, "Usuário criado com sucesso", Toast.LENGTH_LONG).show()

                edtName.text.clear()
                edtMail.text.clear()
                edtPassword.text.clear()
                hex = ""

                startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))

                finishAffinity()
            }
        }

    }
}