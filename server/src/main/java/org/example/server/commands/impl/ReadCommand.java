package org.example.server.commands.impl;

import org.example.server.Vector3D;
import org.example.server.VectorExecutor;
import org.example.server.commands.Command;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReadCommand implements Command {
    public final VectorExecutor vectorExecutor;

    public ReadCommand(VectorExecutor vectorExecutor) {
        this.vectorExecutor = vectorExecutor;
    }

    @Override
    public String execute(List<String> args) {
        String response = "";
        response += "List of vectors:\n";
        var vectors = vectorExecutor.vectors();
        if (args.size() == 2) {
            int pageSize, pageNum;
            try {
                pageSize = Integer.parseInt(args.get(0));
                pageNum = Integer.parseInt(args.get(1));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Number of pages and size must be integer format.");
            }
            vectors = vectorExecutor.vectors().subList(pageSize * (pageNum - 1), Integer.min(pageSize * pageNum, vectorExecutor.vectorsCount()));
        } else if (args.size() > 0) {
            throw new IllegalArgumentException("Wrong number of parameters.");
        }
        int num = 1;
        for (Vector3D vector : vectors) {
            response += num + ") " + vector.name() + ": " + vector.x() + " " + vector.y() + " " + vector.z() + '\n';
            num++;
        }
        return response;
    }
}
