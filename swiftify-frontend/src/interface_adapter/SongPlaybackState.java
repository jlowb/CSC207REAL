package interface_adapter;

import entity.PlayerState;
import view.LoadSongsView;

public class SongPlaybackState extends State {

    private final LoadSongsView view;
    private String songName;
    private PlayerState musicPlayer;
    private boolean playing;

    public SongPlaybackState(LoadSongsView view, String songName, PlayerState musicPlayer, boolean playing) {
        this.view = view;
        this.songName = songName;
        this.musicPlayer = musicPlayer;
        this.playing = playing;
    }

    public LoadSongsView getView() {
        return this.view;
    }

    public String getSongName() {
        return this.songName;
    }

    public PlayerState getMusicPlayer() {
        return this.musicPlayer;
    }

    public boolean getPlaying() {
        return this.playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
