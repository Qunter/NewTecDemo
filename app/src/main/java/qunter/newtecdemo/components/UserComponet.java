package qunter.newtecdemo.components;

import dagger.Component;
import qunter.newtecdemo.MainActivity;
import qunter.newtecdemo.module.UserModule;

@Component(modules = {UserModule.class})
public interface UserComponet {

     void inject(MainActivity activity);
}