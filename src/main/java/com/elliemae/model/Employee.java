package com.elliemae.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

/** @author supraja_giddaluru */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder // Design Pattern implemented for the current project
@Table(name = "employee")
public class Employee {

  @ApiModelProperty(notes = "ID of the Employee")
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ApiModelProperty(notes = "firstname of the Employee")
  @Column(name = "fname")
  @NonNull
  private String fName;

  @ApiModelProperty(notes = "lastname of the Employee")
  @Column(name = "lname")
  @NonNull
  private String lName;

  @ApiModelProperty(notes = "empNumber of the Employee")
  @Column(name = "empno")
  @NonNull
  private int empNo;

  @ApiModelProperty(notes = "ManagerID of the Employee")
  @Column(name = "maniD")
  @NonNull
  private int mngId;
}
