package com.example.application;

import com.example.application.backend.Controller;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("sDashboard")
public class StudentSideBar extends AppLayout {
    String studentID = ComponentUtil.getData(UI.getCurrent(), "studentID").toString();

    public StudentSideBar() {
        DrawerToggle toggle = new DrawerToggle();
        H1 title = new H1("TALEEM.PK");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "5");
        Tabs tabs = getTabs();
        addToDrawer(tabs);
        addToNavbar(toggle, title);
        setPrimarySection(Section.DRAWER);
        setDrawerOpened(false);
    }

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        Controller _local = Controller.getInstance();
        RouterLink wallet = new RouterLink("Account Balance: " + _local.getBalanceStudent(studentID), StudentDashbord.class);
        wallet.getElement().removeAttribute("href");
        tabs.add(createTab(VaadinIcon.WALLET, wallet), createTab(VaadinIcon.HOME, new RouterLink("Home", StudentDashbord.class)), createTab(VaadinIcon.FOLDER_OPEN, new RouterLink("Purchased Courses", PurchasedCourses.class)), createTab(VaadinIcon.POWER_OFF, new RouterLink("Logout", LoginView.class)));

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;
    }

    private Tab createTab(VaadinIcon viewIcon, RouterLink viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box").set("margin-inline-end", "var(--lumo-space-m)").set("margin-inline-start", "var(--lumo-space-xs)").set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));

        return new Tab(link);
    }
}
