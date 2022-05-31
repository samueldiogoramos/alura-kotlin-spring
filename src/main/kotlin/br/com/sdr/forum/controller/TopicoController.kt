package br.com.sdr.forum.controller

import br.com.sdr.forum.dto.RelatorioQuantidadePorTopicosDTO
import br.com.sdr.forum.dto.TopicoDTO
import br.com.sdr.forum.dto.TopicoUpdatedDTO
import br.com.sdr.forum.dto.TopicoView
import br.com.sdr.forum.service.TopicoService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

@RestController
@RequestMapping("/api/v1/topicos")
class TopicoController (
    private val topicoService: TopicoService
        ){

    @GetMapping
    @Cacheable("topicos")
    fun listas(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(size = 5, sort = ["dataCriacao"], direction = Sort.Direction.DESC) paginacao: Pageable
    ):Page<TopicoView> {
       return topicoService.listar(nomeCurso, paginacao)
    }

    @GetMapping("/{id}")
    @Transactional
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return topicoService.buscarPorId(id)
    }

    @GetMapping("/relatorioPorQuantidade")
    fun gerarRelatorioPorQuantidade(): List<RelatorioQuantidadePorTopicosDTO>{
        return topicoService.gerarRelatorioPorQuantidade()
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun novo(@RequestBody @Valid topico: TopicoDTO, uriBuilder : UriComponentsBuilder) : ResponseEntity<TopicoView> {
        val topicoCreated = topicoService.novo(topico)
        val uri = uriBuilder.path("/topicos/${topicoCreated.id}").build().toUri()

        return ResponseEntity.created(uri).body(topicoCreated)
    }

    @PutMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun atualizar(@RequestBody @Valid topico: TopicoUpdatedDTO) : ResponseEntity<TopicoView>{
        val topicoUpdated = topicoService.atualizar(topico)

        return ResponseEntity.ok(topicoUpdated)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun remover(@PathVariable @NotEmpty id:Long) {
        topicoService.remover(id)
    }
}