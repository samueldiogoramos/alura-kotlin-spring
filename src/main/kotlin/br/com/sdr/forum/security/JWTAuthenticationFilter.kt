package br.com.sdr.forum.security

import br.com.sdr.forum.config.JWTUtils
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
    private val jwtUtil: JWTUtils
    ) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")

        val tokenDetail = getTokenDetail(token)

        if(jwtUtil.valid(tokenDetail)){
            val authentication = jwtUtil.getAuthentication(tokenDetail)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    private fun getTokenDetail(token: String?) : String? {
        return token.let {
           it?.startsWith("Bearer ")
            it?.substring(7, it.length)
        } ?: null
    }


}
