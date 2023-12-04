package use_case.load_album;

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
