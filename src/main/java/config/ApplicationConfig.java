package config;

import services.DoingServices;
import services.DoneServices;
import services.ToDoServices;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

///api

@ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> recursos = new HashSet<>();

        recursos.add(ToDoServices.class);
        recursos.add(DoingServices.class);
        recursos.add(DoneServices.class);

        return recursos;
    }
}

