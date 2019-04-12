package br.com.flyway.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "documentos")
public class Documento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "nome_original", nullable = false, length = 255)
	private String nomeOriginal;
	
	@NotNull
	@Size(min = 1)
	@Column(name = "nome_hash", nullable = false)
	private String nomeHash;
	
	@NotNull		
	private String contentType;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@NotNull
	@Size(min = 1)
	@Column(nullable = false)
	private String path;	

	@NotNull
	@Size(min = 1)
	@Column(nullable = false)
	private String checksum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeOriginal() {
		return nomeOriginal;
	}

	public void setNomeOriginal(String nomeOriginal) {
		this.nomeOriginal = nomeOriginal;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNomeHash() {
		return nomeHash;
	}

	public void setNomeHash(String nomeHash) {
		this.nomeHash = nomeHash;
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

}
