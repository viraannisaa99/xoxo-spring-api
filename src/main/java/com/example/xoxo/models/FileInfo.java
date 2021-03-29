package com.example.xoxo.models;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class FileInfo {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "url")
	private String url;
	
    @Lob
    @Column(name= "file")
    private byte[] file;

	public FileInfo(String name, String url, byte[] file) {
		this.name = name;
		this.url = url;
		this.file = file;
	}
	
	public FileInfo(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
}
