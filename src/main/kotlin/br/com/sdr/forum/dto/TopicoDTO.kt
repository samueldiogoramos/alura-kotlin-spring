package br.com.sdr.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TopicoDTO (
    @field:NotEmpty
    @field:Size(min = 1, max = 10)
    val titulo: String,

    @field:NotEmpty
    val mensagem: String,

    @field:NotNull
    val cursoId: Long,

    @field:NotNull
    val autorId: Long,
        )