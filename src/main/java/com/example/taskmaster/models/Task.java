package com.example.taskmaster.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Table(name = "task")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

    private boolean deleted;

    public Task(@NonNull Account account){
        this.account = account;
    }
}
