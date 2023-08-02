package br.com.unimedceara.core.utils;

import java.io.ByteArrayOutputStream;

public class UnzipedFile {
	
	String fileType;
	
	ByteArrayOutputStream byteArrayOutputStream;

	public String getfileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public ByteArrayOutputStream getByteArrayOutputStream() {
		return byteArrayOutputStream;
	}

	public void setByteArrayOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
		this.byteArrayOutputStream = byteArrayOutputStream;
	}	

}

