package tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

//java boilerplate classes generator we need for the AST 
public class GenerateAST {
    public static void main(String[] args) throws IOException{
        if(args.length != 1){
            System.err.println("Usage: java GenerateAST <input_file>");
            System.exit(64);
        }
        String outputDir = args[0];
        
       defineAST(outputDir, "Expr" , Arrays.asList(
            "Binary : Expr left, Token operator, Expr right",
                "Grouping : Expr expression",
                "Literal : Object value",
                "Unary : Token operator, Expr right" ));
    }
    static void defineAST(String outputDirectory, String baseName, List<String> types) throws IOException{
        String path = outputDirectory+"/"+baseName+".java";
        PrintWriter writer = new PrintWriter(path, "UTF-8");

        writer.println("package zubaLang ;");
        writer.println();
        writer.println("abstract class "+baseName+" {");
        defineVisitor(writer, String baseName, List<String> types);
        writer.println();
        for (String type : types){
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(writer, baseName, className, fields);
            writer.println(className);
        }
        //the base accept() method
        writer.println();
        writer.println("abstract <R> R accept(Visitor<R> visitor);");
        writer.println("}");

        writer.close();

    }

    private static void defineVisitor(PrintWriter writer, String baseName, List<String> types){
        writer.println("    interface Visitor<R> {");

        for (String type : types){
            String className = type.split(":")[0].trim();
            writer.println("    R visit" + typeName + baseName + "(" + typeName + " " + baseName.toLowerCase() + ");");
        }

        writer.println("}");
    }

    static void defineType(PrintWriter writer, String baseName, String className, String fields) throws IOException {
        writer.println("static class "+className+ " extends " + baseName + " {");

        writer.println();

        // constructor
        writer.println(" public " +className+"(" +fields+"){");
        String[] fieldNames = fields.split(",");
        for (String fieldName : fieldNames){
             String name = fieldName.split(" ")[1];
            writer.println("    this."+fieldName+" = "+ name) ;
        }
        writer.println("    }");

        //visitor pattern
        writer.println();
        writer.println("    @Override");
        writer.println("    public <R> R accept(Visitor<R> visitor) {");
        writer.println("        return visitor.visit"+ className + baseName +"(this);");
        writer.println("    }");

        //fields
        writer.println();
        for(String fieldName : fieldNames){
            writer.println("final "+fieldName); 
        }
        writer.println("}");

    }
}
