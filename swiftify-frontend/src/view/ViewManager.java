package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {

    private ViewManagerModel viewManagerModel;

    public ViewManager(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewName = (String) evt.getNewValue();
            ViewBuilder viewBuilder = new ViewBuilder(viewName);
            viewBuilder.buildView().setVisible(true);
        }
    }
}
