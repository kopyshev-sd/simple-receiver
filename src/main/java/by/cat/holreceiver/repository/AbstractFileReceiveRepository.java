package by.cat.holreceiver.repository;

import by.cat.holreceiver.Constants;
import by.cat.holreceiver.exceptions.ReceiveStorageException;
import by.cat.holreceiver.model.Receive;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractFileReceiveRepository extends AbstractRepository<File> {

    private final File STORAGE_DIR = new File(Constants.STORAGE_DIRECTORY);

    public AbstractFileReceiveRepository() {
        Objects.requireNonNull(STORAGE_DIR, "directory must not be null");
        if (!STORAGE_DIR.isDirectory()) {
            throw new IllegalArgumentException(STORAGE_DIR.getAbsolutePath() + " is not directory");
        }
        if (!STORAGE_DIR.canRead() || !STORAGE_DIR.canWrite()) {
            throw new IllegalArgumentException(STORAGE_DIR.getAbsolutePath() + " is not readable/writeable");
        }
    }

    @Override
    protected Receive doGet(File input) {
        try {
            return doRead(input);
        } catch (IOException e) {
            throw new ReceiveStorageException(e);
        }
    }

    @Override
    public List<Receive> doGetAll() {
        return getListFiles().stream().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public void doDelete(File file) {
        if (!file.delete()) {
            throw new ReceiveStorageException("Error deleting file: " + file.getAbsolutePath());
        }
    }

    @Override
    public void doSave(Receive receive, File output) {
        try {
            if (!output.createNewFile()) {
                throw new ReceiveStorageException(output.getAbsolutePath() + " is not created");
            }
            doUpdate(receive, output);
        } catch (IOException e) {
            throw new ReceiveStorageException(e);
        }
    }

    @Override
    public void doUpdate(Receive receive, File output) {
        try {
            doWrite(receive, output);
        } catch (IOException e) {
            throw new ReceiveStorageException(e);
        }
    }

    @Override
    public void clear() {
        List<File> files = getListFiles();
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(STORAGE_DIR, uuid);
    }

    @Override
    protected boolean isExist(File key) {
        return key.exists();
    }

    protected abstract void doWrite(Receive receive, File file) throws IOException;

    protected abstract Receive doRead(File file) throws IOException;

    private List<File> getListFiles() {
        File[] files = STORAGE_DIR.listFiles();
        if (files == null) {
            throw new ReceiveStorageException("I/O error while reading directory " + STORAGE_DIR.getAbsolutePath());
        }
        return Arrays.asList(files);
    }
}
