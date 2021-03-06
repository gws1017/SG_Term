package kr.ac.kpu.game.s2017182016.jumpman.game.scenes.title;

import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.widget.TextView;

import kr.ac.kpu.game.s2017182016.jumpman.R;
import kr.ac.kpu.game.s2017182016.jumpman.framework.game.Scene;
import kr.ac.kpu.game.s2017182016.jumpman.framework.iface.GameObject;
import kr.ac.kpu.game.s2017182016.jumpman.framework.object.Background;
import kr.ac.kpu.game.s2017182016.jumpman.framework.object.ImageObject;
import kr.ac.kpu.game.s2017182016.jumpman.framework.object.Midground;
import kr.ac.kpu.game.s2017182016.jumpman.framework.util.Sound;
import kr.ac.kpu.game.s2017182016.jumpman.framework.view.GameView;
import kr.ac.kpu.game.s2017182016.jumpman.game.scenes.main.MainGame;
import kr.ac.kpu.game.s2017182016.jumpman.game.scenes.main.MainScene;

public class TitleScene extends Scene {
    TextView text;
    Animation anim;

    enum Layer {
        bg,logo, COUNT
    }
    public static TitleScene scene;
    MediaPlayer titleBgm;
    MediaPlayer startBgm;
    public void add(Layer layer, GameObject obj) {
        add(layer.ordinal(), obj);
    }
    @Override
    public void start() {
        super.start();
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        int iw = 480;
        int ih = 350;
        titleBgm = MediaPlayer.create(GameView.view.getContext(),R.raw.menu_intro);
        startBgm = MediaPlayer.create(GameView.view.getContext(),R.raw.press_start);
        initLayers(Layer.COUNT.ordinal());


        add(Layer.bg, new Midground(R.mipmap.title_bg));
        int lx = (iw/2) * w / iw ;
        int ly = (82) * h / ih;

        add(Layer.logo,new ImageObject(R.mipmap.title_logo,lx,ly));

        int tx = (iw/2) * w / iw ;
        int ty = (231) * h / ih;

        add(Layer.logo,new ImageObject(R.mipmap.title_text,tx,ty));
        float volume = 1.f;

//        titleBgm.setVolume(volume,volume);
        titleBgm.setLooping(true);
        titleBgm.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        MainGame game = MainGame.get();
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            titleBgm.stop();
            startBgm.start();
            startBgm.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    MainGame game = MainGame.get();
                    game.popScene();
                    game.push(new MainScene());

                }
            });
        }
        return super.onTouchEvent(e);
    }
}
