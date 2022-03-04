package ulaval.glo2003.Utils;

public class StringUtil {
    public static String removeEmptyChar(String string) {
        return string.replaceAll("\n", "")
                .replaceAll("\t", "")
                .replaceAll(" ", "")
                .replaceAll("0", "");
    }

    public static String mixUpperAndLowerCase(String string){
        char[] letters = string.toCharArray();
        StringBuilder randomizedString = new StringBuilder(letters.length);
        for (int i = 0; i < letters.length; i++) {
            if (i % 2 == 0) {
                randomizedString.append(Character.toLowerCase(letters[i]));
            }
            else {
                randomizedString.append(Character.toUpperCase(letters[i]));
                }
        }

        return randomizedString.toString();
    }
}
