package br.com.sdr.forum.dto

import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class TopicoUpdatedDTO (
    @Min(value = Long.MIN_VALUE)
    var id: Long,

    @field:Size(min = 1, max = 15)
    val titulo: String,

    @field:NotEmpty
    val mensagem: String,
)