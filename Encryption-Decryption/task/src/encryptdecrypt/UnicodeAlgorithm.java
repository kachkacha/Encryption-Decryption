package encryptdecrypt;

public class UnicodeAlgorithm extends EncryptionAlgorithms{
    @Override
    public String encrypt(String data, long key) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char character : data.toCharArray())
            stringBuilder.append((char) (character + key));
        return stringBuilder.toString();
    }

    @Override
    public String decrypt(String data, long key) {
        return encrypt(data, -key);
    }
}
