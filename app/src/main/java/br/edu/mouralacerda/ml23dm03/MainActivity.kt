package br.edu.mouralacerda.ml23dm03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    var listaDaTela: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaDaTela = findViewById(R.id.lstNomes)

        val botaoNovoNome = findViewById<FloatingActionButton>(R.id.btnNovoNome)

        botaoNovoNome.setOnClickListener {
            val i = Intent(this, CadastroNomes::class.java)
            startActivity(i)
        }

        listaDaTela!!.setOnItemLongClickListener { adapterView, view, i, l ->

            val alerta = AlertDialog.Builder(this)

            alerta
                .setTitle("Apagar Nome")
                .setMessage("Deseja realmente apagr este nome da lista?")
                .setPositiveButton("Sim") { dialog, which ->
                    val p = adapterView.adapter.getItem(i) as Pessoa
                    Database.getInstance(this)!!.pessoaDAO().apagar(p)
                    atualizarLista("id")
                    Toast.makeText(this, "Nome apagado", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Não") { dialog, which ->
                    Toast.makeText(this, "Nome não apagado", Toast.LENGTH_SHORT).show()
                }
                .show()

            true
        }
    }

    override fun onResume() {
        super.onResume()

        atualizarLista("id")

    }

    fun atualizarLista(ordem: String) {

        var lista: List<Pessoa>? = null

        if(ordem.equals("id")) {
            lista = Database.getInstance(this)!!.pessoaDAO().listarPorId()
        } else if(ordem.equals("nome")) {
            lista = Database.getInstance(this)!!.pessoaDAO().listarPorNome()
        }

        val listaAdaptada = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, lista!!)

        listaDaTela!!.adapter = listaAdaptada

//        findViewById<ListView>(R.id.lstNomes).adapter =
//            ArrayAdapter(this, android.R.layout.simple_list_item_1,
//                Database.getInstance(this)!!.pessoaDAO().listar())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_principal, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.ordemNome) {
            atualizarLista("nome")
        } else if(item.itemId == R.id.ordemId) {
            atualizarLista("id")
        }

        return super.onOptionsItemSelected(item)
    }

}