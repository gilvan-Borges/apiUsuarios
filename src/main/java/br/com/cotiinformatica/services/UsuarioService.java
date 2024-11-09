package br.com.cotiinformatica.services;


import java.util.Date;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.cotiinformatica.components.JwtTokenComponent;
import br.com.cotiinformatica.components.SHA256Component;
import br.com.cotiinformatica.dtos.AutenticarUsuarioRequestDto;
import br.com.cotiinformatica.dtos.AutenticarUsuarioResponseDto;
import br.com.cotiinformatica.dtos.CriarUsuarioRequestDto;
import br.com.cotiinformatica.dtos.CriarUsuarioResponseDto;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.PerfilRepository;
import br.com.cotiinformatica.repositories.PermissaoRepository;
import br.com.cotiinformatica.repositories.UsuarioRepository;


@Service
public class UsuarioService {


	@Autowired //injeção de dependência
	UsuarioRepository usuarioRepository;
	
	@Autowired //injeção de dependência
	PerfilRepository perfilRepository;
	
	@Autowired //injeção de dependência
	PermissaoRepository permissaoRepository;
	
	@Autowired //injeção de dependência
	SHA256Component sha256Component;
	
	@Autowired //injeção de dependência
	JwtTokenComponent jwtTokenComponent;
	
	/*
	 * Método para criar um usuário no banco de dados
	 */
	public CriarUsuarioResponseDto criarUsuario(CriarUsuarioRequestDto dto) {

		
		//Regra de negócio: Não permitir o cadastro de 2 usuários com o mesmo email
		if(usuarioRepository.findByEmail(dto.getEmail()) != null)
			throw new IllegalArgumentException("O email informado já está cadastrado, tente outro.");
		
		//capturar os dados do usuário
		var usuario = new Usuario();
		usuario.setId(UUID.randomUUID());
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(sha256Component.hash(dto.getSenha()));
		usuario.setPerfil(perfilRepository.findByNome("OPERADOR"));
		
		//cadastrando o usuário no banco de dados
		usuarioRepository.save(usuario);
		
		//retornando o dto de resposta
		var response = new CriarUsuarioResponseDto();
		response.setId(usuario.getId());
		response.setNome(usuario.getNome());
		response.setEmail(usuario.getEmail());
		response.setPerfil(usuario.getPerfil().getNome());
		response.setMensagem("Usuário cadastrado com sucesso.");
				
		return response;
	}
	
	/*
	 * Método para autenticar um usuário no banco de dados
	 */
	public AutenticarUsuarioResponseDto autenticarUsuario(AutenticarUsuarioRequestDto dto) {


		//buscando os dados do usuário no banco através do email e da senha
		var usuario = usuarioRepository.findByEmailAndSenha(dto.getEmail(), sha256Component.hash(dto.getSenha()));
		
		//verificando se o usuário não foi encontrado
		if(usuario == null)
			throw new IllegalArgumentException("Usuário inválido.");
		
		//gerando o token do usuário
		var token = jwtTokenComponent.generateToken(usuario.getId());
		
		//retornando o dto de resposta
		var response = new AutenticarUsuarioResponseDto();
		response.setId(usuario.getId());
		response.setNome(usuario.getNome());
		response.setEmail(usuario.getEmail());
		response.setToken(token);
		response.setDataHoraAcesso(new Date());
		response.setDataHoraExpiracao(new Date(new Date().getTime() + 600000));
		response.setPerfil(usuario.getPerfil().getNome());
		response.setMensagem("Usuário autenticado com sucesso.");
		
		return response;
	}
}













