package org.deepsource.doterra.application;

import org.deepsource.doterra.configuration.DoTerraServiceConfiguration;
import org.deepsource.doterra.resources.HelloResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class DoTerraApplication extends Application<DoTerraServiceConfiguration>  {

	@Override
	public void run(DoTerraServiceConfiguration configuration, Environment environment) throws Exception {
		environment.jersey().register(HelloResource.class);
	}

	
    public static void main(String[] args) throws Exception {
        new DoTerraApplication().run(args);
    }
}
