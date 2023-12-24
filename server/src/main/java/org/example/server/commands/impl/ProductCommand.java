package org.example.server.commands.impl;

import org.example.server.Vector3D;
import org.example.server.VectorExecutor;
import org.example.server.commands.Command;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductCommand implements Command {
    public final VectorExecutor vectorExecutor;

    public ProductCommand(VectorExecutor vectorExecutor) {
        this.vectorExecutor = vectorExecutor;
    }

    public double dotProduct(String firstName, String secondName) {
        Vector3D first = vectorExecutor.getVector(firstName), second = vectorExecutor.getVector(secondName);
        return first.x() * second.x() + first.y() * second.y() + first.z() * second.z();
    }

    public Vector3D crossProduct(String firstName, String secondName) {
        Vector3D first = vectorExecutor.getVector(firstName), second = vectorExecutor.getVector(secondName);
        return new Vector3D("", first.y() * second.z() - second.y() * first.z(),
                second.x() * first.z() - first.x() * second.z(),
                first.x() * second.y() - second.x() * first.y());
    }

    public double tripleProduct(String firstName, String secondName, String thirdName) {
        Vector3D first = vectorExecutor.getVector(firstName);
        Vector3D res = crossProduct(secondName, thirdName);
        return first.x() * res.x() + first.y() * res.y() + first.z() * res.z();
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() < 3 || args.size() > 4) {
            throw new IllegalArgumentException("Wrong number of parameters.");
        }
        if (!vectorExecutor.findVector(args.get(1))) {
            throw new IllegalArgumentException("First vector does not exist.");
        }
        if (!vectorExecutor.findVector(args.get(2))) {
            throw new IllegalArgumentException("Second vector does not exist.");
        }

        if (args.get(0).equals("dot")) {
            double dot = dotProduct(args.get(1), args.get(2));
            return "Scalar product of \"" + args.get(1) + "\" and \"" + args.get(2) + "\": " + dot;
        } else if (args.get(0).equals("cross")) {
            Vector3D cross = crossProduct(args.get(1), args.get(2));
            return "Cross product of \"" + args.get(1) + "\" and \"" + args.get(2) + "\": (" + cross.x() + ", " + cross.y() + ", " + cross.z() + ")";
        } else if (args.get(0).equals("triple")) {
            if (args.size() != 4) {
                throw new IllegalArgumentException("Wrong number of parameters.");
            }
            double triple = tripleProduct(args.get(1), args.get(2), args.get(3));
            return "Triple product of \"" + args.get(1) + "\", \"" + args.get(2) + "\" and \"" + args.get(3) + "\": " + triple;
        }
        else {
            throw new IllegalArgumentException("Wrong type of product.");
        }
    }
}
