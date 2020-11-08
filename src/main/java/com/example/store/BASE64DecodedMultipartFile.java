package com.example.store;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

public class BASE64DecodedMultipartFile implements MultipartFile {


	private final byte[] content;

	private final String header;

	private final String contentType;

	private final String suffixName;

	private final String originalFilename;
	
	public BASE64DecodedMultipartFile(byte[] content, String header) {
		this.content = content;
		this.header = header;
		this.contentType = header.split("/")[0].split(":")[1];
		this.suffixName = header.split("/")[1];
		this.originalFilename = System.currentTimeMillis() + (int)Math.random() * 10000 + "." + this.suffixName;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getOriginalFilename() {
		return this.originalFilename;
	}

	
	@Override
	public String getContentType() {
		return this.contentType;
	}

	
	@Override
	public boolean isEmpty() {
		return (this.content == null || this.content.length == 0);
	}

	
	@Override
	public long getSize() {
		return this.content.length;
	}

	
	@Override
	public byte[] getBytes() throws IOException {
		return this.content;
	}

	
	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(this.content);
	}

	
	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		new FileOutputStream(dest).write(this.content);
	}

}
