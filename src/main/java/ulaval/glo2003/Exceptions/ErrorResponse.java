package ulaval.glo2003.Exceptions;

public class ErrorResponse {
    ErrorCode errorCode;
    String description;

    public ErrorResponse (ErrorCode errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }
}
