package encryptdecrypt;

public abstract class EncryptionAlgorithms {

    public static EncryptionAlgorithms getAlgorithm(String algorithm) {
        switch (algorithm) {
            case "shift":
                return new ShiftAlgorithm();
            case "unicode":
                return new UnicodeAlgorithm();
            default:
                return null;
        }
    }

    public abstract String encrypt(String data, long key);

    public abstract String decrypt(String data, long key);
}
