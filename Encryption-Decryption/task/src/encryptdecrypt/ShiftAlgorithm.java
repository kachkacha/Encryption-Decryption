package encryptdecrypt;

public class ShiftAlgorithm extends EncryptionAlgorithms{

    public static final int LETTER_COUNT = 26;

    @Override
    public String encrypt(String data, long key) {
        key %= LETTER_COUNT;
        StringBuilder stringBuilder = new StringBuilder();
        for (char character : data.toCharArray())
            if (Character.isLowerCase(character)) stringBuilder.append((char) ('a' + (character - 'a' + key) % LETTER_COUNT));
            else if (Character.isUpperCase(character)) stringBuilder.append((char) ('A' + (character - 'A' + key) % LETTER_COUNT));
            else stringBuilder.append(character);
        return stringBuilder.toString();
    }

    @Override
    public String decrypt(String data, long key) {
        return encrypt(data, LETTER_COUNT - (key %= LETTER_COUNT));
    }
}
