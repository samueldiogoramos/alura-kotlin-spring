package br.com.sdr.forum.config

import br.com.sdr.forum.service.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.*

@Component
class JWTUtils (
    private val usuarioService: UsuarioService
        ){
    @Value("\${jwt.secret}")
    private lateinit var secret: String

    private val expiration: Long = 120000

    fun valid(tokenDetail: String?): Boolean {
        return try {
            Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(tokenDetail)
                .body
            true
        }catch (e: IllegalArgumentException){
            false
        }
    }

    fun getAuthentication(tokenDetail: String?) : Authentication {
        val username = Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(tokenDetail)
            .body
            .subject
        val authorities = usuarioService.loadUserByUsername(username)?.authorities

        return UsernamePasswordAuthenticationToken(username, null, authorities)
    }

    fun genereteToken(username: String?, authorities: Collection<GrantedAuthority>): String {
        return Jwts.builder()
            .setSubject(username)
            .claim("role", authorities)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS256, secret)
            .compact()
    }
}