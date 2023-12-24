package org.example.server.commands.impl;


import org.example.server.Vector3D;
import org.example.server.VectorExecutor;
import org.example.server.commands.Command;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RangeCommand implements Command {
    public final VectorExecutor vectorExecutor;

    public RangeCommand(VectorExecutor vectorExecutor) {
        this.vectorExecutor = vectorExecutor;
    }

    public Double range(String name) {
        Vector3D vec = vectorExecutor.getVector(name);
        return Math.sqrt(vec.x() * vec.x() + vec.y() * vec.y() + vec.z() + vec.z());
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Wrong number of parameters.");
        }
        if (!vectorExecutor.findVector(args.get(0))) {
            throw new IllegalArgumentException("Vector does not exist.");
        }
        Double range = range(args.get(0));
        return "Range of vector \"" + args.get(0) + "\": " + range;
    }
}
