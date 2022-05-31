package br.com.sdr.forum.mapper

import br.com.sdr.forum.`interface`.Mapper
import br.com.sdr.forum.dto.TopicoDTO
import br.com.sdr.forum.model.Topico
import br.com.sdr.forum.service.CursoService
import br.com.sdr.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService,
):Mapper<TopicoDTO, Topico> {
    override fun map(topicoDTO: TopicoDTO): Topico {
        return Topico(
            titulo = topicoDTO.titulo,
            mensagem = topicoDTO.mensagem,
            curso = cursoService.buscarPorId(topicoDTO.cursoId),
            autor = usuarioService.buscarPorId(topicoDTO.autorId)
        )
    }
}