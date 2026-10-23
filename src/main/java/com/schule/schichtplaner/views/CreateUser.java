package com.schule.schichtplaner.views;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.schule.schichtplaner.entitys.Mitarbeiter;
import com.schule.schichtplaner.repositories.MitarbeiterRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@Route("/create-user")
@PermitAll
public class CreateUser extends VerticalLayout {

    private final MitarbeiterRepository mitarbeiterRepository;

    public CreateUser(MitarbeiterRepository mitarbeiterRepository) {
        this.mitarbeiterRepository = mitarbeiterRepository;

        H1 title = new H1("Benutzer erstellen");
        add(title);

        add(createUserForm());
    }

    private FormLayout createUserForm() {
        FormLayout formLayout = new FormLayout();

        TextField benutzernameField = new TextField("Benutzername");
        PasswordField passwortField = new PasswordField("Passwort");
        Button createButton = new Button("Erstellen");

        createButton.addClickListener(event -> {
            String benutzername = benutzernameField.getValue();
            String passwort = passwortField.getValue();

            if (benutzername.isEmpty() || passwort.isEmpty()) {
                Notification.show("Bitte füllen Sie alle Felder aus.");
                return;
            }

            Mitarbeiter mitarbeiter = new Mitarbeiter();
            mitarbeiter.setBenutzername(benutzername);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            mitarbeiter.setPasswort(passwordEncoder.encode(passwort));

            mitarbeiterRepository.save(mitarbeiter);
            Notification.show("Benutzer erfolgreich erstellt.");
            benutzernameField.clear();
            passwortField.clear();
        });

        formLayout.add(benutzernameField, passwortField, createButton);
        return formLayout;
    }
    
}
