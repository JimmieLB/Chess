public class StringUtils {
    public static String reverse(String str) {
        StringBuilder output = new StringBuilder();
        for (int i=0; i<str.length(); i++)
        {
            char ch = str.charAt(i); //extracts each character
            output.insert(0, ch); //adds each character in front of the existing string
        }
        return output.toString();
    }
}
