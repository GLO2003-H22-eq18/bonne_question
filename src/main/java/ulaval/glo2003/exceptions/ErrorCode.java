package ulaval.glo2003.exceptions;

public enum ErrorCode {
    MISSING_PARAMETER("MISSING_PARAMETER"),
    INVALID_PARAMETER("INVALID_PARAMETER"),
    ITEM_NOT_FOUND("ITEM_NOT_FOUND");

    private final String errorCode;

    ErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return this.errorCode;
    }
}


