package br.com.cotiinformatica.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.components.JwtTokenComponent;
import br.com.cotiinformatica.components.SHA256Component;
import br.com.cotiinformatica.dtos.AutenticarUsuarioRequestDto;
import br.com.cotiinformatica.dtos.CriarUsuarioRequestDto;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.PerfilRepository;
import br.com.cotiinformatica.repositories.PermissaoRepository;
import br.com.cotiinformatica.repositories.UsuarioRepository;


@Service
public class UsuarioService {
	
	@Autowired //injeção de dependêcia
	UsuarioRepository usuarioRepository;

	@Autowired //injeção de dependêcia
	PerfilRepository perfilRepository;

	@Autowired //injeção de dependêcia 
	PermissaoRepository permissaoRepository;
	
	@Autowired //injeção de dependêcia
	SHA256Component sha256Component;
	
	@Autowired //injeção de dependêcia
	JwtTokenComponent jwtTokenComponent;
	 
	/* 
	 * Método para criar um usuario no banco
	 */
	
	public String criarUsuario(CriarUsuarioRequestDto dto) {
		
		//Regra de negocio: Não permitir o cadastro de 2 usuarios com o mesmo email
		
		if(usuarioRepository.findByEmail(dto.getEmail()) != null)
		  throw new IllegalArgumentException("O email informado já está cadastrado, tente outro.");
			
		
		//capturar os dados do usuário
		var usuario = new Usuario();
		usuario.setId(UUID.randomUUID());
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(sha256Component.hash(dto.getSenha()));
		usuario.setPerfil(perfilRepository.findByNome("OPERADOR"));
		
		//cadastrando o usúario no banco
		usuarioRepository.save(usuario);
		
		//retornando mensagem de sucesso
		return "Usuário cadastrado com sucesso.";
	}
	
	public String autenticarUsuario(AutenticarUsuarioRequestDto dto){
		
		//verificar se o usuario existe no banco de dados
		var usuario = usuarioRepository.findByEmailAndSenha(dto.getEmail(), sha256Component.hash(dto.getSenha()));
		
		//verificando se o usuario não foi encontrado
		if(usuario == null)
			throw new IllegalArgumentException("Usuário inválido");
		//gerando o token do usuário
		var token =jwtTokenComponent.generateToken(usuario.getId());
		
		//retornando o token
		
		return token;
	}
}
