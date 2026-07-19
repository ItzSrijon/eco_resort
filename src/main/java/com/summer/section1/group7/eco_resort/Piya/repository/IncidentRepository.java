package com.summer.section1.group7.eco_resort.Piya.repository;

import com.summer.section1.group7.eco_resort.Piya.model.Incident;

import java.util.ArrayList;

public class IncidentRepository {

    private ArrayList<Incident> incidentList;
    public IncidentRepository() {

        incidentList = new ArrayList<>();

    }

    public void addIncident(Incident incident) {

        incidentList.add(incident);

    }

    public ArrayList<Incident> getIncidentList() {

        return incidentList;

    }

}
