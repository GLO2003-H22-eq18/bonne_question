package ulaval.glo2003.Exceptions;

public class ErrorResponse {
    public ErrorCode errorCode;
    public String description;

    public ErrorResponse (ErrorCode errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }
}
