/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempdel;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Art
 */
public class TempDel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Путь очищаемой директории:");
        String tempPath = reader.readLine();
        Path path = Paths.get(tempPath);
                List<String> dirsList=new ArrayList<>();
                List<String> fileList=new ArrayList<>();
                
        try {
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                File files = new File(dir.toString());
                File[] fLists = files.listFiles();
                
            for (File f : fLists) {
               
               if (Files.isRegularFile(f.toPath()) && f != null) {
               fileList.add(f.getName());
               f.delete();
               }     
            }

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                File dirs = new File(dir.toString());
                File[] dirLists = dirs.listFiles();
                for (File d : dirLists) {
                    if(Files.isDirectory(d.toPath()) && d !=null) {
                        dirsList.add(d.toString());
                        d.delete();
                    }
                }
                return FileVisitResult.CONTINUE;
            }
            

        });
    } catch(Exception e) {
    }
    System.out.println("Удалены файлы:");
                for (String files : fileList){
                System.out.println(files);
                }
                System.out.println("Удалены папки:");
                    for (String dirs : dirsList){
                    System.out.println(dirs);
                    }
    }
    
}
