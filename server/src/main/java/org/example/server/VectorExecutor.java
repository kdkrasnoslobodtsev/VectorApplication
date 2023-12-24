package org.example.server;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class VectorExecutor {
    private final VectorCollection vectorCollection;

    public VectorExecutor(@Qualifier("postgresConnection") VectorCollection vectorCollection) {
        this.vectorCollection = vectorCollection;
        vectorCollection.setConnection();
    }

    public boolean findVector(String name) {
        return vectorCollection.hasVector(name);
    }

    public void createVector(String name, Double x, Double y, Double z) {
        vectorCollection.addVector(name, x, y, z);
    }

    public Vector3D getVector(String name) {
        return vectorCollection.getVector(name);
    }

    public Integer vectorsCount() {
        return vectorCollection.size();
    }

    public List<Vector3D> vectors() {
        return vectorCollection.values();
    }

    public void deleteVector(String name) {
        vectorCollection.deleteVector(name);
    }
}
