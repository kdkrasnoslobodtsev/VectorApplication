package org.example.server.commands.impl;


import org.example.server.commands.Command;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExitCommand implements Command {
    @Override
    public String execute(List<String> args) {
        return "Exit";
    }
}
