//Mariana e Paola

package br.com.etechas.tarefas.services;

import br.com.etechas.tarefas.dto.TarefaResponseDTO;
import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.enums.StatusEnum;
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

    public void excluirPorId(Long id){
       Tarefa tarefa = tarefaRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Não há tarefa com id " + id));

       if(tarefa.getStatus() == StatusEnum.PENDING){
           tarefaRepository.delete(tarefa);
       }else{
           throw new RuntimeException("Tarefa em progresso ou concluída");
       }
    }
}
