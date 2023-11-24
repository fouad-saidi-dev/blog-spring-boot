package com.blog.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Util {

    private final String ALPHABET = "0123456789ABCDEFGHJKLMNOPQRSTWYZabcdefghjklmnopqrstwyz";

    private final Random RANDOM = new SecureRandom();

    public String generateStringId(int length) {
        StringBuilder builder = new StringBuilder();

        for (int i=0;i<length;i++) {
            builder.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(builder);
    }
}
