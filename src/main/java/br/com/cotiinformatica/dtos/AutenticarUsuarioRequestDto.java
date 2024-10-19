package br.com.cotiinformatica.dtos;

import lombok.Data;

@Data
public class AutenticarUsuarioRequestDto {
	
	private String email;
	private String senha;
}