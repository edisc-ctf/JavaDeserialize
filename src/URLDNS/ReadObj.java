package URLDNS;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadObj {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // System.out.println(System.getProperty("user.dir")+"/javasec-ysoserial/src/resources/4.ser");
        ObjectInputStream o = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir") + "/src/urldns/urldns.ser"));
        o.readObject();

    }
}
