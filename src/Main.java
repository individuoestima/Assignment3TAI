import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        if(args.length == 0){
            System.out.println("Must have 2 parameteres, image and compressor");
            System.out.println("Compressors:  -gzip -bzip -zip -lz4");
            System.exit(1);
        }

        if(!new File(("NCCD.sh")).exists() || !(new File("ctx1")).exists() || !(new File("ImgCondComp")).exists()){
            System.out.println("Ficheiros do NCCD não encontrados");
            System.exit(1);
        }

        if(!(new File("orl_faces")).exists()){
            System.out.println("Dataset não encontrado!");
            System.exit(1);
        }

        if(!(new File(args[0])).exists()){
            System.out.println("Ficheiro de input não encontrado");
            System.exit(1);
        }

        Path test = Paths.get(args[0]);

        if(args[1].equals("-gzip")){
            System.out.println("Using GZIP");
            Gzip g = new Gzip(test);
            System.out.println("This picture is most likely to be from subject "+g.rankingGZIP());
        }
        else if(args[1].equals("-bzip")){
            System.out.println("Using BZIP2");
            Bzip b = new Bzip(test);
            System.out.println("This picture is most likely to be from subject "+b.rankingBZIP());
        }
        else if (args[1].equals("-zip")){
            System.out.println("Using ZIP");
            Zip z = new Zip(test);
            System.out.println("This picture is most likely to be from subject "+z.rankingZIP());
        }
        else if (args[1].equals("-lz4")){
            System.out.println("Using LZ4");
            LZ4 l = new LZ4(test);
            System.out.println("This picture is most likely to be from subject "+l.rankingLZ4());
        }
        else{
            System.out.println("Compressor não existe!");
            System.exit(1);
        }


    }
}
