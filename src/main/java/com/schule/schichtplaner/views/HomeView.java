package com.schule.schichtplaner.views;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/home")
public class HomeView extends VerticalLayout {

        private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        private final VerticalLayout weekList = new VerticalLayout();

    public HomeView() {
        H1 title = new H1("Schichtplaner");
        add(title);

        add(createPlanner());
        add(createShiftSection());
        }

        private HorizontalLayout createPlanner() {
                DatePicker fromDatePicker = new DatePicker("Von");
                DatePicker toDatePicker = new DatePicker("Bis");

                fromDatePicker.addValueChangeListener(event -> updateWeeks(fromDatePicker.getValue(), toDatePicker.getValue()));
                toDatePicker.addValueChangeListener(event -> updateWeeks(fromDatePicker.getValue(), toDatePicker.getValue()));

                HorizontalLayout dateRange = new HorizontalLayout(fromDatePicker, toDatePicker);

                ComboBox<String> employeeSelector = new ComboBox<>("Mitarbeiter");

                ComboBox<String> shiftType = new ComboBox<>("Schicht");
                shiftType.setItems("Frühdienst", "Spätdienst", "Nachtdienst", "Bereitschaft");

                Button addShift = new Button("Schicht eintragen");

                VerticalLayout planner = new VerticalLayout(
                                new H2("Zeitraum planen"), dateRange, employeeSelector, shiftType, addShift,
                                new H2("Wochen"), weekList);
                return new HorizontalLayout(planner);
        }

        private void updateWeeks(LocalDate from, LocalDate to) {
                weekList.removeAll();
                if (from == null || to == null) {
                        weekList.add(new Span("Wähle ein Von- und Bis-Datum aus."));
                        return;
                }

                if (from.isAfter(to)) {
                        weekList.add(new Span("Das Von-Datum muss vor dem Bis-Datum liegen."));
                        return;
                }

                LocalDate weekStart = from.with(java.time.temporal.TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                LocalDate lastWeekStart = to.with(java.time.temporal.TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                while (!weekStart.isAfter(lastWeekStart)) {
                        int weekNumber = weekStart.get(WeekFields.ISO.weekOfWeekBasedYear());
                        LocalDate weekEnd = weekStart.plusDays(6);
                        Span week = new Span("KW " + weekNumber + " · " + DATE_FORMAT.format(weekStart)
                                        + " - " + DATE_FORMAT.format(weekEnd));
                        weekList.add(week);
                        weekStart = weekStart.plusWeeks(1);
                }
        }

        private VerticalLayout createShiftSection() {
                H2 title = new H2("Schichtübersicht");
                Span emptyState = new Span("Noch keine Schichten eingetragen.");

                VerticalLayout section = new VerticalLayout(title, emptyState);
                return section;
        }
}