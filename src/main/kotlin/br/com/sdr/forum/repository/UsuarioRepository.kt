package br.com.sdr.forum.repository

import br.com.sdr.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, Long>{
    fun findByEmail(username: String?) : Usuario
}