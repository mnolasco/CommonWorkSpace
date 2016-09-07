package mnc.comun.pruebas;

import java.io.File;
import java.io.IOException;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Paths;

import java.nio.file.attribute.BasicFileAttributes;

import java.nio.file.attribute.FileTime;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import mnc.comun.librerias.archivos.ZipUtil;

public class PruebaZip {
  public static void main(String[] args) {
    final List<Path> files = new ArrayList<>();
    Path path = Paths.get("\\\\10.41.1.17\\docs");
    long totalSize=0L;
    try {
      DirectoryStream<Path> stream;
      stream = Files.newDirectoryStream(path);
      for (Path entry : stream) {
        BasicFileAttributes attr;
        try {
          attr = Files.readAttributes(entry, BasicFileAttributes.class);
          FileTime ft =  attr.creationTime();
          DateFormat df = new SimpleDateFormat("yyyymmdd");
          String dateCreated = df.format(ft.toMillis());
          if(dateCreated.equals("20140401")){
            totalSize = totalSize + attr.size()/1024; //KB
            System.out.println(totalSize);
          }
        } catch (IOException e) {
          System.out.println("oops un error! " + e.getMessage());
        }
      }
      stream.close();
      System.out.println(totalSize);
    } catch (IOException e) {
      e.printStackTrace();
    }


    //Path path = Paths.get("\\10.41.1.17\\docs");

    /*List<String> rutaArchivos = new ArrayList<>();
    rutaArchivos.add("E:/prueba/archivos/prueba.txt");
    rutaArchivos.add("E:/plantilla_ecofriendly2.pptx");
    try {
      //ZipUtil.empaquetar("E:/java/demo/prueba.zip", rutaArchivos);
      ZipUtil.desempaquetar("E:/java/demo/prueba.zip");
    } catch (IOException e) {
      e.printStackTrace();
    }*/
  }
}
