package com.hackathon_hub.hackathon_hub_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "task_progresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean isComplete;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
