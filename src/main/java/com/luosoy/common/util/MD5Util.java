package com.luosoy.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述:MD5工具类
 * 版权: 税友软件集团股份有限公司
 * 日期: 2018/2/1
 * 作者: jsh
 */
@Slf4j
public class MD5Util {

    private static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static MessageDigest messagedigest = null;
    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
           log.error("MD5Util工具类初始化失败",e);
        }
    }

    public static String getMD5String(String str) {
        return getMD5String(str.getBytes());
    }
    public static String getFileMD5String(File file) throws IOException {
        return getFileMD5String(file, 0, -1);
    }

    public static String getFileMD5String(File file, long start, long length)
            throws IOException {
        FileInputStream fis = new FileInputStream(file);

        try {
            FileChannel ch = fis.getChannel();

            long remain = file.length() - start;

            if (length < 0 || length > remain) {
                length = remain;
            }

            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY,
                    start, length);

            messagedigest.update(byteBuffer);
        } catch (Exception e) {
           throw e;
        } finally {
            IOUtils.closeQuietly(fis);
        }


        return bufferToHex(messagedigest.digest());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
}
