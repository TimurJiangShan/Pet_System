package com.example.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class EncryptionUtil {

	 /**
     * Table
     */
    public static char[] encodeTable = {'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'};

    /**
     * Base64 Code
     */
    public static String encoderBase64(byte[] value) {
        StringBuilder sb = new StringBuilder();
        int len = value.length;
        int len3 = len / 3;
        for (int i = 0; i < len3; i++) {

            int b1 = (value[i * 3] >> 2) & 0x3F;
            char c1 = encodeTable[b1];
            sb.append(c1);

            int b2 = ((value[i * 3] << 4 & 0x3F) + (value[i * 3 + 1] >> 4)) & 0x3F;
            char c2 = encodeTable[b2];
            sb.append(c2);

            int b3 = ((value[i * 3 + 1] << 2 & 0x3C) + (value[i * 3 + 2] >> 6)) & 0x3F;
            char c3 = encodeTable[b3];
            sb.append(c3);

            int b4 = value[i * 3 + 2] & 0x3F;
            char c4 = encodeTable[b4];
            sb.append(c4);

        }

        int less = len % 3;
        if (less == 1) {

            int b1 = value[len3 * 3] >> 2 & 0x3F;
            char c1 = encodeTable[b1];
            sb.append(c1);

            int b2 = (value[len3 * 3] << 4 & 0x30) & 0x3F;
            char c2 = encodeTable[b2];
            sb.append(c2);
            sb.append("==");

        } else if (less == 2) {

            int b1 = value[len3 * 3] >> 2 & 0x3F;
            char c1 = encodeTable[b1];
            sb.append(c1);

            int b2 = ((value[len3 * 3] << 4 & 0x30) + (value[len3 * 3 + 1] >> 4)) & 0x3F;
            char c2 = encodeTable[b2];
            sb.append(c2);

            int b3 = (value[len3 * 3 + 1] << 2 & 0x3C) & 0x3F;
            char c3 = encodeTable[b3];
            sb.append(c3);
            sb.append("=");

        }

        return sb.toString();
    }

    /**
     * Base64 decode
     */
    public static String decoderBase64(byte[] value) {

        int len = value.length;
        int len4 = len / 4;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (i = 0; i < len4 - 1; i++) {

            byte b1 = (byte) ((char2Index((char) value[i * 4]) << 2) + (char2Index((char) value[i * 4 + 1]) >> 4));
            sb.append((char) b1);
            byte b2 = (byte) ((char2Index((char) value[i * 4 + 1]) << 4)
                    + (char2Index((char) value[i * 4 + 2]) >> 2));
            sb.append((char) b2);
            byte b3 = (byte) ((char2Index((char) value[i * 4 + 2]) << 6) + (char2Index((char) value[i * 4 + 3])));
            sb.append((char) b3);

        }

        for (int j = 0; j < 3; j++) {
            int index = i * 4 + j;
            if ((char) value[index + 1] != '=') {

                if (j == 0) {
                    byte b = (byte) ((char2Index((char) value[index]) << 2)
                            + (char2Index((char) value[index + 1]) >> 4));
                    sb.append((char) b);
                } else if (j == 1) {
                    byte b = (byte) ((char2Index((char) value[index]) << 4)
                            + (char2Index((char) value[index + 1]) >> 2));
                    sb.append((char) b);
                } else if (j == 2) {
                    byte b = (byte) ((char2Index((char) value[index]) << 6)
                            + (char2Index((char) value[index + 1])));
                    sb.append((char) b);
                }

            } else {
                break;
            }
        }

        return sb.toString();
    }


    public static int char2Index(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return ch - 'A';
        } else if (ch >= 'a' && ch <= 'z') {
            return 26 + ch - 'a';
        } else if (ch >= '0' && ch <= '9') {
            return 52 + ch - '0';
        } else if (ch == '+') {
            return 62;
        } else if (ch == '/') {
            return 63;
        }
        return 0;
    }
}
