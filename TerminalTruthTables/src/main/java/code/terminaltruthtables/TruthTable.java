package code.terminaltruthtables;

import java.util.ArrayList;

/**
 *
 * @author nicho
 */
public class TruthTable {
    public ArrayList<Variable> variables;
    public ArrayList<Expression> expressions;
    
    public TruthTable(ArrayList<Variable> variables, ArrayList<Expression> expressions) {
        setVariables(variables);
        setExpressions(expressions);
    }
    
    public TruthTable() {
        ArrayList<Variable> blankVariables = new ArrayList();
        ArrayList<Expression> blankExpressions = new ArrayList();
        setVariables(blankVariables);
        setExpressions(blankExpressions);
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Variable> variables) {
        this.variables = variables;
    }
    
    public void setVariable(Variable variable) {
        for (Variable var : variables) {
            if (var.getName().equals(variable.getName())) {
                throw new IllegalArgumentException();
            } else {
                variables.add(var);
            }
        }
    }
    
    public void clearVariables() {
        ArrayList<Variable> blankVariables = new ArrayList();
        setVariables(blankVariables);
    }

    public ArrayList<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(ArrayList<Expression> expressions) {
        this.expressions = expressions;
    }
    
    public void setExpression(Expression expression) {
        for (Expression exp : expressions) {
            if (exp.equals(expression)) {
                throw new IllegalArgumentException();
            } else {
                expressions.add(exp);
            }
        }
    }
    
    public void clearExpressions() {
        ArrayList<Expression> blankExpressions = new ArrayList();
        setExpressions(blankExpressions);
    }
    
    public void displayTruthTable() {
        
    }
}
