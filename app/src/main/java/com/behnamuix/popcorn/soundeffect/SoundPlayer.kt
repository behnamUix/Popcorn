package com.behnamuix.popcorn.soundeffect

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import com.behnamuix.popcorn.R

class SoundPlayer(ctx:Context) {
    private val soundPool: SoundPool
    private val soundId: Int
    init {

        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()


        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()


        soundId = soundPool.load(ctx, R.raw.remove_sound_effect, 1)
    }

    fun play(loop: Boolean = false) {
        soundPool.play(
            soundId,
            1f,
            1f,
            1,
            if(loop) -1 else 0,
            1f
        )
    }

    fun stop() {
        soundPool.stop(soundId)
    }

    fun release() {
        soundPool.release()
    }
}