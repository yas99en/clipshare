package io.github.yas99en.clipshare.view;

import java.lang.reflect.Proxy;

import javax.swing.SwingUtilities;

public class InvokeLaterProxy {
    @SuppressWarnings("unchecked")
    public static <T> T makeProxy(Class<T> clazz, T obj) {
        return (T)Proxy.newProxyInstance(obj.getClass().getClassLoader(), new Class<?>[]{clazz},
                (proxy, method, args) -> {
                    SwingUtilities.invokeLater(() -> {
                        try {
                            method.invoke(obj, args);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                    return null;
                });
    }
}
