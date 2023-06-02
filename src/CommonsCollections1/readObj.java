package CommonsCollections1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.Runtime;

// jdk<=8u71
//
public class readObj {
    public static void main(String[] args) throws IOException {
        FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/resources/commoncollections1.ser");
        ObjectInputStream obj_in = new ObjectInputStream(fi);
        try {
            obj_in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
