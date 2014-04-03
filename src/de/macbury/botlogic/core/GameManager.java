package de.macbury.botlogic.core;

import aurelienribon.tweenengine.Tween;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import de.macbury.botlogic.core.audio.AudioManager;
import de.macbury.botlogic.core.controller.GameController;
import de.macbury.botlogic.core.entites.ModelEntity;
import de.macbury.botlogic.core.graphics.camera.RTSCameraController;
import de.macbury.botlogic.core.input.InputManager;
import de.macbury.botlogic.core.levels.BaseLevel;
import de.macbury.botlogic.core.levels.PlaygroundLevel;
import de.macbury.botlogic.core.runtime.ScriptContextFactory;
import de.macbury.botlogic.core.runtime.ScriptRunner;
import de.macbury.botlogic.core.tween.CameraAccessor;
import de.macbury.botlogic.core.tween.ModelEntityAccessor;
import org.mozilla.javascript.ContextFactory;
import sun.font.ScriptRun;

import javax.swing.*;

/**
 * Created by macbury on 27.03.14.
 */
public class GameManager extends Game {
  private static final String TAG = "GameManager";
  private boolean loading = true;

  @Override
  public void create() {
    Gdx.app.log(TAG, "created");
    BotLogic.game         = this;
    BotLogic.inputManager = new InputManager();
    BotLogic.audio        = new AudioManager();
    Tween.registerAccessor(ModelEntity.class, new ModelEntityAccessor());
    Tween.registerAccessor(RTSCameraController.class, new CameraAccessor());
    ContextFactory.initGlobal(new ScriptContextFactory());
    loading = false;
  }

  public BaseLevel getLevel() {
    return (BaseLevel)getScreen();
  }

  public boolean isLoading() {
    return loading;
  }

  @Override
  public void render() {
    super.render();
  }

  public void newGame(String path) {
    Gdx.app.log(TAG, "New game");
    if (getLevel() != null) {
      getLevel().dispose();
      Gdx.app.log(TAG, "Disposing old level");
    }
    setScreen(new PlaygroundLevel(path));
  }

  public GameController getController() {
    return getLevel().getController();
  }

  public ScriptRunner getScriptRunner() {
    return getController().getScriptRunner();
  }
}
