package ulaval.glo2003.Exceptions;

public class ErrorResponse {
    public ErrorCode code;
    public String description;

    public ErrorResponse(ErrorCode errorCode, String description) {
        this.code = errorCode;
        this.description = description;
    }
}
