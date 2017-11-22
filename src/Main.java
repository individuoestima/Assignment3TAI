import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

public class Main {

    public static int compressConcatenatedImages(Path fileLocation,Path fileLocation2) throws IOException {
        byte[] a = Files.readAllBytes(fileLocation);
        byte[] b = Files.readAllBytes(fileLocation2);
        byte[] data = new byte[a.length + b.length];
        System.arraycopy(a, 0, data, 0, a.length);
        System.arraycopy(b, 0, data, a.length, b.length);
        ByteArrayOutputStream byteStream =
                new ByteArrayOutputStream(data.length);
        try {
            GZIPOutputStream zipStream =
                    new GZIPOutputStream(byteStream);
            try {
                zipStream.write(data);
            } finally {
                zipStream.close();
            }
        } finally {
            byteStream.close();
        }
        byte[] compressedData = byteStream.toByteArray();
        return compressedData.length;
    }

    public static int compressImage(Path fileLocation) throws IOException {
        byte [] data = Files.readAllBytes(fileLocation);
        ByteArrayOutputStream byteStream =
                new ByteArrayOutputStream(data.length);
        try {
            GZIPOutputStream zipStream =
                    new GZIPOutputStream(byteStream);
            try {
                zipStream.write(data);
            } finally {
                zipStream.close();
            }
        } finally {
            byteStream.close();
        }
        byte[] compressedData = byteStream.toByteArray();
        return compressedData.length;

    }

    public static void main(String[] args) throws IOException {
        Path fileLocation = Paths.get("/home/kanto/Documents/TAI/Assignment3TAI/orl_faces/s01/01.pgm");
        System.out.println(compressConcatenatedImages(fileLocation,fileLocation));
        System.out.println(compressImage(fileLocation));
    }
}
