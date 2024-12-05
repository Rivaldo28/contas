package com.sistema.contas.endereco.domain.usecases;

import com.grandle.endereco.dtos.EnderecoDTO;
import com.sistema.contas.endereco.application.ports.repository.EnderecoRepository;
import com.sistema.contas.endereco.domain.entities.Endereco;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EnderecoUseCase {

    private final EnderecoRepository enderecoRepository;

    // Salva um novo endereço
    public EnderecoDTO salvarEndereco(EnderecoDTO enderecoDTO) {
        // Converte EnderecoDTO para Endereco
        Endereco endereco = new Endereco();
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());

        // Salva a entidade Endereco no repositório
        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        // Converte Endereco salvo para EnderecoDTO
        return new EnderecoDTO(
                Math.toIntExact(enderecoSalvo.getId()),
                enderecoSalvo.getLogradouro(),
                enderecoSalvo.getRua(),
                enderecoSalvo.getNumero(),
                enderecoSalvo.getComplemento(),
                enderecoSalvo.getBairro(),
                enderecoSalvo.getCep(),
                enderecoSalvo.getCidade(),
                enderecoSalvo.getEstado()
        );
    }

    // Atualiza um endereço existente
    public EnderecoDTO atualizarEndereco(Long id, EnderecoDTO enderecoDTO) {
        if (!enderecoRepository.existsById(id)) {
            return null; // Pode lançar uma exceção customizada, se necessário
        }

        // Converte EnderecoDTO para Endereco
        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setLogradouro(enderecoDTO.getLogradouro());
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setCidade(enderecoDTO.getCidade());
        endereco.setEstado(enderecoDTO.getEstado());

        // Atualiza a entidade Endereco no repositório
        Endereco enderecoAtualizado = enderecoRepository.save(endereco);

        // Converte Endereco atualizado para EnderecoDTO
        return new EnderecoDTO(
                Math.toIntExact(enderecoAtualizado.getId()),
                enderecoAtualizado.getLogradouro(),
                enderecoAtualizado.getRua(),
                enderecoAtualizado.getNumero(),
                enderecoAtualizado.getComplemento(),
                enderecoAtualizado.getBairro(),
                enderecoAtualizado.getCep(),
                enderecoAtualizado.getCidade(),
                enderecoAtualizado.getEstado()
        );
    }

    // Lista todos os endereços
    public List<EnderecoDTO> listarEnderecos(String filtro) {
        List<Endereco> enderecos;

        if (filtro == null || filtro.isEmpty()) {
            enderecos = enderecoRepository.findAll();
        } else {
            enderecos = enderecoRepository.listar(filtro);
        }

        // Converte lista de Endereco para lista de EnderecoDTO
        return enderecos.stream()
                .map(endereco -> new EnderecoDTO(
                        Math.toIntExact(endereco.getId()),
                        endereco.getLogradouro(),
                        endereco.getRua(),
                        endereco.getNumero(),
                        endereco.getComplemento(),
                        endereco.getBairro(),
                        endereco.getCep(),
                        endereco.getCidade(),
                        endereco.getEstado()
                ))
                .collect(Collectors.toList());
    }

    // Remove um endereço pelo ID
    public void removerEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

    // Obter um endereço pelo ID
    public EnderecoDTO obterEndereco(Long id) {
        Endereco endereco = enderecoRepository.findById(id).orElse(null);
        if (endereco == null) {
            return null;
        }

        // Converte Endereco para EnderecoDTO
        return new EnderecoDTO(
                Math.toIntExact(endereco.getId()),
                endereco.getLogradouro(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getEstado()
        );
    }
}
