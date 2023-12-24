package org.example.server.commands.impl;

import org.example.server.VectorExecutor;
import org.example.server.commands.Command;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteCommand implements Command {
    public final VectorExecutor vectorExecutor;

    public DeleteCommand(VectorExecutor vectorExecutor) {
        this.vectorExecutor = vectorExecutor;
    }

    @Override
    public String execute(List<String> args) {
        if (args.size() != 1) {
            throw new IllegalArgumentException("Wrong number of parameters.");
        }
        if (vectorExecutor.findVector(args.get(0))) {
            vectorExecutor.deleteVector(args.get(0));
            return "Vector deleted successfully";
        } else {
            return "No vector with such name";
        }
    }
}
