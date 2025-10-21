package br.com.etechas.tarefas.services;

import br.com.etechas.tarefas.dto.UsuarioCadastroDTO;
import br.com.etechas.tarefas.dto.UsuarioResponseDTO;
import br.com.etechas.tarefas.entity.Usuario;
import br.com.etechas.tarefas.mapper.UsuarioMapper;
import br.com.etechas.tarefas.repositorys.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO registrar(UsuarioCadastroDTO cadastro){

        Optional<Usuario> usuarioName =  usuarioRepository.findByUsername(cadastro.username());

        if(usuarioName.isPresent()){
            throw new RuntimeException("Usuário com mesmo username já existe!");
        }

        var senhaCifrada = passwordEncoder.encode(cadastro.password());
        var entidade = usuarioMapper.toEntity(cadastro);

        entidade.setPassword(senhaCifrada);

        usuarioRepository.save(entidade);

        UsuarioResponseDTO usuarioResposeDTO = usuarioMapper.toUsuarioResponseDTO(entidade);

        /*
        Usuario usuario = usuarioMapper.toEntity(cadastro);
        usuarioRepository.save(usuario);
        UsuarioResponseDTO usuarioResposeDTO = usuarioMapper.toUsuarioResponseDTO(usuario);
        */

        return usuarioResposeDTO;
    }

    public List<UsuarioResponseDTO> getAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        List<UsuarioResponseDTO> usuariosResponseDTO = usuarioMapper.toUsuarioResponseDTOList(usuarios);

        return usuariosResponseDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException((
                "Usuário" + username + " não encontrado")));
    }
}
