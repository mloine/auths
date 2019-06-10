package com.mloine.auth.auths.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SerializaUtil {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(SerializaUtil.class);

    /**
     * 序列化
     *
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        if (logger.isDebugEnabled()) {
            logger.debug("Object - start"); //$NON-NLS-1$
        }

        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        byte[] bytes = null;
        try {
            baos = null;
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            logger.error("序列化异常", e);
        } finally {
            try {
                if (null != oos) {
                    oos.close();
                }
                if (null != baos) {
                    baos.close();
                }
            } catch (IOException e) {
                logger.error("序列化异常", e);
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Object - end"); //$NON-NLS-1$
        }
        return bytes;
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        if (logger.isDebugEnabled()) {
            logger.debug("byte[] - start"); //$NON-NLS-1$
        }

        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        Object ob = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            ob = ois.readObject();
        } catch (Exception e) {
            logger.error("反序列化异常", e);
        } finally {
            try {
                if (null != bais) {
                    bais.close();
                }
                if (null != ois) {
                    ois.close();
                }
            } catch (IOException e) {
                logger.error("反序列化异常", e);
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("byte[] - end"); //$NON-NLS-1$
        }
        return ob;
    }
}
