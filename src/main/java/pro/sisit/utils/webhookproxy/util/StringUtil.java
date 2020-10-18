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

    public static String buildGitURL(String ssh, Boolean usingHttps) {
        if (ssh == null) {
            return null;
        }

        String[] data = ssh.split(":");
        if (data.length != 2) {
            return null;
        }

        String urlPart = data[0];
        urlPart = urlPart.replace("git@", "");

        return String.format("%s://%s/%s", usingHttps ? "https" : "http", urlPart, data[1]);
    }
}
