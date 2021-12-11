package com.example.application;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("dialog-basic")
public class DialogBasic  extends Div{


    VerticalLayout dialogLayout;
    public DialogBasic() {
        // tag::snippet[]
        Dialog dialog = new Dialog();
        dialog.getElement().setAttribute("aria-label", "Create new employee");

        VerticalLayout dialogLayout = createDialogLayout(dialog);
        dialog.add(dialogLayout);
dialog.open();

    }

    private static VerticalLayout createDialogLayout(Dialog dialog) {
        H2 headline = new H2("Register As");
        headline.getStyle().set("margin", "var(--lumo-space-m) 0 0 0")
                .set("font-size", "2em").set("font-weight", "italic");

        Button teacher = new Button("Teacher", buttonClickEvent -> {
            UI.getCurrent().navigate(Registeration.class);
            dialog.close();
        });
        Button student = new Button("Student", buttonClickEvent -> {
            UI.getCurrent().navigate(Registeration.class);
            dialog.close();
        });

        student.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        teacher.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        VerticalLayout buttonLayout = new VerticalLayout(teacher, student);
        buttonLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        VerticalLayout dialogLayout = new VerticalLayout(headline,buttonLayout);

        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        dialogLayout.getStyle().set("width", "300px").set("max-width", "100%");

        return dialogLayout;
    }

}
