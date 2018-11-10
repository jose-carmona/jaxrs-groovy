package org.jose.jaxrs.model.cucumber;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import cucumber.runtime.CucumberException;
import cucumber.runtime.io.ResourceIteratorFactory;
import cucumber.runtime.io.Resource;
import cucumber.runtime.io.FileResourceIterator;
import cucumber.runtime.io.ZipResourceIterator;

import org.jboss.vfs.VirtualFile;

/*
  https://docs.oracle.com/javase/7/docs/api/java/util/ServiceLoader.html

  https://stackoverflow.com/questions/4899371/why-cant-i-open-a-jboss-vfs-url

  import org.jboss.vfs.*;

  String filename = ...;
  URLConnection conn = new URL("vfs:/...").openConnection();
  VirtualFile vf = (VirtualFile)conn.getContent();
  File contentsFile = vf.getPhysicalFile();
  File dir = contentsFile.getParentFile();
  File physicalFile = new File(dir, filename);
  InputStream is = new FileInputStream(physicalFile);
*/

/**
 * vfs:/
 */
public class VfsResourceIteratorFactory implements ResourceIteratorFactory {

  @Override
  public boolean isFactoryFor(URL url) {
    System.out.println(url.getProtocol());
    return ("vfs".equals(url.getProtocol()));
  }

  public Iterator<Resource> createJarIterator(URL url, String path, String suffix) {

    URLConnection conn;
    VirtualFile vf;
    File file;

    String urlstr = url.toString().substring(0, url.toString().length() - path.length() -2);
    String jarPath = urlstr.substring(urlstr.lastIndexOf("/"), urlstr.length());

    System.out.println("createJarIterator");
    System.out.println(url);
    System.out.println(path);
    System.out.println(suffix);
    System.out.println(urlstr);
    System.out.println(jarPath);

    try {
      conn = new URL(url.toString().substring(0, url.toString().length() - path.length() -2)).openConnection();
      vf = (VirtualFile)conn.getContent();

      file = vf.getPhysicalFile().getParentFile();
      System.out.println(file);

      return new ZipResourceIterator(file.getAbsolutePath() + jarPath, path, suffix);
    }
    catch (Throwable e){
      e.printStackTrace();
      throw new CucumberException(e);
    }
  }

  public Iterator<Resource> createFileIterator(URL url, String path, String suffix) {
    URLConnection conn;
    VirtualFile vf;
    File file;
    File root;

    String urlstrFile = url.toString();
    String urlstrRoot = url.toString().substring(0, url.toString().length() - path.length() -2);

    System.out.println("createFileIterator");
    System.out.println(url);
    System.out.println(path);
    System.out.println(suffix);
    System.out.println(urlstrFile);
    System.out.println(urlstrRoot);

    try {
      conn = new URL(urlstrFile).openConnection();
      vf = (VirtualFile)conn.getContent();

      file = vf.getPhysicalFile();
      System.out.println(file);

      conn = new URL(urlstrRoot).openConnection();
      vf = (VirtualFile)conn.getContent();

      root = vf.getPhysicalFile();
      System.out.println(root);

      /* (File root, File file, String suffix) */
      return FileResourceIterator.createClasspathFileResourceIterator(root, file, suffix);
    }
    catch (Throwable e) {
      e.printStackTrace();
      throw new CucumberException(e);
    }
  }

  @Override
  public Iterator<Resource> createIterator(URL url, String path, String suffix) {
    if (url.toString().lastIndexOf(".jar") > 0 ){
      return createJarIterator(url, path, suffix);
    } else {
      return createFileIterator(url, path, suffix);
    }
  }
}
