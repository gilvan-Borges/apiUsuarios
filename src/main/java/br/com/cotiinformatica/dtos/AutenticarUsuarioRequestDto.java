package br.com.cotiinformatica.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AutenticarUsuarioRequestDto {
	
	@Email(message = "Por favor, informe o endereço de email válido.")
	@NotEmpty(message = "Por favor, informe o email do usúario")
	private String email;
	
	@Size(min = 8, message = "Por favor, informe a senha do usúario com pelo menos 8 caracteres")
	@NotEmpty(message = "Por favor, informe a senha do usúario")
	private String senha;
}