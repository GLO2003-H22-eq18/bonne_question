package ulaval.glo2003.utils;

import org.bson.types.ObjectId;
import ulaval.glo2003.exceptions.InvalidArgumentException;
import ulaval.glo2003.exceptions.MissingArgumentException;

public class ObjectIdUtil {

    private static final int OBJECT_ID_LENGTH = 24;

    public static class MissingObjectIdException extends MissingArgumentException {
        public MissingObjectIdException() {
            super("Object Id is missing");
        }
    }

    public static class InvalidObjectIdException extends InvalidArgumentException {
        public InvalidObjectIdException() {
            super("Object Id is invalid");
        }
    }

    public static ObjectId createValidObjectId(String stringObjectId) {

        checkMissingObjectId(stringObjectId);
        checkInvalidObjectId(stringObjectId);

        return new ObjectId(stringObjectId);
    }

    public static void checkMissingObjectId(String stringObjectId) {
        if (stringObjectId == null) {
            throw new MissingObjectIdException();
        }
    }

    public static void checkInvalidObjectId(String stringObjectId) {
        if (stringObjectId.length() != OBJECT_ID_LENGTH) {
            throw new InvalidObjectIdException();
        }
    }
}
