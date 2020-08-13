package encryptdecrypt;

public abstract class EncryptionAlgorithms {

    public enum MODE {enc, dec}

    public enum ALGORITHM {shift, unicode}

    public static EncryptionAlgorithms getAlgorithm(ALGORITHM algorithm) {
        switch (algorithm) {
            case shift:
                return new ShiftAlgorithm();
            case unicode:
                return new UnicodeAlgorithm();
            default:
                throw new IllegalArgumentException("no such algorithm: " + algorithm.name());
        }
    }

    public String encryptDecrypt(String data, long key, MODE mode) {
        switch (mode) {
            case enc:
                return encrypt(data, key);
            case dec:
                return decrypt(data, key);
            default:
                throw new IllegalArgumentException("no such mode: " + mode.name());
        }
    }

    public abstract String encrypt(String data, long key);

    public abstract String decrypt(String data, long key);
}
