package serialize;

import java.io.*;

public class ReadTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User();
        user.setName("edisc");
        user.setAge(20);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("1.txt"));
        oos.writeObject(user);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("1.txt"));
        Object o = ois.readObject();
    }
}