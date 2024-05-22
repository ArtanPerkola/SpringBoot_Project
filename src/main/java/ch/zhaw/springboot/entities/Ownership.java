package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Ownership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dateAcquired;

    @ManyToOne
    private User employee;

    @ManyToOne
    private Process process;

    @Autowired
    public Ownership(LocalDate dateAcquired, User employee, Process process) {
        this.dateAcquired = dateAcquired;
        this.employee = employee;
        this.process = process;
    }

    public Ownership() {
    }

    public int getId() {
        return id;
    }

    public LocalDate getDateAcquired() {
        return dateAcquired;
    }

    public User getEmployee() {
        return employee;
    }

    public Process getProcess() {
        return process;
    }

    public void setDateAcquired(LocalDate dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
}
