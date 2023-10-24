package br.edu.mouralacerda.ml23dm03

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PessoaDAO {

    @Insert
    fun salvar(obj: Pessoa)

    @Delete
    fun apagar(obj: Pessoa)

    @Query("SELECT * FROM Pessoa")
    fun listar(): List<Pessoa>

    @Query("SELECT * FROM Pessoa ORDER BY nome")
    fun listarPorNome(): List<Pessoa>

    @Query("SELECT * FROM Pessoa ORDER BY id")
    fun listarPorId(): List<Pessoa>

}