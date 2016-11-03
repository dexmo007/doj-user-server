package rest;

import data.User;
import data.UserService;
import sun.misc.BASE64Decoder;

import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Authorization methods
 */
public class Authorization {

    public static boolean isUserAuthenticated(UserService userService, String authString) {
        if (authString == null || authString.isEmpty())
            return false;
        String decodedAuth = "";
        // Header is in the format "Basic 5tyc0uiDat4"
        // We need to extract data before decoding it back to original string
        String[] authParts = authString.split("\\s+");
        String authInfo = authParts[1];
        // Decode the data back to original string
        byte[] bytes = null;
        try {
            bytes = new BASE64Decoder().decodeBuffer(authInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        decodedAuth = new String(bytes);
        System.out.println(decodedAuth);

        String[] split = decodedAuth.split(":");
        if (split.length != 2)
            return false;

        User authUser = userService.get(split[0]);
        return authUser.getPw().equals(split[1]);
    }

    public static Response error(String msg) {
        String value = "{\"error\":\"" + msg + "\"}";
        return Response.accepted(value).build();
    }

}
