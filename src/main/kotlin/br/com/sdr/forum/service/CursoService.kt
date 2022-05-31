package br.com.sdr.forum.service

import br.com.sdr.forum.model.Curso
import br.com.sdr.forum.repository.CursoRepository
import org.springframework.stereotype.Service

@Service
class CursoService(
    private val cursoRepository: CursoRepository
) {
    fun buscarPorId(id: Long) : Curso {
        return cursoRepository.getById(id)
    }
}