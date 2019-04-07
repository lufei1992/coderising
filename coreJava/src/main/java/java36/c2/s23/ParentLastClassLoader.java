package java36.c2.s23;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ParentLastClassLoader extends ClassLoader{
    private String[] jarFiles; //jar文件路径

    private Hashtable classes = new Hashtable(); //将定义的类缓存在hashtable里面

    public ParentLastClassLoader(ClassLoader parent, String[] paths)
    {
        super(parent);
        this.jarFiles = paths;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException
    {
        throw new ClassNotFoundException();
    }

    @Override
    protected synchronized Class<?> loadClass(String className, boolean resolve) throws ClassNotFoundException
    {
        try
        {
            byte classByte[];
            Class result = null;
            //先从缓存中查看是否已经加载该类
            result = (Class) classes.get(className);
            if (result != null) {
                return result;
            }
            //如果没找到该类,则直接从jar文件里面加载
            for(String jarFile: jarFiles){
                try {
                    JarFile jar = new JarFile(jarFile);
                    JarEntry entry = jar.getJarEntry(className.replace(".","/") + ".class");
                    InputStream is = jar.getInputStream(entry);
                    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                    int nextValue = is.read();
                    while (-1 != nextValue) {
                        byteStream.write(nextValue);
                        nextValue = is.read();
                    }
                    classByte = byteStream.toByteArray();
                    if(classes.get(className) == null){
                        result = defineClass(className, classByte, 0, classByte.length, null);
                        classes.put(className, result);
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            result = (Class) classes.get(className);
            if (result != null) {
                return result;
            }
            else{
                throw new ClassNotFoundException("Not found "+ className);
            }
        }
        catch( ClassNotFoundException e ){
            return super.loadClass(className, resolve);
        }
    }
}
