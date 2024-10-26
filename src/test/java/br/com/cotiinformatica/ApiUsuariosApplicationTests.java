package br.com.cotiinformatica;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import br.com.cotiinformatica.dtos.CriarUsuarioRequestDto;

@SpringBootTest // qualifica a classe como uma classe de testes do Spring Boot
@AutoConfigureMockMvc // Configurar a biblioteca MockMvc para testes em API
class ApiUsuariosApplicationTests {
	@Autowired
	MockMvc mockMvc; // executar chamadas de teste na API

	@Autowired
	ObjectMapper objectMapper; // enviar e receber dados em JSON

	@Test
	public void criarUsuarioTest() throws Exception {

		var dto = new CriarUsuarioRequestDto(); // criando objeto
		var faker = new Faker(); // criando objeto

		// preenchendo os dados da requisição
		dto.setNome(faker.name().fullName());
		dto.setEmail(faker.internet().emailAddress());
		dto.setSenha("@Teste2024");

		// fazendo uma requisição para cadastrar usuário na API
		var result = mockMvc.perform(post("/api/usuarios/criar") // chamada para a api
				.contentType("application/json") // definindo o formato de dados
				.content(objectMapper.writeValueAsString(dto))) // enviando os dados
				.andExpect(status().isOk()) // verificando se a resposta é OK
				.andReturn(); // capturando o resultadoj

		// verificando a resposta obtida da API
		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		assertTrue(content.contains("Usuário cadastrado com sucesso."));
	}

	@Test
	public void autenticarUsuarioTest() {
		
		fail("Não implementado.");
		
	}
}
