package PhotoSort;

import PhotoSort.Exceptions.FSException;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class FSConnector {
    /*
    Execute all tasks fo filesystem.
    parse, move, delete
     */
    public static boolean checkForExist(String filePath){
        return Files.exists(Paths.get(filePath));
    }

    public static void createPath(String path) throws FSException {
        try {
            Files.createDirectory(Paths.get(path));
        } catch (IOException e) {
            throw new FSException(e.getMessage());
        }

    }

    public static void walkFileTree(final String sourcePath, final DBConnector dbConnector) throws IOException {
        Files.walkFileTree(Paths.get(sourcePath), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                /*if(file.toString().endsWith(".rar")
                        || file.toString().endsWith(".rar")
                )
                    return FileVisitResult.CONTINUE;*/
                Date date = null;
                try {
                    date = checkMetadata(file.toString());
                    //System.out.println(date);
                    String hash = getHashOfFile(file);
                    dbConnector.addToDB(file.toString(), date, hash);
                } catch (ImageProcessingException e) {

                    //System.out.println(file.toString() + " not image file.");
                }

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });


    }

    private static String getHashOfFile(Path file) {

        try(InputStream is = Files.newInputStream(file)) {
            return DigestUtils.md5Hex(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Date checkMetadata(String file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(new FileInputStream(file));
        ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

        Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
        return date;
        }

    public static void moveFiles(String sourcePath, String targetPath) {
        //
    }

    public static String insertSep(String source){
        return source + getSeparator();
    }

    public static String getSeparator() {
        return File.separator;
    }
}




