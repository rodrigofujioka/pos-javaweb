package dev.fujioka.juan.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

@Service
public class MessageServiceImpl {

	@Autowired
	private MessageSource messageSource;

	public String getMessage(String key) {
		String mensagem;
		try {
			mensagem = messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
		} catch (Exception e) {
			mensagem = key;
		}
		return mensagem;

	}

	public String getMessage(FieldError field) {
		return messageSource.getMessage(field, LocaleContextHolder.getLocale());

	}
}
