package libraryapp.ui.button;

import libraryapp.service.IService;
import libraryapp.service.Service;

import java.util.HashMap;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author: Anton Gorbovyi
 * @version: 12.05.2024
 **/
public abstract class Button {
    private final HashMap<String, Service> services;

    public Button() {
        this.services = new HashMap<>();
    }
    public HashMap<String, Service> getServices(){
        return this.services;
    }

    public IService getService(String name){
        return services.get(name);
    }

    public void put(String key, Service value){
        this.services.put(key,value);
    }
}
