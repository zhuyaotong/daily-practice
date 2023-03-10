package com.github.zhuyaotong.generator;

import com.google.common.annotations.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

public interface IdGenerator {
    String generate();
}

class IdGenerationFailureException extends RuntimeException {
    public IdGenerationFailureException(String message, UnknownHostException e) {
        super(message, e);
    }
}

interface LogTraceIdGenerator extends IdGenerator {
}

/**
 * Id Generator that is used to generate random IDs.
 *
 * <p>
 * The IDs generated by this class are not absolutely unique,
 * but the probability of duplication is very low.
 */
class RandomIdGenerator implements LogTraceIdGenerator {
    private static final Logger logger = LoggerFactory.getLogger(RandomIdGenerator.class);

    /**
     * Generate the random ID. The IDs may be duplicated only in extreme situation.
     *
     * @return an random ID.
     */
    @Override
    public String generate() throws IdGenerationFailureException {
        String substrOfHostName = null;
        try {
            substrOfHostName = getLastfieldOfHostName();
        } catch (UnknownHostException e) {
            throw new IdGenerationFailureException("host name is empty.", e);
        }
        long currentTimeMillis = System.currentTimeMillis();
        String randomString = generateRandomAlphameric(8);
        String id = String.format("%s-%d-%s",
                substrOfHostName, currentTimeMillis, randomString);
        return id;
    }

    /**
     * Get the local hostname and
     * extract the last field of the name string splitted by delimiter '.'.
     *
     * @return the last field of hostname. Throws UnknownHostException if hostname is null or empty string.
     */
    private String getLastfieldOfHostName() throws UnknownHostException {
        String substrOfHostName = null;
        String hostName = InetAddress.getLocalHost().getHostName();
        if (hostName == null || hostName.isEmpty()) {
            throw new UnknownHostException("...");
        }
        substrOfHostName = getLastSubstrSplittedByDot(hostName);
        return substrOfHostName;
    }

    /**
     * Get the last field of {@hostName} splitted by delemiter '.'.
     *
     * @param hostName should not be null
     * @return the last field of {@hostName}. Throws IllegalArgumentException if {@hostName} is empty string or null.
     */
    @VisibleForTesting
    protected String getLastSubstrSplittedByDot(String hostName) {
        if (hostName == null || hostName.isEmpty()) {
            throw new IllegalArgumentException("...");
        }
        String[] tokens = hostName.split("\\.");
        String substrOfHostName = tokens[tokens.length - 1];
        return substrOfHostName;
    }

    /**
     * Generate random string which
     * only contains digits, uppercase letters and lowercase letters.
     *
     * @param length should not be less equal than 0
     * @return the random string. Throws IllegalArgumentException if {@length} is less equal 0.
     */
    @VisibleForTesting
    protected String generateRandomAlphameric(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("...");
        }
        char[] randomChars = new char[length];
        int count = 0;
        Random random = new Random();
        while (count < length) {
            int maxAscii = 'z';
            int randomAscii = random.nextInt(maxAscii);
            boolean isDigit = randomAscii >= '0' && randomAscii <= '9';
            boolean isUppercase = randomAscii >= 'A' && randomAscii <= 'Z';
            boolean isLowercase = randomAscii >= 'a' && randomAscii <= 'z';
            if (isDigit || isUppercase || isLowercase) {
                randomChars[count] = (char) (randomAscii);
                ++count;
            }
        }
        return new String(randomChars);
    }

    public static void main(String[] args) {
        IdGenerator idGenerator = new RandomIdGenerator();
        System.out.println(idGenerator.generate());
    }
}