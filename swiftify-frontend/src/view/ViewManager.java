package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Manages views and responds to changes in the associated ViewManagerModel.
 */

public class ViewManager implements PropertyChangeListener {

    private final ViewManagerModel viewManagerModel;
    /**
     * Constructs a new ViewManager with the specified ViewManagerModel.
     *
     * @param viewManagerModel The model responsible for managing views.
     */

    public ViewManager(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }
    /**
     * Called when a property of the associated ViewManagerModel changes.
     * This method creates and displays a new view based on the updated ViewModel.
     *
     * @param evt The PropertyChangeEvent containing information about the change.
     */

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            ViewModel viewModel = (ViewModel) evt.getNewValue();
            ViewBuilder viewBuilder = new ViewBuilder(this.viewManagerModel);
            viewBuilder.buildView().setVisible(true);
        }
    }
}
