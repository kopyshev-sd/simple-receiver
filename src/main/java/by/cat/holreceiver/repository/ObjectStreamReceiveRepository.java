package by.cat.holreceiver.repository;

import by.cat.holreceiver.exceptions.ReceiveStorageException;
import by.cat.holreceiver.model.Receive;

import java.io.*;

public class ObjectStreamReceiveRepository extends AbstractFileReceiveRepository {
    @Override
    protected void doWrite(Receive receive, File output) throws IOException {
        try (ObjectOutputStream bos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(output)))){
            bos.writeObject(receive);
        }
    }

    @Override
    protected Receive doRead(File input) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(input)))) {
           return (Receive) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new ReceiveStorageException(e);
        }
    }
}
