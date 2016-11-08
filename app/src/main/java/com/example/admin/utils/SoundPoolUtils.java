package com.example.admin.utils;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by ${Kikis} on 2016-09-26.
 */

public class SoundPoolUtils {

    //音效的音量
    int streamVolume;

    //定义SoundPool 对象
    private SoundPool soundPool;

    //定义HASH表
    private HashMap<Integer, Integer> soundPoolMap;

    private static Activity ac;

    public SoundPoolUtils(Activity ac) {
        this.ac = ac;
        initSounds();
    }
    //初始化;
    private void initSounds() {

        //初始化soundPool 对象,第一个参数是允许有多少个声音流同时播放,第2个参数是声音类型,第三个参数是声音的品质
        soundPool = new SoundPool(100, AudioManager.STREAM_MUSIC, 100);

        //初始化HASH表
        soundPoolMap = new HashMap<Integer, Integer>();

        //获得声音设备和设备音量
        AudioManager mgr = (AudioManager) ac.getSystemService(Context.AUDIO_SERVICE);
        streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);

    }

    //传入音效ID;
    public void loadSfx(int raw, int ID) {
    //把资源中的音效加载到指定的ID(播放的时候就对应到这个ID播放就行了)
        soundPoolMap.put(ID, soundPool.load(ac, raw, ID));
    }


    //播放;
    public void play(int sound, int uLoop) {
        soundPool.play(soundPoolMap.get(sound), streamVolume, streamVolume, 1, uLoop, 1f);
    }

}
