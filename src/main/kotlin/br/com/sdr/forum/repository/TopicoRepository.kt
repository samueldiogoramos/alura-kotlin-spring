package br.com.sdr.forum.repository

import br.com.sdr.forum.dto.RelatorioQuantidadePorTopicosDTO
import br.com.sdr.forum.model.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicoRepository:JpaRepository<Topico, Long> {
    fun findByCursoNome(
        nomeCurso: String,
        paginacao: Pageable
    ): Page<Topico>

    @Query("select new br.com.sdr.forum.dto.RelatorioQuantidadePorTopicosDTO(topico.titulo, count(topico)) from Topico topico group by topico.titulo")
    fun gerarRelatorioPorQuantidade(): List<RelatorioQuantidadePorTopicosDTO>

}