package j2ee.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

class Lookup {
    public static void main(String[] args) {
    // Check that user has supplied name of file to lookup
    if (args.length != 1) {
        System.err.println("usage: java Lookup <filename>");
        System.exit(-1);
    }

    String name = args[0];

    // Identify service provider to use
    Hashtable env = new Hashtable(11);
    env.put(Context.INITIAL_CONTEXT_FACTORY,  "com.sun.jndi.fscontext.RefFSContextFactory");

    try {

        // Create the initial context
        Context ctx = new InitialContext(env);

        // Look up an object
        Object obj = ctx.lookup(name);

        // Print it out
        System.out.println(name + " is bound to: " + obj);
        
        // Close the context when we're done
        ctx.close();
    } catch (NamingException e) {
        System.err.println("Problem looking up " + name + ": " + e);
    }
    }
}