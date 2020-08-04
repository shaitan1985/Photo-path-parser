package PhotoSort;

import PhotoSort.Exceptions.FSException;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.Assert.*;

public class FSConnectorTest {

    @Test
    public void checkForExist() {
        String path = "E:\\source";
        assertTrue(FSConnector.checkForExist(path));
    }

    @Test
    public void createPath() throws FSException, IOException {
        String tempPath = "E:\\" + UUID.randomUUID().toString();
        FSConnector.createPath(tempPath);
        assertTrue(Files.exists(Paths.get(tempPath)));
        Files.delete(Paths.get(tempPath));
    }
}