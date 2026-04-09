package flin.financial.demo.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.math.BigInteger;

@Converter
public class BigIntegerEncryptConverter implements AttributeConverter<BigInteger, String> {

    @Override
    public String convertToDatabaseColumn(BigInteger attribute) {
        if (attribute == null) return null;
        return CryptoUtils.encrypt(attribute.toString());
    }

    @Override
    public BigInteger convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return new BigInteger(CryptoUtils.decrypt(dbData));
    }
}
