package com.nharire.docketapp.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "crime_register")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrimeRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private  Long crimeId;

    @ManyToOne
    @JoinColumn(name = "head_id_id")
    private Officer headId;

    @Column(name ="date_of_offense")
    private Date  dateOfOffense;

    @Column(name = "under_section")
    private String underSection;

    @Column(name = "date_of_report")
    private Date  dateOfReport;


    @Column(name = "case_status")
    private String  caseStatus;

    @Column(name = "time_of_offense")
    private Time  timeOfOffense;

    @Column(name = "description")
    private String  description;

    @OneToMany
    @JoinColumn(name = "accused_id_national_id")
    private List<Accused> accusedList;

    @ManyToOne
    @JoinColumn(name = "complainer_national_id")
    private Complainant  complainer;

    @ManyToOne
    @JoinColumn(name = "police_station_id")
    private PoliceStation  policeStation;

    @OneToMany
    @JoinColumn(name = "report_id")
    private List<Report> reports;

    @OneToMany
    @JoinColumn(name = "witness")
    private List<Witness> witness;




}
