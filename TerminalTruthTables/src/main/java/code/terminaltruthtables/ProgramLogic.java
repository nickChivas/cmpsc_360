package code.terminaltruthtables;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nicho
 */
public class ProgramLogic {

    public Scanner scanner;
    public TruthTable truthTable;

    public ProgramLogic(Scanner scanner, TruthTable truthTable) {
        setScanner(scanner);
        setTruthTable(truthTable);
    }

    public ProgramLogic(Scanner scanner) {
        setScanner(scanner);
        TruthTable blankTruthTable = new TruthTable();
    }

    public void start() {
        System.out.println("Terminal Truth Tables\n--------------------");
        boolean userFinished = false;
        while (!userFinished) {
            TruthTable thisTruthTable = new TruthTable();
            thisTruthTable.displayTruthTable();
            String input = scanner.nextLine();
            String[] inputToArray = input.split(" ");
            String command = inputToArray[0].toUpperCase();
            if (command.equals("HELP")) {
                switch (inputToArray[1].toUpperCase()) {
                    case "DEFINE" -> {
                    }
                    case "EVALUATE" -> {
                    }
                    case "RESET" -> {
                    }
                    case "SAVE" -> {
                    }
                    case "LOAD" -> {
                    }
                    case "EXIT" -> {
                    }
                    default -> printHelpScreen();
                }
            }
            if (command.equals("DEFINE")) {
                if (inputToArray.length > 2) {
                    throw new IllegalArgumentException("Please enter a valid variable name");
                }
                Variable newVariable = new Variable(inputToArray[1]);
                truthTable.setVariable(newVariable);
            }
            if (command.equals("EVALUATE")) {
                Expression newExpression = new Expression();
                ArrayList<Variable> variables = new ArrayList();
                String statement = "";
                for (int i = 1; i < inputToArray.length; i++) {
                    if (!inputToArray[i].toUpperCase().equals("AND") || !inputToArray[i].toUpperCase().equals("OR") || !inputToArray[i].toUpperCase().equals("NOT")) {
                        boolean validVariable = false;
                        for (Variable variable : truthTable.getVariables()) {
                            if (variable.getName().equals(inputToArray[i])) {
                                validVariable = true;
                            }
                        }
                        if (validVariable = false) {
                            throw new IllegalArgumentException("Please enter a valid logical expression");
                        } else {
                            Variable newVariable = new Variable(inputToArray[i]);
                            variables.add(newVariable);
                        }
                        statement += inputToArray[i] + " ";
                    } else {
                        statement += inputToArray[i] + " ";
                    }
                }
                newExpression.setVariables(variables);
                newExpression.setStatement(statement);
            }
            if (command.equals("RESET")) {
                truthTable.clearVariables();
                truthTable.clearExpressions();
            }
            if (command.equals("SAVE")) {

            }
            if (command.equals("LOAD")) {

            }
            if (command.equals("EXIT")) {
                userFinished = true;
            } else {
                System.out.println("Please enter a valid command.");
            }
        }
    }

    public void printHelpScreen() {
        System.out.println("""
                           0) Enter "HELP" before any of the commands below to get more information
                           1) Define your variables with "DEFINE"
                           2) Enter a predicate with "EVALUATE"
                           3) Reset your truth table with "RESET"
                           4) Save your truth table with "SAVE"
                           5) Load a previous truth table with "LOAD"
                           6) Exit the program with "EXIT"
                           """);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public TruthTable getTruthTable() {
        return truthTable;
    }

    public void setTruthTable(TruthTable truthTable) {
        this.truthTable = truthTable;
    }

}
