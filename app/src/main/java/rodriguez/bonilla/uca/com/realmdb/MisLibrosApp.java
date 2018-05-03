package rodriguez.bonilla.uca.com.realmdb;

import android.app.Application;

public class MisLibrosApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }
}
