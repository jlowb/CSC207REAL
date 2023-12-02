package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {

    private final ViewManagerModel viewManagerModel;

    public ViewManager(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            ViewModel viewModel = (ViewModel) evt.getNewValue();
            ViewBuilder viewBuilder = new ViewBuilder(viewModel);
            viewBuilder.buildView().setVisible(true);
        }
    }
}
