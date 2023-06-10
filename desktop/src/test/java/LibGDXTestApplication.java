import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Files;

public class LibGDXTestApplication extends ApplicationAdapter {
    @Override
    public void create() {
        // Set up the necessary LibGDX context for testing
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        // Add any additional initialization code you may need
    }
}
