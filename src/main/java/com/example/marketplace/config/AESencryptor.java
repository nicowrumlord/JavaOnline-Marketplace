package com.example.marketplace.config;

import jakarta.persistence.AttributeConverter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Configuration
public class AESencryptor implements AttributeConverter<Object, String>{
    @Value("${aes.encryption.key}")
    private String encryptionKey;
    private final String encryptionCypher = "AES";

    private static Key key;
    private static Cipher cipher;

    private Key getKey() {
        if (key == null)
            key = new SecretKeySpec(encryptionKey.getBytes(), encryptionCypher);
        return key;
    }

    private Cipher getCipher() {
        try {
            if (cipher == null)
                cipher = Cipher.getInstance(encryptionCypher);
            return cipher;
        } catch (NoSuchPaddingException paddingException){
            throw new RuntimeException(paddingException);
        }
          catch (NoSuchAlgorithmException exception) {
              throw new RuntimeException(exception);
          }
    }

    private void initCipher(int encryptMode) throws InvalidKeyException {
        getCipher().init(encryptMode, getKey());
    }

    @SneakyThrows
    @Override //encrypt
    public String convertToDatabaseColumn(Object attribute) {
        if (attribute == null)
            return  null;
        initCipher(Cipher.ENCRYPT_MODE);
        byte[] bytes = SerializationUtils.serialize(attribute);
        return Base64.getEncoder().encodeToString(getCipher().doFinal(bytes));

    }

    @SneakyThrows
    @Override //decrypt
    public Object convertToEntityAttribute(String dbData) {
        if (dbData == null){
            return null;
        }
        initCipher(Cipher.DECRYPT_MODE);
        byte[] bytes = getCipher().doFinal(Base64.getDecoder().decode(dbData));
        return new String(cipher.doFinal(Base64.getDecoder().decode(bytes)));
    }
}
