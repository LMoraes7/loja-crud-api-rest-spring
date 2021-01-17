package br.com.projeto.crud.loja.controller.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.projeto.crud.loja.dto.handler.ErroValidacaoDto;

@RestControllerAdvice
public class ValidadeHandlerController {

	@Autowired
	private MessageSource ms;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroValidacaoDto> handler(MethodArgumentNotValidException ex) {
		List<ErroValidacaoDto> errosDto = new ArrayList<ErroValidacaoDto>();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		fieldErrors.stream().forEach(erro -> {
			String mensagem = ms.getMessage(erro, LocaleContextHolder.getLocale());
			errosDto.add(new ErroValidacaoDto(erro.getField(), mensagem));
		});
		return errosDto;
	}
}
