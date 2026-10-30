package com.yeiyei.triplanner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class TravelPreferences {

    public TravelPreferences(
            String destination,
            LocalDate startDate,
            LocalDate endDate,
            Integer budget,
            Set<TravelInterest> interests,
            TravelPace travelPace) {

        if (budget == null || budget <= 0) {
            throw new IllegalArgumentException(
                    "El presupuesto debe ser mayor que cero");
        }

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException(
                    "Las fechas de inicio y fin son obligatorias");
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException(
                    "La fecha de fin no puede ser anterior a la fecha de inicio");
        }

        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.interests = interests == null
                ? new HashSet<>()
                : new HashSet<>(interests);
        this.travelPace = travelPace;
    }

    @NotBlank
    private String destination;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    @Positive
    private Integer budget;

    @Getter(AccessLevel.NONE)
    private Set<TravelInterest> interests = new HashSet<>();

    @NotNull
    private TravelPace travelPace;

    public void changeBudget(Integer newBudget) {
        if (newBudget == null || newBudget <= 0) {
            throw new IllegalArgumentException("El presupuesto debe ser mayor que cero");
        }

        this.budget = newBudget;
    }

    public void replaceInterests(Set<TravelInterest> newInterests) {
        if (newInterests == null) {
            throw new IllegalArgumentException("Los intereses no pueden ser nulos");
        }

        this.interests = new HashSet<>(newInterests);
    }

    public Set<TravelInterest> getInterests() {
        return Set.copyOf(interests);
    }
}
