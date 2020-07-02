package test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * test.Test2
 *
 * @desc:
 * @author: wuao <wuao@baidu.com>
 * @time: 2020-03-18 12:44
 */
public abstract class Test2 {

    public static void main(String[] args) throws Exception {
        Mac sha512_HMAC = null;
        String result = null;
        String context = "baidu@2020$";

        byte[] byteKey = context.getBytes(StandardCharsets.UTF_8);
        final String HMAC_SHA512 = "HmacSHA512";
        sha512_HMAC = Mac.getInstance(HMAC_SHA512);
        SecretKeySpec keySpec = new SecretKeySpec(byteKey, HMAC_SHA512);
        sha512_HMAC.init(keySpec);
        byte[] mac_data = sha512_HMAC.
                doFinal("00073267604D".getBytes(StandardCharsets.UTF_8));
        //result = Base64.encode(mac_data);
        result = bytesToHex(mac_data);
        System.out.println(result);
    }

    private static String bytesToHex(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        byte[] var2 = data;
        int var3 = data.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte aData = var2[var4];
            String hex = Integer.toHexString(aData);
            if (hex.length() == 1) {
                sb.append("0");
            } else if (hex.length() == 8) {
                hex = hex.substring(6);
            }

            sb.append(hex);
        }

        return sb.toString().toLowerCase(Locale.getDefault());
    }
}
