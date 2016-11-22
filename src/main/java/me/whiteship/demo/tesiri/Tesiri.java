package me.whiteship.demo.tesiri;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;
import java.util.Random;

public class Tesiri {

    public <T> T create(Class<T> clazz) {
        T t;
        try {
            Constructor[] ctors = clazz.getDeclaredConstructors();
            Constructor ctor = null;
            for (Constructor ctor1 : ctors) {
                ctor = ctor1;
                if (ctor.getGenericParameterTypes().length == 0)
                    break;
            }
            ctor.setAccessible(true);

            Parameter[] parameters = ctor.getParameters();
            Object[] params = new Object[parameters.length];
            for (int i = 0 ; i < parameters.length ; i++) {
                params[i] = create(parameters[i].getType());
            }

            t = (T) ctor.newInstance(params);

            setFieldRandomValue(t, clazz);

            Class<? super T> superclass = clazz.getSuperclass();
            if (superclass != null && !superclass.equals(Object.class)) {
                setFieldRandomValue(t, superclass);
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new TesiriException(e);
        }
        return t;
    }

    private <T> void setFieldRandomValue(T t, Class<T> clazz) throws IllegalAccessException {
        Random random = new Random();
        for (Field f : clazz.getDeclaredFields()) {
            // TODO skip if the filed has an annotation that indicates it wants to skip.
            f.setAccessible(true);
            Class<?> type = f.getType();
            if (type.isAssignableFrom(String.class)) {
                f.set(t, "I'm your shaman.");
            } else if (type.isAssignableFrom(int.class)) {
                f.set(t, random.nextInt());
            } else if (type.isAssignableFrom(Integer.class)) {
                f.set(t, random.nextInt());
            } else if (type.isAssignableFrom(LocalDateTime.class)) {
                f.set(t, LocalDateTime.now().plusDays(random.nextInt(30)));
            }
        }
    }

}
