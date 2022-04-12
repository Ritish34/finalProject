package util;

import java.security.SecureRandom;
import java.util.stream.Collectors;

public class GeneratePassword {
	// Method to generate a random alphanumeric password of a specific length
    public String generateRandomPassword(int len, int randNumOrigin, int randNumBound)
    {
        SecureRandom random = new SecureRandom();
        return random.ints(len, randNumOrigin, randNumBound + 1)
                    .mapToObj(i -> String.valueOf((char)i))
                    .collect(Collectors.joining());
    }
}
