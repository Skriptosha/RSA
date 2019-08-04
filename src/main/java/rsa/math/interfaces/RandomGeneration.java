package rsa.math.interfaces;

import java.math.BigInteger;

/**
 * Генерация случайного числа размера bits бит. Необходима для создания ключа
 */
public interface RandomGeneration {

    BigInteger getRandomNumber(int bits);
}
