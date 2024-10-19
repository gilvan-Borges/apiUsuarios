package br.com.cotiinformatica.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.AutenticarUsuarioRequestDto;
import br.com.cotiinformatica.dtos.CriarUsuarioRequestDto;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
	
	@PostMapping("criar")
	public void criar(@RequestBody CriarUsuarioRequestDto dto) {
		//TODO
	}
	
	@PostMapping("autenticar")
	public void autenticar(@RequestBody AutenticarUsuarioRequestDto dto) {
		//TODO
	}
}
