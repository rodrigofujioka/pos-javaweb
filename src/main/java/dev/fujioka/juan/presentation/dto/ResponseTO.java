package dev.fujioka.juan.presentation.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseTO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private T data;

	private List<String> erros = new ArrayList<>();

	public ResponseTO(T data) {
		this.data = data;
	}
	
	public ResponseTO() {
	}
}
