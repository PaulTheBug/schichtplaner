package com.schule.schichtplaner.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;

public class MainLayout extends AppLayout {

    public MainLayout() {
        DrawerToggle drawerToggle = new DrawerToggle();
        H1 title = new H1("Schichtplaner");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)");
        title.getStyle().set("margin", "0");

        addToNavbar(drawerToggle, title);

        SideNav navigation = new SideNav();
        navigation.addItem(
                new SideNavItem("Startseite", HomeView.class),
                new SideNavItem("Benutzer erstellen", CreateUser.class));

        addToDrawer(new Scroller(navigation));
    }
}
