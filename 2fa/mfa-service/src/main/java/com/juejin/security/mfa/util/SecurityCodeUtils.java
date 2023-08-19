package com.juejin.security.mfa.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class SecurityCodeUtils {

    private SecurityCodeUtils() {}

    public static String generateSecurityCode() {
        String code;

        try {
            // 强随机数生成器，可以生成具有高熵的随机数
            SecureRandom random = SecureRandom.getInstanceStrong();
            // 由于需要生成6位数的验证码，因此将结果加上100000，以确保生成的随机数在100000到999999之间
            code = String.valueOf(random.nextInt(900000) + 100000);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("安全码生成失败");
        }

        return code;
    }
}
