package com.autentication.authenticationservice.security.exception;


/*
@Log4j2
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = UserIsBlocked.class)
    public ResponseEntity<Object> handleJournalNotFoundException(UserIsBlocked exception) {
        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(exception.getMessage());
        errors.setStatusName(HttpStatus.FORBIDDEN);
        errors.setStatusCode(HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(errors, HttpStatus.FORBIDDEN);
    }

/*
    @ExceptionHandler(value = TooManyUsersException.class)
    public ResponseEntity<Object> handleTooManyUsersException(TooManyUsersException exception) {
        logger.error("Too many users currently using controller access denied");

        CustomErrorResponse errors = new CustomErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(exception.getMessage());
        errors.setStatusName(HttpStatus.TOO_MANY_REQUESTS);
        errors.setStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());

        return new ResponseEntity<>(errors, HttpStatus.TOO_MANY_REQUESTS);
    }
    */

