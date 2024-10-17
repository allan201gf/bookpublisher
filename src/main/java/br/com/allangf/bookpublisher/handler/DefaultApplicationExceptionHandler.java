package br.com.allangf.bookpublisher.handler;

import br.com.allangf.bookpublisher.domain.entity.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Integer.MIN_VALUE)
public class DefaultApplicationExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFound(NotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String trace = MDC.getCopyOfContextMap().get("traceId");
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setTitle("Not Found");
        errorDTO.setDescription(e.getMessage());
        errorDTO.setCode(String.valueOf(status.value()));
        errorDTO.setTraceId(trace);
        log.error("Response error: {}", errorDTO);
        return new ResponseEntity<>(errorDTO, status);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDTO> handleBadRequest(BadRequestException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String trace = MDC.getCopyOfContextMap().get("traceId");
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setTitle("Bad Request");
        errorDTO.setDescription(e.getMessage());
        errorDTO.setCode(String.valueOf(status.value()));
        errorDTO.setTraceId(trace);
        log.error("Response error: {}", errorDTO);
        return new ResponseEntity<>(errorDTO, status);
    }

}
