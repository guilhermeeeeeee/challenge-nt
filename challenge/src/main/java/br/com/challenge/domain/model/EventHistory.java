package br.com.challenge.domain.model;

import br.com.challenge.controller.enums.NotificationType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class EventHistory implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @Column(nullable = false)
    private String type;

    @Column
    private LocalDateTime createdAt;

    public EventHistory(Subscription subscription, NotificationType status) {
        this.subscription = subscription;
        this.type = status.toString();
    }

    public EventHistory(){}

    @PrePersist
    public void initializeDates() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        type = type;
    }
}
