package br.com.etechas.tarefas.services;

import br.com.etechas.tarefas.dto.TarefaResponseDTO;
import br.com.etechas.tarefas.mapper.TarefaMapper;
import br.com.etechas.tarefas.repositorys.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
   private TarefaMapper tarefaMapper;

    public List<TarefaResponseDTO> listarTarefas(){
        return tarefaMapper.toResponseDTOList(tarefaRepository.findAll());
    }
}
