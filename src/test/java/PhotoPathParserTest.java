import org.junit.Test;

import static org.junit.Assert.*;

public class PhotoPathParserTest {

    @Test
    public void main() {
        String[] args = new String[2];
        args[0] = "E:\\source";
        args[1] = "E:\\target";

        PhotoPathParser.main(args);

    }
}