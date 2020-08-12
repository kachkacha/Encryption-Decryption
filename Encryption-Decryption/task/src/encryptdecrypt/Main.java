package encryptdecrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private Crypto crypto;

    private boolean inputFromFile = true;
    private String inputFile;

    private boolean outputToFile = false;
    private String outputFile;

    public static void main(String[] args) {
        Main app = new Main();
        try {
            app.initializeCrypto(args);
            app.crypto.compute();
            app.outputResult(app.crypto.getDataAfter());
        } catch (Exception exception) {
            System.out.println("Error, " + exception.getMessage());
        }
    }

    public void initializeCrypto(String[] args) throws Exception {
        crypto = new Crypto();
        for (int i = 0; i < args.length - 1; i += 2) {
            String nextArg = args[i + 1];
            System.out.println(args[i] + " " + nextArg);
            switch (args[i]) {
                case "-mode":
                    crypto.setEncryptionMode(nextArg);
                    break;
                case "-key":
                    crypto.setKey(Long.parseLong(nextArg));
                    break;
                case "-data":
                    crypto.setDataBefore(nextArg);
                    inputFromFile = false;
                    break;
                case "-alg":
                    crypto.setAlgorithm(EncryptionAlgorithms.getAlgorithm(nextArg));
                    break;
                case "-in":
                    if (inputFromFile) inputFile = nextArg;
                    break;
                case "-out":
                    outputFile = nextArg;
                    outputToFile = true;
                    break;
                default:
                    throw new IllegalArgumentException("illegal argument: " + args[i]);
            }
        }
        if (inputFromFile) crypto.setDataBefore(getDataFromFile(inputFile));
    }

    private String getDataFromFile(String inputFile) throws IOException{
        return new String(Files.readAllBytes(Paths.get(inputFile)));
    }

    private void outputResult(String output) throws IOException{
        if (outputToFile) Files.write(Paths.get(outputFile), output.getBytes());
        else System.out.println(output);
    }

}
