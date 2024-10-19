package br.com.cotiinformatica.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_permissao")
public class Permissao {
	
	@Id
	@Column(name = "id")
	private UUID id;
	                                    //NOT NULL
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;
	
	@ManyToMany(mappedBy = "permissoes")
	private List<Perfil> perfis;
}