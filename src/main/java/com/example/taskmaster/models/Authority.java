package com.example.taskmaster.models;

import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "AUTHORITY")
public class Authority implements Serializable {

    @Id
    @Column(length = 16)
    private String name;

    @Override
    public String toString(){
        return "Authority{" +
                "name='" + name + "'" +
                "}";
    }
}
