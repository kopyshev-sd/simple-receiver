package by.cat.holreceiver.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Receive implements Serializable {
    private static final long serialVersionUid = 1L;

    private String uuid;

    private String name;

    private String payload;

    private LocalDateTime receivedAt;

    public Receive() {
    }

    public Receive(String name, String payload) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.payload = payload;
        this.receivedAt = LocalDateTime.now();
    }

    public Receive(String uuid, String name, String payload, LocalDateTime receivedAt) {
        this.uuid = uuid;
        this.name = name;
        this.payload = payload;
        this.receivedAt = receivedAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receive receive = (Receive) o;
        return Objects.equals(name, receive.name) && Objects.equals(payload, receive.payload) && Objects.equals(receivedAt, receive.receivedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, payload, receivedAt);
    }

    @Override
    public String toString() {
        return "Receive{" +
                "name='" + name + '\'' +
                ", payload='" + payload + '\'' +
                ", receivedAt=" + receivedAt +
                '}';
    }
}
