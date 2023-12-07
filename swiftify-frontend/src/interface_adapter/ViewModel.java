package interface_adapter;

import java.beans.PropertyChangeListener;
/**
 * Represents a base class for view models in the Swiftify application.
 */
public abstract class ViewModel {

    private String viewName;
    private State state;
    /**
     * Constructs a new ViewModel with the specified view name and initial state.
     *
     * @param viewName The name of the associated view.
     * @param state    The initial state of the view model.
     */

    public ViewModel(String viewName, State state) {
        this.viewName = viewName;
        this.state = state;
    }
    /**
     * Retrieves the name of the associated view.
     *
     * @return The name of the view.
     */

    public String getViewName() {
        return this.viewName;
    }
    /**
     * Sets the active view name.
     *
     * @param viewName The name of the active view.
     */

    public void setActiveView(String viewName) {
        this.viewName = viewName;
    }
    /**
     * Retrieves the current state of the view model.
     *
     * @return The current state.
     */

    public State getState() {
        return this.state;
    }

    /**
     * Sets the state of the view model.
     *
     * @param state The new state.
     */

    public void setState(State state) {
        this.state = state;
    }

    /**
     * Notifies listeners that a property has changed.
     */

    public abstract void firePropertyChanged();

    /**
     * Adds a property change listener to the view model.
     *
     * @param listener The property change listener to be added.
     */

    public abstract void addPropertyChangeListener(PropertyChangeListener listener);
}
