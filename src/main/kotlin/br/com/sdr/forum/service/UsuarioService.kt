package br.com.sdr.forum.service

import br.com.sdr.forum.model.Usuario
import br.com.sdr.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioService (
    private val usuarioRepository: UsuarioRepository
    ) : UserDetailsService{
    fun buscarPorId(id: Long) : Usuario {
        return usuarioRepository.getById(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = usuarioRepository.findByEmail(username) ?: throw RuntimeException()

        return UserDetail(usuario)
    }

}