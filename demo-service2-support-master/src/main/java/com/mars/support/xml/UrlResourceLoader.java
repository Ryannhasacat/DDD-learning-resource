/*
 * Created by 2020-06-25 13:38:10 
 */
package com.mars.support.xml;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * load resources with the url.
 */
public class UrlResourceLoader 
					extends AbstractResourceLoader implements ResourceLoader {
	private static Log log = LogFactory.getLog(UrlResourceLoader.class);
	private Class<?> clazz = this.getClass();
	
	public UrlResourceLoader() { super(); }
	
	/**
	 * @param clazz the class help to load resource.
	 */
	public UrlResourceLoader(Class<?> clazz) {
		if(clazz!=null) {
			this.clazz = clazz;
		}
	}
	@Override
	public boolean loadResource(ResourceCallBack callback, String path) throws IOException {
		boolean success = false;
		PathMatchingResourcePatternResolver resolver = 
				new PathMatchingResourcePatternResolver(this.clazz.getClassLoader());
		Resource[] loaders = resolver.getResources(path);
		for (Resource loader : loaders) {
			printLog(loader);
			InputStream is = loader.getInputStream();
			if (is != null) {
				callback.apply(is);
				success = true;
			}
		}
		return success;
	}
	
	private void printLog(Resource resource) {
		try {
			log.debug(resource.getFile().getCanonicalPath());
		} catch (IOException e) {
			
		}
	}
}
