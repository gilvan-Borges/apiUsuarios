package br.com.cotiinformatica.components;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.cotiinformatica.entities.Perfil;
import br.com.cotiinformatica.entities.Permissao;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.PerfilRepository;
import br.com.cotiinformatica.repositories.PermissaoRepository;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Component
public class LoadDataComponent implements ApplicationRunner {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PerfilRepository perfilRepository;

	@Autowired
	PermissaoRepository permissaoRepository;

	@Autowired
	SHA256Component sha256Component;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		var permissaoCadastro = new Permissao();
		permissaoCadastro.setId(UUID.fromString("fc5623b4-ab30-484f-91b3-db95aeca7d52"));
		permissaoCadastro.setNome("CADASTRAR CLIENTES");

		var permissaoConsulta = new Permissao();
		permissaoConsulta.setId(UUID.fromString("150551c2-5c27-4ec7-8cd1-b7f360449d13"));
		permissaoConsulta.setNome("CONSULTAR CLIENTES");

		var permissaoEdicao = new Permissao();
		permissaoEdicao.setId(UUID.fromString("cdad386e-c44c-4166-b6e7-d113c6924514"));
		permissaoEdicao.setNome("EDITAR CLIENTES");

		var permissaoExclusao = new Permissao();
		permissaoExclusao.setId(UUID.fromString("44231dcf-193f-4736-95ad-1665039a0a51"));
		permissaoExclusao.setNome("EXCLUIR CLIENTES");

		permissaoRepository.save(permissaoCadastro);
		permissaoRepository.save(permissaoConsulta);
		permissaoRepository.save(permissaoEdicao);
		permissaoRepository.save(permissaoExclusao);

		var perfilAdministrador = new Perfil();
		perfilAdministrador.setId(UUID.fromString("a0278bac-d28b-4892-b730-740970b7f1bd"));
		perfilAdministrador.setNome("ADMINISTRADOR");

		var permissoesAdministrador = new ArrayList<Permissao>();
		permissoesAdministrador.add(permissaoCadastro);
		permissoesAdministrador.add(permissaoConsulta);
		permissoesAdministrador.add(permissaoEdicao);
		permissoesAdministrador.add(permissaoExclusao);

		perfilAdministrador.setPermissoes(permissoesAdministrador);

		perfilRepository.save(perfilAdministrador);

		var perfilOperador = new Perfil();
		perfilOperador.setId(UUID.fromString("c583b6b7-be76-4b51-8db5-4545d5860b6c"));
		perfilOperador.setNome("OPERADOR");

		var permissoesOperador = new ArrayList<Permissao>();
		permissoesOperador.add(permissaoConsulta);

		perfilOperador.setPermissoes(permissoesOperador);

		perfilRepository.save(perfilOperador);

		var usuarioAdministrador = new Usuario();
		usuarioAdministrador.setId(UUID.fromString("f5b8e780-30c1-4b6e-a8f4-96d76ede67c4"));
		usuarioAdministrador.setNome("Gilvan Borges");
		usuarioAdministrador.setEmail("gilvan.borges@gmail.com");
		usuarioAdministrador.setSenha(sha256Component.hash("@Admin123"));
		usuarioAdministrador.setPerfil(perfilAdministrador);

		usuarioRepository.save(usuarioAdministrador);
	}
}
