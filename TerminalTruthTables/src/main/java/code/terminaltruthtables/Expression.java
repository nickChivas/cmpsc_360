package code.terminaltruthtables;

import java.util.ArrayList;

/**
 *
 * @author nicho
 */
public class Expression {
    public ArrayList<Variable> variables;
    public String statement;

    public Expression(ArrayList<Variable> variables, String statement) {
        this.variables = variables;
        this.statement = statement;
    }
    
    public Expression() {
        ArrayList<Variable> blankVariables = new ArrayList();
        String statement = "";
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Variable> variables) {
        this.variables = variables;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
    
    public void evaluateExpression() {
        
    }
}
