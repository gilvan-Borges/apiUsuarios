package br.com.cotiinformatica.dtos;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class AutenticarUsuarioResponseDto {
	
	private UUID id;
	private String nome;
	private String email;
	private String token;
	private Date dataHoraAcesso;
	private Date dataHoraExpiracao;
	private String perfil;
	private String mensagem;
}
