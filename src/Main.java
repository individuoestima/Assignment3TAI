import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        if(args.length == 0){
            System.out.println("Must have 2 parameteres, image and compressor");
            System.out.println("Compressors:  -gzip -bzip -zip -lz4");
        }

        Path test = Paths.get(args[0]);

        if(args[1].equals("-gzip")){
            System.out.println("Using GZIP");
            Gzip g = new Gzip(test);
            g.rankingGZIP();
        }
        if(args[1].equals("-bzip2")){
            System.out.println("Using BZIP2");
            Bzip b = new Bzip(test);
            b.rankingBZIP();
        }
        if (args[1].equals("-zip")){
            System.out.println("Using ZIP");
            Zip z = new Zip(test);
            z.rankingZIP();
        }
        if (args[1].equals("-lz4")){
            System.out.println("Using LZ4");
            LZ4 l = new LZ4(test);
            l.rankingLZ4();
        }


    }
}
