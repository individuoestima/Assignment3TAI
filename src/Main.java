import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        Path test = Paths.get("/home/kanto/Documents/TAI/Assignment3TAI/orl_faces/s01/10.pgm");
        Gzip g = new Gzip(test);
        Bzip b = new Bzip(test);
        b.rankingBZIP();
        g.rankingGZIP();


    }
}
