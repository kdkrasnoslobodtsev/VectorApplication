package org.example.server.commands;

import java.util.List;

public interface Command {
    String execute(List<String> args);
}
