import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Decrypt implements IDecrypt {

    public long getKey() {
        try {
            Path path = Path.of("key.private.txt");
            String res = Files.readString(path);
            return Long.parseLong(res);
        } catch (IOException e) { e.printStackTrace(); return 0; } 
        
    }
    public void setKey(long l) {}
    public byte[] decrypt(byte[] b) {
        long k = getKey();
        System.out.println("decrypt");
        return b;
    }
}
