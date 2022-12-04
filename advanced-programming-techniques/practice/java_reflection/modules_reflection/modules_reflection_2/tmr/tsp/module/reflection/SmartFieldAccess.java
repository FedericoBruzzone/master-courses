package tsp.module.reflection;

import java.lang.reflect.*;

public interface SmartFieldAccess {
    default public Object instVarAt(String name) throws Exception {
        Field f = this.getClass().getDeclaredField(name);
        f.setAccessible(true);
        if (!Modifier.isStatic(f.getModifiers()))
            return f.get(this);
        return null;
    }

    default public void instVarAtPut(String name, Object value) throws Exception {
        Field f = this.getClass().getDeclaredField(name);
        f.setAccessible(true);
        if (!Modifier.isStatic(f.getModifiers()))
            f.set(this, value);
    }
}