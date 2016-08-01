package pvt.mke.comun.librerias.archivos;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import java.net.URI;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class ZipUtil extends ArchivoUtil {

  public static void empaquetar(String ruta, List<String> rutaArchivos) throws IOException {
    String rutaCarpeta = getRutaCarpeta(ruta);
    crearCarpeta(rutaCarpeta);
    agregarArchivos(ruta, rutaArchivos);
  }
  
  public static void desempaquetar(String ruta) throws IOException {
    try (FileSystem zipFileSystem = getReferenciaZip(ruta, false);) {
      final Path root = zipFileSystem.getPath("/");
      final String carpetaDestino = getRutaCarpeta(ruta);
      Files.walkFileTree(root, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file,
                BasicFileAttributes attrs) throws IOException {
              
              final Path destFile = Paths.get(carpetaDestino,file.toString());
              Files.copy(file, destFile, StandardCopyOption.REPLACE_EXISTING);
              return FileVisitResult.CONTINUE;
            }
       
            @Override
            public FileVisitResult preVisitDirectory(Path dir,
                BasicFileAttributes attrs) throws IOException {
              final Path dirToCreate = Paths.get(carpetaDestino,dir.toString());
              if(Files.notExists(dirToCreate)){
                Files.createDirectory(dirToCreate);
              }
              return FileVisitResult.CONTINUE;
            }
          });
    }
  }

  private static void agregarArchivos(String ruta, List<String> rutaArchivos) throws IOException {
    try (FileSystem zipFileSystem = getReferenciaZip(ruta, true);) {
      for (String archivo : rutaArchivos) {
        Path pathArchivo = Paths.get(archivo);
        Path pathInZip = zipFileSystem.getPath(getNombre(archivo));
        Files.copy(pathArchivo, pathInZip, StandardCopyOption.REPLACE_EXISTING);
      }
    }
  }

  private static FileSystem getReferenciaZip(String ruta, boolean nuevo) throws IOException {
    Map<String, String> env = new HashMap<>();
    if(nuevo){
      env.put("create", "true");
    }
    env.put("encoding", "UTF-8");
    
    URI uri = null;
    String sistemaOperativo = System.getProperty("os.name" );
    if(sistemaOperativo.contains("indow")){
      uri = URI.create("jar:file:///" + ruta);
    }else{ //linux
      uri = URI.create("jar:file:" + ruta);
    }
    return FileSystems.newFileSystem(uri, env);
  }
}