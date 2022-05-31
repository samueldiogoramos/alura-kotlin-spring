package br.com.sdr.forum.service

import br.com.sdr.forum.dto.RelatorioQuantidadePorTopicosDTO
import br.com.sdr.forum.dto.TopicoDTO
import br.com.sdr.forum.dto.TopicoUpdatedDTO
import br.com.sdr.forum.dto.TopicoView
import br.com.sdr.forum.exception.NotFoundException
import br.com.sdr.forum.mapper.TopicoMapper
import br.com.sdr.forum.mapper.TopicoViewMapper
import br.com.sdr.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(
    val topicoRepository: TopicoRepository,
    val topicoViewMapper: TopicoViewMapper,
    val topicoMapper: TopicoMapper
){
    val RECURSO_NOT_FOUND: String? = "Recurso não encontrado para a operação requisitada"

    fun listar(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoView> {
        val topicos = if(nomeCurso == null) {
            topicoRepository.findAll(paginacao)
        }else{
            topicoRepository.findByCursoNome(nomeCurso, paginacao)
        }

        return topicos.map {
                it -> topicoViewMapper.map(it)
            }
    }

    fun buscarPorId( id: Long): TopicoView {
        return topicoRepository.findById(id)
            .map { it ->
            topicoViewMapper.map(it)
        }
            .orElseThrow{
                NotFoundException(RECURSO_NOT_FOUND)
            }
    }

    fun novo(topicDTO: TopicoDTO): TopicoView {
        val topicoNovo = topicoMapper.map(topicDTO)

        topicoRepository.save(topicoNovo)

        return topicoViewMapper.map(topicoNovo)
    }

    fun atualizar(topico: TopicoUpdatedDTO): TopicoView {
         val topicoUpdated = topicoRepository.findById(topico.id)
            .apply {
                this.get().mensagem = topico.mensagem
                this.get().titulo = topico.titulo
            }.orElseThrow{
                 NotFoundException(RECURSO_NOT_FOUND)
             }

        return topicoViewMapper.map(topicoUpdated)
    }

    fun remover(id: Long) {
        topicoRepository.deleteById(id)
    }

    fun gerarRelatorioPorQuantidade(): List<RelatorioQuantidadePorTopicosDTO> {
        return topicoRepository.gerarRelatorioPorQuantidade()
    }
}