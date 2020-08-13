package encryptdecrypt;

public class Main {

    public static void main(String[] args) {
        try {
            new Controller(args);
        } catch (Exception exception) {
            System.out.println("Error, " + exception.getMessage());
        }
    }
}
