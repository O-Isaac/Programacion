/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oisaac.proyectosdeflujos.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Isaac
 */
public class FileController {
    private Path path;
    private static final Logger LOG = Logger.getLogger(FileController.class.getName());

    public FileController(Path path) {
        this.path = path;
    }
    
    /**
     * Retornara todos los archivos del directorio
     * INFO: Este metodo omite los directorios
     * @return lista de todos los archivos
     */
    public List<String> getFiles() {
        try {
            return Files.list(path)
            .filter(file -> !Files.isDirectory(file))
            .map(file -> file.toString())
            .toList();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error al obtener los archivos del directorio {0}", path);
            LOG.log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList();
    }
    
    /**
     * Obtienes todos los directorios de la carpeta
     * @return lista de todos los directorios
     */
    public List<String> getDirectories() {
        try {
            return Files.list(path)
            .filter(file -> Files.isDirectory(file))
            .map(file -> file.toString())
            .toList();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error al obtener los archivos del directorio {0}", path);
            LOG.log(Level.SEVERE, null, ex);
        }
         
        return new ArrayList();
    }
    
    /**
     * Patro glob para obtener archivos del directorio.
     * @param glob
     * @return 
     */
    public List<Path> glob(String glob) {
        return glob(glob, false);
    }
    
    public List<Path> glob(String glob, boolean directories) {
        Path main = this.path;
        PathMatcher pathMatcher = FileSystems
            .getDefault()
            .getPathMatcher("glob:" + glob);
        
        List<Path> elements = new ArrayList<>();
        
	try {
            Files.walkFileTree(main, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    if (!directories && Files.isDirectory(path)) {
                            return FileVisitResult.CONTINUE;
                    }
                    
                    if (pathMatcher.matches(dir) && dir != main) {
                        elements.add(dir);
                    }
                    
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    if (pathMatcher.matches(path)) elements.add(path);
                    return FileVisitResult.CONTINUE;
                }
                
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    // Prodiamos capturar la excepcion que ha dado el archivo
                    
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error al obtener los archivos del directorio {0}", path);
            LOG.log(Level.SEVERE, null, ex);
        }
       
        return elements;
    }
    
    /**
     * List file in nested directory using glob function and file attribute view
     * @param globPattern 
     */
    public void listGlobDirectory(String globPattern) {
        List<Path> globFiles = glob(globPattern);
        
        for (Path file : globFiles) {
            try {
                BasicFileAttributeView attrview = Files.getFileAttributeView(path, BasicFileAttributeView.class);
                BasicFileAttributes attr = attrview.readAttributes();
                System.out.printf("[!] Name=%s, size=%s, absolute=", file.getFileName(), attr.size(), file.toAbsolutePath());
            }            
            catch (IOException ex) {
                LOG.log(Level.SEVERE, "Error al obtener los archivos del directorio {0}", path);
                LOG.log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    public void catFileDirectories(String globPattern, String outDirectory) {
        List<Path> globList = glob(globPattern);
            
        try (PrintWriter pw = new PrintWriter(outDirectory)) {
            pw.printf("+------------------------------------------------------------------------------------------------------+-----------------+--------------------------------+-------------------------------------------------------------------------%n");
            pw.printf("| %-100s | %-15s | %-30s | %-60s%n", "Nombre", "Tamaño", "Creación", "Path");
            pw.printf("+------------------------------------------------------------------------------------------------------+-----------------+--------------------------------+-------------------------------------------------------------------------%n");
            for (Path globPath : globList) {
                BasicFileAttributeView attrview = Files.getFileAttributeView(globPath, BasicFileAttributeView.class);
                BasicFileAttributes attr = attrview.readAttributes();
                File file = globPath.toFile();
                
                pw.printf("| %-100s | %-15d | %-30tc | %-60s%n",
                    file.getName(),
                    attr.size(),
                    Date.from(attr.creationTime().toInstant()),
                    file.getAbsolutePath());
            }
            
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, "Archivo no encontrado el log de {0}", path);
            LOG.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error al escribir el log de {0}", path);
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Elimina recursivamente todos los archivos del directorio!
     **/
    public void fullDelete() {
        List<Path> DirectoriesFiles = glob("*/**");
        
        try {
            for (Path dir : DirectoriesFiles) {
                Files.deleteIfExists(dir);
                LOG.log(Level.INFO, "Path {0} eliminado!", dir);
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error al obtener los archivos del directorio {0}", path);
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public Path getPath() {
        return path;
    }
}
