package com.triangle.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

//@Table("")
@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private  String title;
    @ManyToMany
    private List<Question> questions;
}
