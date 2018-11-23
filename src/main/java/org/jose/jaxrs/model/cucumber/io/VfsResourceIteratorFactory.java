package org.jose.jaxrs.model.cucumber.io;

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

/**
 * ResourceIteratorFactory sobre VFS.
 * WildFly usa VFS en el despliegue de las aplicaciones. Ésta clase permite a
 * cucumber acceder a recuersos en VFS, para luego usar Iterators propios de
 * cucumber.
 * La Factoría es cargada mediante ServiceLoader:
 *   https://docs.oracle.com/javase/7/docs/api/java/util/ServiceLoader.html
 */

/**
 * vfs:/
 */
public class VfsResourceIteratorFactory implements ResourceIteratorFactory {

  @Override
  public boolean isFactoryFor(URL url) {
    return ("vfs".equals(url.getProtocol()));
  }

  public Iterator<Resource> createJarIterator(URL url, String path, String suffix) {
    String urlRootstr = url.toString().substring(0, url.toString().length() - path.length() -2);
    String jarFilestr = urlRootstr.substring(urlRootstr.lastIndexOf("/"), urlRootstr.length());

    try {
      URLConnection conn = new URL(urlRootstr).openConnection();
      VirtualFile vf = (VirtualFile)conn.getContent();
      File file = vf.getPhysicalFile().getParentFile();

      return new ZipResourceIterator(file.getAbsolutePath() + jarFilestr, path, suffix);
    }
    catch (Throwable e){
      e.printStackTrace();
      throw new CucumberException(e);
    }
  }

  public Iterator<Resource> createFileIterator(URL url, String path, String suffix) {
    String urlFilestr = url.toString();
    String urlRootstr = urlFilestr.substring(0, urlFilestr.length() - path.length() -2);

    try {
      URLConnection conn = new URL(urlFilestr).openConnection();
      VirtualFile vf = (VirtualFile)conn.getContent();
      File file = vf.getPhysicalFile();

      conn = new URL(urlRootstr).openConnection();
      vf = (VirtualFile)conn.getContent();
      File root = vf.getPhysicalFile();

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
