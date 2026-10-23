package com.schule.schichtplaner.views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.schule.schichtplaner.entitys.Einsatz;
import com.schule.schichtplaner.entitys.Mitarbeiter;
import com.schule.schichtplaner.entitys.Schicht;
import com.schule.schichtplaner.repositories.EinsatzRepository;
import com.schule.schichtplaner.repositories.MitarbeiterRepository;
import com.schule.schichtplaner.repositories.SchichtRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/home")
public class HomeView extends VerticalLayout {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final VerticalLayout weekList = new VerticalLayout();

    private final MitarbeiterRepository mitarbeiterRepository;
    private final SchichtRepository schichtRepository;
    private final EinsatzRepository einsatzRepository;
    private final Grid<Einsatz> einsatzGrid = new Grid<>(Einsatz.class, false);
    private LocalDate toDateInit;

    public HomeView(MitarbeiterRepository mitarbeiterRepository, SchichtRepository schichtRepository,
            EinsatzRepository einsatzRepository) {
        this.mitarbeiterRepository = mitarbeiterRepository;
        this.schichtRepository = schichtRepository;
        this.einsatzRepository = einsatzRepository;

        H1 title = new H1("Schichtplaner");
        add(title);

        add(createPlanner());
        add(createShiftSection());
    }

    private HorizontalLayout createPlanner() {
        DatePicker fromDatePicker = new DatePicker("Von");

        DatePicker toDatePicker = new DatePicker("Bis");

        fromDatePicker.addValueChangeListener(event -> {
            LocalDate date = fromDatePicker.getValue();

            if (date != null) {
                toDateInit = date.plusWeeks(1);

            }
            toDatePicker.setValue(toDateInit);
        });

        HorizontalLayout dateRange = new HorizontalLayout(fromDatePicker, toDatePicker);

        /*  
                        Zeigt die Mitarbeiter an die in der Datenbank liegen
         */
        ComboBox<Mitarbeiter> employeeSelector = new ComboBox<>("Mitarbeiter");
        employeeSelector.setItems(mitarbeiterRepository.findAll());
        employeeSelector.setItemLabelGenerator(mitarbeiter
                -> mitarbeiter.getVorname() + " " + mitarbeiter.getNachname());

        ComboBox<Schicht> shiftType = new ComboBox<>("Schicht");
        shiftType.setItems(schichtRepository.findAll());
        shiftType.setItemLabelGenerator(Schicht::getSchichtart);

        Button addShift = new Button("Schicht eintragen");
        addShift.addClickListener(event -> {
            saveEinsatz(employeeSelector.getValue(), shiftType.getValue(),
                    fromDatePicker.getValue(), toDatePicker.getValue());
        });

        VerticalLayout planner = new VerticalLayout(
                new H2("Zeitraum planen"), dateRange, employeeSelector, shiftType, addShift,
                new H2("Wochen"), weekList);
        return new HorizontalLayout(planner);
    }

    private VerticalLayout createShiftSection() {
        H2 title = new H2("Schichtübersicht");
        einsatzGrid.addColumn(einsatz -> einsatz.getMitarbeiter().getVorname() + " "
                + einsatz.getMitarbeiter().getNachname()).setHeader("Mitarbeiter");
        einsatzGrid.addColumn(einsatz -> einsatz.getSchicht().getSchichtart()).setHeader("Schicht");
        einsatzGrid.addColumn(Einsatz::getDatumVon).setHeader("Von");
        einsatzGrid.addColumn(Einsatz::getDatumBis).setHeader("Bis");
        einsatzGrid.setItems(einsatzRepository.findAll());
        einsatzGrid.setWidthFull();

        VerticalLayout section = new VerticalLayout(title, einsatzGrid);
        section.setWidthFull();
        return section;
    }

    private void saveEinsatz(Mitarbeiter mitarbeiter, Schicht schicht, LocalDate datumVon,
            LocalDate datumBis) {
        if (mitarbeiter == null || schicht == null || datumVon == null || datumBis == null) {
            Notification.show("Bitte Mitarbeiter, Schicht und Zeitraum auswählen.");
            return;
        }

        if (datumVon.isAfter(datumBis)) {
            Notification.show("Das Von-Datum muss vor dem Bis-Datum liegen.");
            return;
        }

        einsatzRepository.save(new Einsatz(mitarbeiter, schicht, datumVon, datumBis));
        einsatzGrid.setItems(einsatzRepository.findAll());
        Notification.show("Einsatz wurde gespeichert.");
    }
}
