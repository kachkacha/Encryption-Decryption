package encryptdecrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import static encryptdecrypt.EncryptionAlgorithms.*;

public class Controller {

    private enum ARGUMENTS {
        data(""), mode("enc"), alg("shift"), key("0"), in(null), out(null);

        private final String defaultValue;

        ARGUMENTS(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getDefault() {
            return defaultValue;
        }
    }

    private Map <ARGUMENTS, String> arguments;

    private String resultData;

    public Controller(String[] args) throws Exception {
        initializeController(args);
        acquireData();
        processData();
        outputResult();
    }

    private void initializeController(String[] args) {
        arguments = new HashMap<>();

        for (ARGUMENTS argument : ARGUMENTS.values())
            arguments.put(argument, argument.getDefault());

        for (int i = 0; i < args.length; i += 2) {
            String key = args[i].substring(1);
            String value = args[i + 1];
            if (isValidArgument(key, value)) arguments.put(ARGUMENTS.valueOf(key), value);
        }
    }

    private boolean isValidArgument(String key, String value) {
        if (!arguments.containsKey(ARGUMENTS.valueOf(key))) throw new IllegalArgumentException("wrong argument: " + key);
        switch (ARGUMENTS.valueOf(key)) {
            case mode:
                if (Arrays.stream(MODE.values()).map(Enum::name).noneMatch(Predicate.isEqual(value)))
                    throw new IllegalArgumentException("wrong -mode: " + value);
                break;
            case alg:
                if (Arrays.stream(ALGORITHM.values()).map(Enum::name).noneMatch(Predicate.isEqual(value)))
                    throw new IllegalArgumentException("wrong -alg: " + value);
                break;
            case key:
                if (Long.parseLong(value) < 0)
                    throw new IllegalArgumentException("wrong -key(should be positive):" + value);
                break;
        }
        return true;
    }

    public void acquireData() throws IOException {
        String inputFile = arguments.get(ARGUMENTS.in);
        if (arguments.get(ARGUMENTS.data).equals(ARGUMENTS.data.getDefault()) && inputFile != null)
            arguments.put(ARGUMENTS.data, getDataFromFile(inputFile));
    }

    private String getDataFromFile(String inputFile) throws IOException{
        return new String(Files.readAllBytes(Paths.get(inputFile)));
    }

    public void processData() {
        String data = arguments.get(ARGUMENTS.data);
        long key = Long.parseLong(arguments.get(ARGUMENTS.key));

        ALGORITHM algorithm = ALGORITHM.valueOf(arguments.get(ARGUMENTS.alg));
        MODE mode = MODE.valueOf(arguments.get(ARGUMENTS.mode));

        resultData = getAlgorithm(algorithm).encryptDecrypt(data, key, mode);
    }

    private void outputResult() throws IOException {
        String outputFile = arguments.get(ARGUMENTS.out);
        if (outputFile != null) Files.write(Paths.get(outputFile), resultData.getBytes());
        else System.out.println(resultData);
    }
}
