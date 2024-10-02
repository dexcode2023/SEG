import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RecursiveDescentParser {
    private static BufferedReader reader;
    private static String currentToken;

    public static void main(String[] args) {
        if(args.length > 0){
            try {
            String filename = args[0];
            reader = new BufferedReader(new FileReader(filename));
            currentToken = getNextToken();
            program();
            if (currentToken.equals("$")) {
                System.out.println("SUCCESS");
            } else {
                throw new ParseException("Unexpected token at the end: " + currentToken);
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Parsing Error: " + e.getMessage());
        }
        }
        else{
            System.out.println("No filename specified.");
        }
    }

    private static String getNextToken() throws IOException {
        return reader.readLine();
    }

    private static void match(String expectedToken) throws ParseException {
        if (currentToken.equals(expectedToken)) {
            try {
                currentToken = getNextToken();
            } catch (IOException e) {
                throw new ParseException("Error reading next token.");
            }
        } else {
            throw new ParseException("Expected token: " + expectedToken + ", found: " + currentToken);
        }
    }


    private static void program() throws ParseException {
        match("{");
        statementList();
        
        
    }

    private static void statementList() throws ParseException {
        statement();
        statementListPrime();
    }

    private static void statementListPrime() throws ParseException {
        if (currentToken.equals(";")) {
            match(";");
            statement();
            statementListPrime();
        } else if(currentToken.equals("}")){
            return;
        }
    }

    private static void statement() throws ParseException {
        if (currentToken.equals("compute")) {
            match("compute");
            match(":");
            expression();
        } else if (currentToken.equals("call")) {
            match("call");
            match(":");
            procedureCall();
        } else if(currentToken.equals("}")){
            match("}");
        
        } else {
            throw new ParseException("Invalid statement." + currentToken);
        }
    }

    private static void procedureCall() throws ParseException {
        match("id");
        match("(");
        parameters();
        match(")");
    }

    private static void parameters() throws ParseException {
        factor();
        parametersPrime();
    }

    private static void parametersPrime() throws ParseException {
        if (currentToken.equals(",")) {
            match(",");
            factor();
            parametersPrime();
        }
    }

    private static void expression() throws ParseException {
        match("id");
        match("=");
        factor();
        expressionPrime();
    }

    private static void expressionPrime() throws ParseException {
        if (currentToken.equals("+") || currentToken.equals("-")) {
            match(currentToken);
            factor();
            expressionPrime();
        }
    }

    private static void factor() throws ParseException {
        if (currentToken.equals("id") || currentToken.equals("num")) {
            match(currentToken);
        } else {
            throw new ParseException("Invalid factor.");
        }
    }

    static class ParseException extends Exception {
        public ParseException(String message) {
            super(message);
        }
    }
}
