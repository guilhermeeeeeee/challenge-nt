package br.com.challenge.domain.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Status implements Serializable {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    public Status(){}
    public Status(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
