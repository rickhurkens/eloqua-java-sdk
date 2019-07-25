package com.eloqua;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Activator is the main starting class.
 */
public class Activator implements BundleActivator {

    /**
     * Invoked upon starting this bundle.
     * @param context The bundle context if this bundle.
     */
    @Override
    public final void start(final BundleContext context) {
        System.out.println("Starting bundle 'eloqua-java-sdk'");
    }

    /**
     * Invoked upon stopping this bundle.
     * @param context The bundle context if this bundle.
     */
    @Override
    public final void stop(final BundleContext context) {
        System.out.println("Stopping bundle 'eloqua-java-sdk'");
    }
}
