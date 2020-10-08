package pro.sisit.utils.webhookproxy.util;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.text.StringEscapeUtils;

public class StringUtil {

    public static String getCutEscapedString(String message) {
        return getCutEscapedString(message, 50, "<empty>");
    }

    public static String getCutEscapedString(String message, int maxLength, String emptyMessage) {
        return RegExUtils.replaceAll(
                StringEscapeUtils.escapeHtml4(getCutString(message, maxLength, emptyMessage)),
                "\\W+", " ");
    }

    public static String getCutString(String message, int maxLength, String emptyMessage) {
        if (message != null) {
            if (message.length() > maxLength && (maxLength > 4)) {
                return message.substring(0, maxLength - 4) + " ...";
            }
            return message;
        }

        return emptyMessage;
    }
}
