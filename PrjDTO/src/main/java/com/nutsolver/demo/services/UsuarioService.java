package com.nutsolver.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutsolver.demo.entities.Usuario;
import com.nutsolver.demo.entities.UsuarioRequestDto;
import com.nutsolver.demo.entities.UsuarioResponseDto;
import com.nutsolver.demo.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public UsuarioResponseDto salvarUsuario(UsuarioRequestDto dto) {

		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(dto.getSenha());

		Usuario salvo = usuarioRepository.save(usuario);

		return new UsuarioResponseDto(salvo.getIdUsuario(), salvo.getNome(), salvo.getEmail());
	}

	public void deletarUsuario(Long idUsuario) {
		usuarioRepository.deleteById(idUsuario);
	}

	public UsuarioResponseDto getUsuarioById(Long idUsuario) {

		Usuario usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		return new UsuarioResponseDto(usuario.getIdUsuario(), usuario.getNome(), usuario.getEmail());
	}

	public List<UsuarioResponseDto> listAllUsuarios() {
		return usuarioRepository.findAll().stream()
				.map(Usuario -> new UsuarioResponseDto(Usuario.getIdUsuario(), Usuario.getNome(), Usuario.getEmail()))
				.collect(Collectors.toList());
	}

}
