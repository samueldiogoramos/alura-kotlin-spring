package br.com.sdr.forum.model

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Role (
    @Id
    @GeneratedValue
    val id: Long,

    val nome: String
        ) : GrantedAuthority {
    override fun getAuthority() = nome
}