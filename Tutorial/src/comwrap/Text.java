package comwrap;

import java.util.HashMap;

import org.w3c.dom.Element;

public class Text {
    public static String removeVIE(String strVie) {
        final String SPECIAL_CHARACTERS = "àÀảẢãÃá�?ạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬđ�?èÈẻẺẽẼéÉẹẸêÊ�?ỀểỂễỄếẾệỆìÌỉỈĩĨí�?ịỊòÒ�?ỎõÕóÓ�?ỌôÔồỒổỔỗỖố�?ộỘơƠ�?ỜởỞỡỠớỚợỢùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰý�?ỳỲỷỶỹỸỵỴ";
        final String REPLACEMENTS = "aAaAaAaAaAaAaAaAaAaAaAaAaAaAaAaAaAdDeEeEeEeEeEeEeEeEeEeEeEiIiIiIiIiIoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOuUuUuUuUuUuUuUuUuUuUuUyYyYyYyYyYyY";

        HashMap<Character, Character> hashMap = new HashMap<Character, Character>(
                SPECIAL_CHARACTERS.length());
        for (int i = 0; i < SPECIAL_CHARACTERS.length(); i++) {
            hashMap.put(SPECIAL_CHARACTERS.charAt(i), REPLACEMENTS.charAt(i));
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Character chr : strVie.toCharArray()) {
            Character newChr = hashMap.get(chr);

            if (newChr != null)
                stringBuilder.append(newChr);
            else
                stringBuilder.append(chr);

        }
        return stringBuilder.toString();
    }

    public static String XMLGetTextValue(Element el, String tagName) {
        return el.getElementsByTagName(tagName).item(0).getTextContent();
    }

    public static String capitalize(String text) {
        boolean space = true;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char chr = text.charAt(i);
            if (chr != '\t' && chr != '\n' && chr != ' ') {
                if (space)
                    stringBuilder.append(" ");
                space = false;
                stringBuilder.append(chr);
            } else {
                space = true;
            }
        }
        return stringBuilder.toString();
    }
}
