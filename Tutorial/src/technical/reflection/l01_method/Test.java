package technical.reflection.l01_method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = Victim.class.getMethod("printMessage", null);

        Victim v = new Victim();
        method.invoke(v, null);
    }

}
