package br.com.cotiinformatica.components;

import java.util.Date;
import java.util.UUID;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenComponent {
	/*
	 * Método para gerar um TOKEN JWT para um usuário autenticado pelo sistema 
	 */
	public String generateToken(UUID usuarioId) {

		var dataAtual = new Date(); // pegando a data atual do sistema

		return Jwts.builder().setSubject(usuarioId.toString()) // identificação do usuário
			.setNotBefore(dataAtual) // data de geração do token
			.setExpiration(new Date(dataAtual.getTime() + 600000)) // data e hora de expiração do token
			.signWith(SignatureAlgorithm.HS256, "73cbce54-24dd-46af-a820-4e8fa5f8ecc2") // chave para assinatura do token
			.compact();
	}
}