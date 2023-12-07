package use_case.load_albums;

public class LoadAlbumsInputData {
    private String selection;

    public LoadAlbumsInputData(String selection) {
        this.selection = selection;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}
