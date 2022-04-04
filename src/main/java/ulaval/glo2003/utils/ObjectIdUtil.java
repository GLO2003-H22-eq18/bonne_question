package ulaval.glo2003.utils;

import org.bson.types.ObjectId;

public class ObjectIdUtil {
    static public ObjectId createValidObjectId(String stringObjectId) {
        ObjectId objectId = new ObjectId();

        if (stringObjectId != null && stringObjectId.length() == 24) {
            objectId = new ObjectId(stringObjectId);
        }

        return objectId;
    }
}
