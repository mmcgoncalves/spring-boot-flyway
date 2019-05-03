package br.com.flyway.domain.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DocumentoUploadDTO {
	
	@NotNull
	@Min(value = 1, message = "Somente número positivos")
	private Integer numero;
	
	@NotNull	
	private String descricao;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	

}
