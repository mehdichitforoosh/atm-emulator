package com.energizeglobal.assignment.exception.handler;

import com.energizeglobal.assignment.common.json.ErrorArrayJsonObject;
import com.energizeglobal.assignment.common.json.ErrorJsonObject;
import com.energizeglobal.assignment.exception.CardBlockedException;
import com.energizeglobal.assignment.exception.ValidationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * using basePackages attribute of @ControllerAdvice to limit the controller classes
 *
 * @author Mehdi Chitforoosh
 * @since 1.0.0
 */
@ControllerAdvice(basePackages = {"com.energizeglobal.assignment"})
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final MessageSourceAccessor messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSourceAccessor messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(CardBlockedException.class)
    @ResponseBody
    public ErrorJsonObject handleCardBlockedException(CardBlockedException ex) {
        logger.error("CardBlockedException handler executed", ex);
        String message = messageSource.getMessage("CardBlockedException", new Object[]{});
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("CardBlockedException", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.FORBIDDEN.value(), message, arrayJsonObjectList);
        return errorJsonObject;
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ErrorJsonObject handleAccessDeniedException(AccessDeniedException ex) {
        logger.error("AccessDeniedException handler executed", ex);
        String message = messageSource.getMessage("AccessDeniedException", new Object[]{});
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("AccessDeniedException", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.FORBIDDEN.value(), message, arrayJsonObjectList);
        return errorJsonObject;
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ErrorJsonObject handleAuthenticationException(AuthenticationException ex) {
        logger.error("AuthenticationException handler executed", ex);
        String message = messageSource.getMessage("AuthenticationException", new Object[]{});
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("AuthenticationException", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.FORBIDDEN.value(), message, arrayJsonObjectList);
        return errorJsonObject;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ErrorJsonObject handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        logger.error("MissingServletRequestParameterException handler executed", ex);
        String name = ex.getParameterName();
        String message = messageSource.getMessage("MissingServletRequestParameterException", new Object[]{name});
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("MissingServletRequestParameterException", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, arrayJsonObjectList);
        return errorJsonObject;
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorJsonObject handleUnrecognizedPropertyException(HttpMessageNotReadableException ex) {
        logger.error("UnrecognizedPropertyException handler executed.", ex);
        Throwable throwable = ex.getRootCause();
        ArrayList<String> s = new ArrayList<String>();
        if (throwable instanceof JsonParseException) {
            return null;
        } else if (throwable instanceof UnrecognizedPropertyException) {
            UnrecognizedPropertyException exception = (UnrecognizedPropertyException) ex.getRootCause();
            String unrecognizedPropertyName = exception.getPropertyName();
            s.add(unrecognizedPropertyName);
            String message = messageSource.getMessage("UnrecognizedPropertyException", new Object[]{unrecognizedPropertyName});
            List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
            ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("UnrecognizedPropertyException", message);
            arrayJsonObjectList.add(arrayJsonObject);
            ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.BAD_REQUEST.value(), message, arrayJsonObjectList);
            return errorJsonObject;
        } else {
            String message = messageSource.getMessage("HttpMessageNotReadableException", new Object[]{});
            List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
            ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("HttpMessageNotReadableException", message);
            arrayJsonObjectList.add(arrayJsonObject);
            ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.BAD_REQUEST.value(), message, arrayJsonObjectList);
            return errorJsonObject;
        }
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ErrorJsonObject handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        logger.error("HttpRequestMethodNotSupportedException handler executed.", ex);
        String message = messageSource.getMessage("HttpRequestMethodNotSupportedException", new Object[]{});
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("HttpRequestMethodNotSupportedException", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.METHOD_NOT_ALLOWED.value(), message, arrayJsonObjectList);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setAllow(ex.getSupportedHttpMethods());
        return errorJsonObject;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorJsonObject handleIllegalArgumentException(IllegalArgumentException ex) {
        ex.printStackTrace();
        logger.error("IllegalArgumentException handler executed.", ex);
        String message = messageSource.getMessage("IllegalArgumentException", new Object[]{});
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("IllegalArgumentException", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.NOT_FOUND.value(), message, arrayJsonObjectList);
        return errorJsonObject;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorJsonObject handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error("MethodArgumentNotValidException handler executed.", ex);
        String message = messageSource.getMessage("MethodArgumentNotValidException", new Object[]{});
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("MethodArgumentNotValidException", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.BAD_REQUEST.value(), message, arrayJsonObjectList);
        return errorJsonObject;
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorJsonObject handleValidationException(ValidationException ex) {
        logger.error("ValidationException handler executed.", ex);
        Locale locale = LocaleContextHolder.getLocale();
        Errors result = ex.getErrors();
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        if (result != null) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            List<ObjectError> globalErrors = result.getGlobalErrors();
            for (FieldError fieldError : fieldErrors) {
                String field = fieldError.getField();
                String objectName = fieldError.getObjectName();
                String code = fieldError.getCode() + "." + objectName + "." + field;
                String message = messageSource.getMessage(code, new Object[]{}, locale);
                ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject(result.getObjectName(), field, code, message);
                arrayJsonObjectList.add(arrayJsonObject);
            }
            for (ObjectError objectError : globalErrors) {
                String objectName = objectError.getObjectName();
                String code = objectError.getCode() + "." + objectName;
                String message = messageSource.getMessage(code, new Object[]{}, locale);
                ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject(result.getObjectName(), code, message);
                arrayJsonObjectList.add(arrayJsonObject);
            }
        }
        List<FieldError> errorCodes = ex.getFieldErrors();
        if (errorCodes != null) {
            for (FieldError fieldError : errorCodes) {
                String field = fieldError.getField();
                String objectName = fieldError.getObjectName();
                String code = fieldError.getCode() + "." + objectName + "." + field;
                String message = messageSource.getMessage(code, new Object[]{}, locale);
                ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject(result.getObjectName(), field, code, message);
                arrayJsonObjectList.add(arrayJsonObject);
            }
        }
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.BAD_REQUEST.value(), "", arrayJsonObjectList);
        return errorJsonObject;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorJsonObject handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        logger.error("EmptyResultDataAccessException handler executed", ex);
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("EmptyResultDataAccessException", new Object[]{}, locale);
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("EmptyResultDataAccessException", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.NOT_FOUND.value(), message, arrayJsonObjectList);
        return errorJsonObject;
    }

    @ExceptionHandler(DataRetrievalFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorJsonObject handleDataRetrievalFailureException(DataRetrievalFailureException ex) {
        logger.error("DataRetrievalFailureException handler executed", ex);
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("DataRetrievalFailureException", new Object[]{}, locale);
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("DataRetrievalFailureException", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, arrayJsonObjectList);
        return errorJsonObject;
    }

    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorJsonObject handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException ex) {
        logger.error("IncorrectResultSizeDataAccessException handler executed", ex);
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("IncorrectResultSizeDataAccessException", new Object[]{}, locale);
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("IncorrectResultSizeDataAccessException", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, arrayJsonObjectList);
        return errorJsonObject;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public ErrorJsonObject handleDataAccessException(DataAccessException ex) {
        logger.error("DataAccessException handler executed", ex);
        String message = messageSource.getMessage("DataAccessException", new Object[]{});
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("DataAccessException", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, arrayJsonObjectList);
        return errorJsonObject;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorJsonObject handleRuntimeException(RuntimeException ex) {
        logger.error("RuntimeException handler executed", ex);
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("RuntimeException", new Object[]{}, locale);
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("RuntimeException", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, arrayJsonObjectList);
        return errorJsonObject;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorJsonObject handleException(Exception ex) {
        logger.error("Exception handler executed", ex);
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("Exception", new Object[]{}, locale);
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("Exception", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, arrayJsonObjectList);
        return errorJsonObject;
    }

    @ExceptionHandler(NestedServletException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorJsonObject handleNestedServletException(NestedServletException ex) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("NestedServletException", new Object[]{}, locale);
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("NestedServletException", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, arrayJsonObjectList);
        return errorJsonObject;
    }

    //???????????????????
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorJsonObject handleException(Throwable t) {
        t.printStackTrace();
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("Exception", new Object[]{}, locale);
        List<ErrorArrayJsonObject> arrayJsonObjectList = new ArrayList<ErrorArrayJsonObject>();
        ErrorArrayJsonObject arrayJsonObject = new ErrorArrayJsonObject("Throwable", message);
        arrayJsonObjectList.add(arrayJsonObject);
        ErrorJsonObject errorJsonObject = new ErrorJsonObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, arrayJsonObjectList);
        return errorJsonObject;
    }
}
