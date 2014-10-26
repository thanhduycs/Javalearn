//http://armoredbarista.blogspot.com/2012/01/patching-java-at-runtime.html

package technical.reflection.l01_method;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.instrument.*;
import java.security.ProtectionDomain;

final public class Patcher implements ClassFileTransformer {

    private static byte[] PATCHED_BYTES;
    private static final String PATH_TO_FILE = "Apatched.class";
    private static final String CLASS_TO_PATCH = "stringchanger/A";

    public Patcher() throws FileNotFoundException, IOException {
        if (PATCHED_BYTES == null) {
            PATCHED_BYTES = readPatchedCode(PATH_TO_FILE);
        }
    }

    public static void premain(String agentArgument,
            final Instrumentation instrumentation) {
        System.out.println("Initializing hot patcher...");
        Patcher agent = null;

        try {
            agent = new Patcher();
        } catch (Exception e) {
            System.out.println("terrible things happened....");
        }

        instrumentation.addTransformer(agent);
    }

    public byte[] transform(final ClassLoader loader, String className,
            final Class classBeingRedefined,
            final ProtectionDomain protectionDomain,
            final byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] result = null;

        if (className.equals(CLASS_TO_PATCH)) {
            System.out.println("Patching... " + className);
            result = PATCHED_BYTES;
        }

        return result;
    }

    private byte[] readPatchedCode(final String path)
      throws FileNotFoundException, IOException {

        return null;
    }
}