package br.com.challenge.domain.model;

import br.com.challenge.controller.enums.NotificationType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Subscription implements Serializable {

    public static final String ACTIVE = "ATIVO";
    public static final String INACTIVE = "INATIVO";
    @Id
    private String id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subscription",
            targetEntity = EventHistory.class, fetch = FetchType.EAGER)
    private List<EventHistory> events;

    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    public void initializeDates() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void updateUpdatedAt() {
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<EventHistory> getEvents() {
        return events;
    }

    public void setEvents(List<EventHistory> events) {
        this.events = events;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStatusByTypeNotification(NotificationType notificationType){
        if(!Objects.equals(notificationType,NotificationType.SUBSCRIPTION_CANCELED)){
            this.status = new Status(1L, ACTIVE);
        }else {
            this.status = new Status(2L, INACTIVE);
        }
    }

    public void addEvent(EventHistory event) {

        if (Objects.isNull(this.events) || this.events.isEmpty()) {
            this.events = new ArrayList<>();
        }

        this.events.add(event);

    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
