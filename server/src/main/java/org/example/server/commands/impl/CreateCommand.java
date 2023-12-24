package org.example.server.commands.impl;



import org.example.server.VectorExecutor;
import org.example.server.commands.Command;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateCommand implements Command {
    public final VectorExecutor vectorExecutor;

    public CreateCommand(VectorExecutor vectorExecutor) {
        this.vectorExecutor = vectorExecutor;
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() != 4) {
            throw new IllegalArgumentException("Wrong number of parameters.");
        }
        if (vectorExecutor.findVector(args.get(0))) {
            throw new IllegalArgumentException("Name of vector already exists.");
        }
        double x, y, z;
        try {
            x = Double.parseDouble(args.get(1));
            y = Double.parseDouble(args.get(2));
            z = Double.parseDouble(args.get(3));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Coordinates must be double format.");
        }
        vectorExecutor.createVector(args.get(0), x, y, z);
        return "Vector was successfully created.";
    }
}
