package Piya.repository;

import Piya.model.Incident;

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
