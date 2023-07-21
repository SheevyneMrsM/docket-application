package com.nharire.docketapp.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "report")
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "crime_id", nullable = false)
    private Long crimeId;

    @Column(name = "report_Description", nullable = false)
    private String reportDescription;

    @Column(name = "reported", nullable = false)
    private LocalDateTime reported;

}
