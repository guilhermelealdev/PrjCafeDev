package com.nutsolver.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutsolver.demo.entities.UsuarioRequestDto;
import com.nutsolver.demo.entities.UsuarioResponseDto;
import com.nutsolver.demo.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping("/salvar")
	public UsuarioResponseDto SaveUsuario(@RequestBody UsuarioRequestDto dto) {
		return usuarioService.salvarUsuario(dto);
	}
	
	@DeleteMapping("/deletar/{idUsuario}")
	public void DeleteUsuario(@PathVariable Long idUsuario) {
		usuarioService.deletarUsuario(idUsuario);
	}
	
	@GetMapping("/{idUsuario}")
	public UsuarioResponseDto GetUsuario(@PathVariable Long idUsuario) {
		return usuarioService.getUsuarioById(idUsuario);
	}
	
	@GetMapping
	public List<UsuarioResponseDto> ListAllUsuarios(){
		return usuarioService.listAllUsuarios();
	}
	
	
	
	
	
	
}
