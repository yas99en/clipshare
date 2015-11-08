package io.github.yas99en.clipshare.view;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Msgs {
    private static final String BUNDLE_NAME = "io.github.yas99en.clipshare.view.messages"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private Msgs() {
    }

    public static String m(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
}
