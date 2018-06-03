package com.fitness.fitness_backend.entity;

import com.google.protobuf.GeneratedMessageV3;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Lev
 * 10.10.2016
 */

@Entity(name = "client")
public class Client implements Model {

    @Id
    private UUID id;

    private String name;

    @Column(name = "is_archived")
    private Boolean isArchived;

    @Column(name = "updated_at")
    private Long changed;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public long getChanged() {
        return changed;
    }

    @Override
    public void setChanged(long l) {
        this.changed = changed;
    }
}
