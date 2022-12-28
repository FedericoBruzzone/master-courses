import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Decrypt implements IDecrypt {
    private static String keyPath = "key.private";

    public Decrypt() {
    }

    public long getKey() {
        try {
            Path path = Path.of(Decrypt.keyPath);
            String key = Files.readString(path);
            return Long.parseLong(key);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void setKey(long key) {
        try {
            Path path = Path.of(Decrypt.keyPath);
            Files.writeString(path, Long.toString(key));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] decrypt(byte[] bytecode) {
        System.out.println("Decrypting...");
        return bytecode;
    }
}
