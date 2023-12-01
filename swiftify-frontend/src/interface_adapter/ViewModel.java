package interface_adapter;

import java.beans.PropertyChangeListener;

public abstract class ViewModel {

    private String viewName;
    private State state;

    public ViewModel(String viewName, State state) {
        this.viewName = viewName;
        this.state = state;
    }

    public String getViewName() {
        return this.viewName;
    }

    public void setActiveView(String viewName) {
        this.viewName = viewName;
    }

    public State getState() {
        return this.state;
    }
    public abstract void firePropertyChanged();

    public abstract void addPropertyChangeListener(PropertyChangeListener listener);
}
