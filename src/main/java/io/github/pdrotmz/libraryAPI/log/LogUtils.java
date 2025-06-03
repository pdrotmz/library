package io.github.pdrotmz.libraryAPI.log;

import java.util.Arrays;

public class LogUtils {

    public static String formatRequest(String endpoint, Object body) {
        return String.format("📥 [REQUEST] [%s] Payload: %s", endpoint, sanitizeSensitiveData(body));
    }

    public static String formatResponse(String endpoint, Object response) {
        return String.format("📤 [RESPONSE] [%s] Response: %s", endpoint, sanitizeSensitiveData(response));
    }

    public static String formatError(String context, Exception e) {
        return String.format("❌ [ERROR] [%s] Exception: %s", context, e.getMessage());
    }

    public static String sanitizeSensitiveData(Object data) {
        if (data == null) return "null";

        String raw;

        if (data instanceof Object[]) {
            raw = Arrays.toString((Object[]) data);
        } else {
            raw = data.toString();
        }

        return raw
                .replaceAll("(?i)(\"?token\"?\\s*[:=]\\s*\")([^\"]+)(\")", "$1***HIDDEN***$3")
                .replaceAll("(?i)(\"?password\"?\\s*[:=]\\s*\")([^\"]+)(\")", "$1***HIDDEN***$3")
                .replaceAll("(?i)(\"?email\"?\\s*[:=]\\s*\")([^\"]+)(\")", "$1***HIDDEN***$3")
                .replaceAll("(?i)(\"?cpf\"?\\s*[:=]\\s*\")([\\d.-]{11,14})(\")", "$1***HIDDEN***$3")
                .replaceAll("(?i)(Bearer\\s+)([a-zA-Z0-9._-]+)", "$1***HIDDEN***");
    }
}
