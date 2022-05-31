package br.com.sdr.forum.dto

import br.com.sdr.forum.model.StatusTopico
import java.time.LocalDateTime

data class TopicoView (
    val id: Long? = null,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
        )