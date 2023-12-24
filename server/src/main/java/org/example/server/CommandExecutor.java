package org.example.server;

import org.example.server.commands.Command;
import org.example.server.commands.impl.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Service
public class CommandExecutor {
    private final HashMap<String, Command> commandList;

    public CommandExecutor(CreateCommand createCommand, ReadCommand readCommand, RangeCommand rangeCommand, AngleCommand angleCommand, ProductCommand productCommand, ExitCommand exitCommand, HelpCommand helpCommand, DeleteCommand deleteCommand) {
        commandList = new HashMap<>();
        commandList.put("create", createCommand);
        commandList.put("read", readCommand);
        commandList.put("range", rangeCommand);
        commandList.put("angle", angleCommand);
        commandList.put("product", productCommand);
        commandList.put("exit", exitCommand);
        commandList.put("help", helpCommand);
        commandList.put("delete", deleteCommand);
    }

    public String handleCommand(String command) {
        ArrayList<String> request = new ArrayList<>(Arrays.asList(command.split(" ")));
        String instruction = request.get(0);
        request.remove(0);
        try {
            return commandList.get(instruction).execute(request);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
