package ulaval.glo2003.utils;

import org.bson.types.ObjectId;
import ulaval.glo2003.exceptions.InvalidArgumentException;
import ulaval.glo2003.exceptions.MissingArgumentException;

public class ObjectIdUtil {


    private static final int OBJECT_ID_LENGTH = 24;

    public static class MissingObjectIdException extends MissingArgumentException {
        public MissingObjectIdException(String objectName) {
            super(objectName + " id is missing");
        }
    }

    public static class InvalidObjectIdException extends InvalidArgumentException {
        public InvalidObjectIdException(String objectName) {
            super(objectName + " id is invalid");
        }
    }

    public static <T> ObjectId createValidObjectId(String stringObjectId, Class<T> objectClass) {

        checkMissingObjectId(stringObjectId, objectClass.getSimpleName());
        checkInvalidObjectId(stringObjectId, objectClass.getSimpleName());

        return new ObjectId(stringObjectId);
    }

    public static void checkMissingObjectId(String stringObjectId, String objectName) {
        if (stringObjectId == null) {
            throw new MissingObjectIdException(objectName);
        }
    }

    public static void checkInvalidObjectId(String stringObjectId, String objectName) {
        if (stringObjectId.length() != OBJECT_ID_LENGTH) {
            throw new InvalidObjectIdException(objectName);
        }
    }
}
