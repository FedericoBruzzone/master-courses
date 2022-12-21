import java.util.HashMap;
import java.util.Stack;
import java.io.*;


public class Logger{
    private static Logger instance = new Logger();
    private HashMap<String,Arch> graph = new HashMap<>();
    private Stack<String> callStack = new Stack<>();

    private class Arch{
        String caller, method, called;
        Arch(String caller, String method, String called){
            this.caller=caller;this.method=method;this.called=called;
        }
        @Override
        public String toString(){
            return "\t"+caller+" -> "+called+" [label=\""+method+"()\"]\n";
        }
    }

    private Logger(){
        callStack.push("main");
    }

    public static Logger get(){
        return Logger.instance;
    }

    public void log(String message){
         System.out.println("[LOGGER]: "+message);
    }

    public void trackExecution(String className, String methodName, int objectHash ){
        this.log("Traking call to: "+className+"."+methodName+"()"+(objectHash > 0 ? " on Object "+ objectHash : ""));
        Arch arch = new Arch(callStack.peek(),methodName,className+"_"+String.valueOf(objectHash));
        this.graph.put(arch.caller + arch.method + arch.called, arch);
        //Update the HEAD of the callStack
        this.callStack.push(className+"_"+String.valueOf(objectHash));
    }

    public void trackPopStack(){
        this.callStack.pop();
    }

    public void printExecutionGraph(){
        this.log("Exporting the Execution Graph!");
        try(FileWriter fout = new FileWriter("graph.dot")){
            fout.append("digraph {\n");
            for ( String s : this.graph.keySet()){
                fout.append(this.graph.get(s).toString());
            }
            fout.append("}\n");
        }catch(IOException e){this.log(e.getMessage());}
    }

}