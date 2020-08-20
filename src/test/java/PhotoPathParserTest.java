import org.junit.Test;

import static org.junit.Assert.*;

public class PhotoPathParserTest {

    @Test
    public void main() {
        String[] args = new String[2];
        String os = System.getProperty("os.name");
        if (!os.equals("Linux")) {
            args[0] = "E:\\source";
            args[1] = "E:\\target";
        }
        else{
            String userHome = System.getProperty("user.home");
            args[0] = userHome + "/source";
            args[1] = userHome + "/target";
        }
        PhotoPathParser.main(args);

    }
}