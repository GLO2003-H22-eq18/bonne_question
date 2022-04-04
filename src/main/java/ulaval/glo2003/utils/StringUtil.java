package ulaval.glo2003.utils;

public class StringUtil {
    public static String removeEmptyChar(String string) {
        return string.replaceAll("\n", "")
                .replaceAll("\t", "")
                .replaceAll(" ", "")
                .replaceAll("0", "");
    }
}
