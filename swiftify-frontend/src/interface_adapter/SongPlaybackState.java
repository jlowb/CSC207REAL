package interface_adapter;

import entity.PlayerState;
import view.LoadSongsView;

public class SongPlaybackState extends State {

    private final LoadSongsView view;
    private PlayerState musicPlayer;
    private boolean playing;

    public SongPlaybackState(LoadSongsView view, PlayerState musicPlayer, boolean playing) {
        this.view = view;
        this.musicPlayer = musicPlayer;
        this.playing = playing;
    }

    public LoadSongsView getView() {
        return this.view;
    }
}
