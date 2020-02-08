package dev.fujioka.juan.infrastructure.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ConverterServiceImpl {

	@Autowired
	private ModelMapper modelMapper;

	public <T> T converter(Object request, Class<T> tipoDestino) {
		return modelMapper.map(request, tipoDestino);

	}
	
	public <T> Page<T> converter(Page<?> lista, Class<T> tipoDestino) {
		return lista.map(d -> converter(d, tipoDestino));
	}
	
	public <T> List<T> convert(List<?> dataList, Class<T> tipoDestino) {
		return dataList.stream().map(d -> converter(d, tipoDestino)).collect(Collectors.toList());
	}

}
