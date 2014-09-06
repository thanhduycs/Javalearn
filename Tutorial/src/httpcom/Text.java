package httpcom;

import java.util.HashMap;

public class Text {
    public static String removeVIE(String strVie) {
        final String SPECIAL_CHARACTERS = "àÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬđĐèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆìÌỉỈĩĨíÍịỊòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰýÝỳỲỷỶỹỸỵỴ";
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
    
    
}
