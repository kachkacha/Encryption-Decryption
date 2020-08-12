package encryptdecrypt;

import org.jetbrains.annotations.NotNull;

public class Crypto {

    private enum ENCRYPTION_MODE {enc, dec;

        public boolean isEncryption() {
            return this == enc;
        }
    }

    private String dataBefore = "";

    private String dataAfter = "";

    private ENCRYPTION_MODE encryptionMode = ENCRYPTION_MODE.enc;

    private long key = 0;

    private EncryptionAlgorithms algorithm = new ShiftAlgorithm();

    public void compute() {
        if (encryptionMode.isEncryption()) dataAfter = algorithm.encrypt(dataBefore, key);
        else dataAfter = algorithm.decrypt(dataBefore, key);
    }

    public void setDataBefore(@NotNull String data) {
        dataBefore = data;
    }

    public String getDataBefore() {
        return dataBefore;
    }

    public String getDataAfter() {
        return dataAfter;
    }

    public void setEncryptionMode(String encryptionMode) throws IllegalArgumentException {
        this.encryptionMode = ENCRYPTION_MODE.valueOf(encryptionMode);
    }

    public String getEncryptionMode() {
        return encryptionMode.name();
    }

    public void setKey(long key) {
        if (key < 0) throw new IllegalArgumentException("negative value for key");
        this.key = key;
    }

    public long getKey() {
        return key;
    }

    public void setAlgorithm(@NotNull EncryptionAlgorithms algorithm) {
        this.algorithm = algorithm;
    }

    public EncryptionAlgorithms getAlgorithm() {
        return algorithm;
    }
}
