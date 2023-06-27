package by.cat.holreceiver.repository;

import by.cat.holreceiver.exceptions.ReceiveStorageException;
import by.cat.holreceiver.model.Receive;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractRepository<SK> implements ReceiveRepository {

    public static final Comparator<Receive> RECEIVE_COMPARATOR = Comparator.comparing(Receive::getReceivedAt, Collections.reverseOrder());

    @Override
    public final Receive get(String uuid) {
        SK key = getExistingSearchKey(uuid);
        return doGet(key);
    }

    @Override
    public final List<Receive> getAll() {
        List<Receive> receives = doGetAll();
        receives.sort(RECEIVE_COMPARATOR);
        return receives;
    }

    @Override
    public final void delete(String uuid) {
        SK key = getExistingSearchKey(uuid);
        doDelete(key);
    }

    @Override
    public final void save(Receive receive) {
        SK key = getNotExistingSearchKey(receive.getUuid());
        doSave(receive, key);
    }

    @Override
    public final void update(Receive receive, String uuid) {
        SK key = getExistingSearchKey(uuid);
        doUpdate(receive, key);
    }

    private SK getExistingSearchKey(String uuid) {
        SK key = getSearchKey(uuid);
        if (isExist(key)) {
            return key;
        }
        throw new ReceiveStorageException("Receive " + uuid + " not exist");
    }

    private SK getNotExistingSearchKey(String uuid) {
        SK key = getSearchKey(uuid);
        if (!isExist(key)) {
            return key;
        }
        throw new ReceiveStorageException("Receive " + uuid + " is already exist");
    }

    protected abstract boolean isExist(SK key);

    protected abstract SK getSearchKey(String uuid);

    protected abstract Receive doGet(SK key);

    protected abstract List<Receive> doGetAll();

    protected abstract void doSave(Receive receive, SK key);

    protected abstract void doDelete(SK key);

    protected abstract void doUpdate(Receive receive, SK key);
}
