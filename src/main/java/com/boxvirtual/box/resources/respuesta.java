package com.boxvirtual.box.resources;

import lombok.Data;

@Data
public class respuesta {
	private String explanation;
	private String message;
	private int statusCode;

	public respuesta(String explanation, String message, int statusCode){
		this.explanation = explanation;
		this.message = message;
		this.statusCode = statusCode;
	}
}
