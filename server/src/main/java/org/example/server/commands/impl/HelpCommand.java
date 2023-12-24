package org.example.server.commands.impl;


import org.example.server.commands.Command;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HelpCommand implements Command {
    @Override
    public String execute(List<String> args) {
        if (args.size() != 0) {
            throw new IllegalArgumentException("Wrong number of parameters.");
        }
        return "Доступные команды:" + '\n' +
                "create $название $x $y $z - создать вектор" + '\n'
                + "read - вывести все векторы" + '\n'
                + "range $название - вывести длину вектора" + '\n'
                + "angle $вектор1 $вектор2 - найти угол между векторами" + '\n'
                + "product $типПроизведения $вектор1 $вектор2 - результат соответствующего произведения" + '\n'
                + "\t$типПроизведения может быть dot (скалярное) и cross (векторное" + '\n'
                + "exit - завершить программу" + '\n'
                + "save $имяФайла - сохранить коллекцию векторов в файл в формате \"$имяВектора: $x $y $z\"" + '\n'
                + "load $имяФайла - прочитать коллекцию векторов из файла (обязательный формат- \"$имяВектора: $x $y $z\")" + '\n';
    }
}
