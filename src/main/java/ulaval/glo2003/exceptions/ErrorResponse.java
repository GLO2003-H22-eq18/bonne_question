package ulaval.glo2003.exceptions;

public class ErrorResponse {
    public ErrorCode code;
    public String description;

    public ErrorResponse() {
        super();
    }

    public ErrorResponse(ErrorCode errorCode, String description) {
        this.code = errorCode;
        this.description = description;
    }
}
