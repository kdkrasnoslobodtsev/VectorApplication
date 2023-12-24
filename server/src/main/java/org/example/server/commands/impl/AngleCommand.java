package org.example.server.commands.impl;

import org.example.server.Vector3D;
import org.example.server.VectorExecutor;
import org.example.server.commands.Command;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AngleCommand implements Command {
    public final VectorExecutor vectorExecutor;

    public AngleCommand(VectorExecutor vectorExecutor) {
        this.vectorExecutor = vectorExecutor;
    }

    private Double angleBetween(String firstName, String secondName) {
        Vector3D first = vectorExecutor.getVector(firstName), second = vectorExecutor.getVector(secondName);
        double angle = Math.toDegrees(Math.acos((first.x() * second.x() + first.y() * second.y() + first.z() * second.z()) / (Math.sqrt(first.x() * first.x() + first.y() * first.y() + first.z() * first.z()) * Math.sqrt(second.x() * second.x() + second.y() * second.y() + second.z() * second.z()))));
        if (Double.isNaN(angle)) {
            if (first.x() * second.x() + first.y() * second.y() + first.z() * second.z() > 0) {
                return 0.0;
            } else {
                return 180.0;
            }
        } else {
            return angle;
        }
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() != 2) {
            throw new IllegalArgumentException("Wrong number of parameters.");
        }
        if (!vectorExecutor.findVector(args.get(0))) {
            throw new IllegalArgumentException("First vector does not exist");
        }
        if (!vectorExecutor.findVector(args.get(1))) {
            throw new IllegalArgumentException("Second vector does not exist");
        }
        Double angle = angleBetween(args.get(0), args.get(1));
        return "Angle between \"" + args.get(0) + "\" and \"" + args.get(1) + "\": " + angle;
    }

}
