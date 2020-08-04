package PhotoSort;

import PhotoSort.Exceptions.FSException;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.HashSet;

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

    public static void walkFileTree(String sourcePath, String targetPath) throws IOException {
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
                try {
                    checkMetadata(file.toString());
                    System.out.println("**********");
                    System.out.println("**********");
                    System.out.println("**********");
                } catch (ImageProcessingException e) {
                    System.out.println(file.toString() + " not image file.");
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

    public static void checkMetadata(String file) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(new FileInputStream(file));
        ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

        Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
        System.out.println(date.toString());
        }
    }




