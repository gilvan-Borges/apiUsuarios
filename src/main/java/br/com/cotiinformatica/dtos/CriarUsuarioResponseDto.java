package br.com.cotiinformatica.dtos;
import java.util.UUID;
import lombok.Data;

@Data
public class CriarUsuarioResponseDto {
	
	private UUID id;
	private String nome;
	private String email;
	private String perfil;
	private String mensagem;
}
