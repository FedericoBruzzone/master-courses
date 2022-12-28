import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Encrypt implements IEncrypt {
    private static String keyPath = "key.public";

    public Encrypt() {
    }

    public long getKey() {
        try {
            Path path = Path.of(Encrypt.keyPath);
            String key = Files.readString(path);
            return Long.parseLong(key);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void setKey(long key) {
        try {
            Path path = Path.of(Encrypt.keyPath);
            Files.writeString(path, Long.toString(key));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] encrypt(byte[] bytecode) {
        System.out.println("Encrypting...");
        return bytecode;
    }
}
