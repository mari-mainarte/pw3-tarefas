package br.com.etechas.tarefas.services;

import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.repositorys.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTarefas(){
        return tarefaRepository.findAll();
    }
}
