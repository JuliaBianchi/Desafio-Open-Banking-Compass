package br.com.api.demo.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeValidacaoDto> handler(MethodArgumentNotValidException exception) {
		List<ErroDeValidacaoDto> dto = new ArrayList<>();
		
		List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
		fieldError.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeValidacaoDto erro = new ErroDeValidacaoDto(e.getField(),mensagem);
			dto.add(erro);
		});
		
		return dto;
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public String notFound(NotFoundException exceptionNotFound) {
		return "ERRO: produto não encontrado";
		
	}
	
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public String notAllowed(HttpRequestMethodNotSupportedException exceptionNotAllowed) {
		return "ERRO: Método não permitido ";
	
	
	}
}
