package com.example.application;

import com.example.application.backend.Controller;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("CreateCourseDialogue")
public class CreatCourseDialogue extends Div {
    String tID = ComponentUtil.getData(UI.getCurrent(), "teacherID").toString(); // Have to be Dynamic

    public CreatCourseDialogue() {
        Dialog dialog = new Dialog();
        dialog.getElement().setAttribute("aria-label", "Create new employee");

        VerticalLayout dialogLayout = createDialogLayout(dialog);
        dialog.add(dialogLayout);
        dialog.open();
    }

    private VerticalLayout createDialogLayout(Dialog dialog) {
        H2 headline = new H2("Create Course");
        headline.getStyle().set("margin", "var(--lumo-space-l) 0 0 0").set("font-size", "2.5em").set("font-weight", "Bold");

        TextField firstNameField = new TextField("Course Name");
        TextField lastNameField = new TextField("Subject");
        TextField price = new TextField("Course Price:");

        ComboBox<String> comboBox = new ComboBox<>("Select Class");
        comboBox.setItems("9th", "10th", "1'st year", "2nd year", "Higher");
        comboBox.setRequired(true);
        comboBox.setWidth("250px");
        comboBox.setAllowCustomValue(false);

        VerticalLayout fieldLayout = new VerticalLayout(firstNameField, lastNameField, price);
        fieldLayout.setSpacing(true);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button aContinueButton = new Button("Continue", buttonClickEvent -> {
            // Going to next state
            Controller regCourse = Controller.getInstance();
            int courseID = regCourse.registerCourse(tID, firstNameField.getValue(), lastNameField.getValue(), comboBox.getValue(), price.getValue());
            // having course id too
            ComponentUtil.setData(UI.getCurrent(), "courseID", String.valueOf(courseID));
            UI.getCurrent().navigate(CreatCourse.class);
            dialog.close();
        });
        aContinueButton.setWidth("150px");

        HorizontalLayout buttonLayout = new HorizontalLayout(aContinueButton);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        aContinueButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VerticalLayout dialogLayout = new VerticalLayout(headline, fieldLayout, comboBox, buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        dialogLayout.getStyle().set("width", "400px").set("max-width", "100%");

        return dialogLayout;
    }

}
