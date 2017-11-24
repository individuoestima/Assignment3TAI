import org.apache.tools.bzip2.CBZip2OutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Bzip {

    private Path file;

    public Bzip(Path f){
        file = f;
    }
    public int compressConcatenatedImagesBZIP(byte [] test,Path fileLocation) throws IOException {
        byte[] image = Files.readAllBytes(fileLocation);
        byte[] data = new byte[test.length + image.length];
        System.arraycopy(test, 0, data, 0, test.length);
        System.arraycopy(image, 0, data, test.length, image.length);
        ByteArrayOutputStream byteStream =
                new ByteArrayOutputStream(data.length);
        try {
            CBZip2OutputStream zipStream =
                    new CBZip2OutputStream(byteStream);
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

    public int compressImageBZIP(byte [] data) throws IOException {
        ByteArrayOutputStream byteStream =
                new ByteArrayOutputStream(data.length);
        try {
            CBZip2OutputStream zipStream =
                    new CBZip2OutputStream(byteStream);
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

    public float ncdBZIP(byte [] data,Path fileLocation2) throws IOException {
        byte [] image = Files.readAllBytes(fileLocation2);
        int data_size = compressImageBZIP(data);
        int image_size = compressImageBZIP(image);

        int top = compressConcatenatedImagesBZIP(data,fileLocation2) - Math.min(data_size,image_size);
        float ncd = (float)top / (Math.max(data_size,image_size));
        return ncd;
    }

    public void rankingBZIP() throws IOException {
        ArrayList<Float> values = new ArrayList<>();

        for(int i = 1 ; i<10;i++) {
            Path fileLocation = Paths.get("orl_faces/s0"+i+"/01.pgm");
            Path fileLocation2 = Paths.get("orl_faces/s0"+i+"/02.pgm");
            Path fileLocation3 = Paths.get("orl_faces/s0"+i+"/03.pgm");

            byte[] a = Files.readAllBytes(fileLocation);
            byte[] b = Files.readAllBytes(fileLocation2);
            byte[] c = Files.readAllBytes(fileLocation3);
            byte[] data = new byte[a.length + b.length + c.length];
            System.arraycopy(a, 0, data, 0, a.length);
            System.arraycopy(b, 0, data, a.length, b.length);
            System.arraycopy(c, 0, data, b.length, c.length);
            values.add(ncdBZIP(data, file));
        }
        for(int i = 10 ; i<=40;i++) {
            Path fileLocation = Paths.get("orl_faces/s"+i+"/01.pgm");
            Path fileLocation2 = Paths.get("orl_faces/s"+i+"/02.pgm");
            Path fileLocation3 = Paths.get("orl_faces/s"+i+"/03.pgm");

            byte[] a = Files.readAllBytes(fileLocation);
            byte[] b = Files.readAllBytes(fileLocation2);
            byte[] c = Files.readAllBytes(fileLocation3);
            byte[] data = new byte[a.length + b.length + c.length];
            System.arraycopy(a, 0, data, 0, a.length);
            System.arraycopy(b, 0, data, a.length, b.length);
            System.arraycopy(c, 0, data, b.length, c.length);
            values.add(ncdBZIP(data, file));
        }
        int index = 0;
        for (int i = 0 ;i<values.size();i++){
            if(values.get(i) < values.get(index)){
                index = i;
            }
        }
        System.out.println(index + " "+ values.get(index));
    }


}
