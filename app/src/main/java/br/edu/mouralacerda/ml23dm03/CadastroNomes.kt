package br.edu.mouralacerda.ml23dm03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CadastroNomes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_nomes)

        val botaoSalvar = findViewById<Button>(R.id.btnSalvar)
        val texto = findViewById<EditText>(R.id.edtNome)

        botaoSalvar.setOnClickListener {

            val p = Pessoa(texto.text.toString())

            Database.getInstance(this)!!.pessoaDAO().salvar(p)

            finish()
            
        }

    }
}