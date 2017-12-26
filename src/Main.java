import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        if(args.length == 0){
            System.out.println("Must have 2 parameteres, image and compressor");
            System.out.println("Compressors:    -gzip -bzip");
        }

        Path test = Paths.get(args[0]);

        if(args[1].equals("-gzip")){
            Gzip g = new Gzip(test);
            g.rankingGZIP();
        }
        if(args[1].equals("-bzip")){
            Bzip b = new Bzip(test);
            b.rankingBZIP();
        }

    }
}
