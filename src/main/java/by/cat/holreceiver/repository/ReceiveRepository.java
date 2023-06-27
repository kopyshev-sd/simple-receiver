package by.cat.holreceiver.repository;

import by.cat.holreceiver.model.Receive;

import java.util.List;

public interface ReceiveRepository {

    Receive get(String uuid);

    List<Receive> getAll();

    void delete(String uuid);

    void save(Receive receive);

    void update(Receive receive, String uuid);

    void clear();
}
