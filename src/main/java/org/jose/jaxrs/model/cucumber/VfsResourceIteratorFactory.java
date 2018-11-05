package org.org.jose.jaxrs.model.cucumber;

import java.io.File;
import java.net.URL;
import java.util.Iterator;

import java.net.URISyntaxException;

import cucumber.runtime.CucumberException;
import cucumber.runtime.io.ResourceIteratorFactory;
import cucumber.runtime.io.Resource;
import cucumber.runtime.io.FileResourceIterator;

/**
 * vfs:/
 */
public class VfsResourceIteratorFactory implements ResourceIteratorFactory {

    @Override
    public boolean isFactoryFor(URL url) {
        return ("vfs".equals(url.getProtocol()));
    }

    @Override
    public Iterator<Resource> createIterator(URL url, String path, String suffix) {
        String filePath;
        try {
          filePath = url.toURI().getSchemeSpecificPart();
        } catch (URISyntaxException e) {
          throw new CucumberException(e);
        }

        File file = new File(filePath);
        File rootDir = new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - path.length()));
        return FileResourceIterator.createClasspathFileResourceIterator(rootDir, file, suffix);
    }
}
