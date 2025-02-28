# ClimateComparerApp für Android

## Kurzbeschreibung

ClimateComparerApp ist konzipiert zur Anzeige von Wetterdaten von frei wählbaren weltweiten Standorten.
Die App wird in Kotlin mit Jetpack Compose entwickelt und nutzt die  Open-Meteo-API für Wetterdaten. Zusätzlich wird RoomDB für die Speicherung von Standort Favoriten gespeichert.

## Design

<img src = "https://github.com/user-attachments/assets/08dc7cf0-c89b-4fb9-906b-fa5ae431e75c" width="300">

<img src = "https://github.com/user-attachments/assets/803f8ed0-fa67-44eb-aeb0-dc8fb61c2ae0" width="300">

<img src = "https://github.com/user-attachments/assets/285dfa16-c1b8-4615-97ad-a4132e8fe01a" width="300">

## Features

- Auswahl des Standorts via Textsuche
- Anzeige von Wetterdaten für ausgewählten Standort
- Speichern von Standorten als Favoriten
- Auswahlmöglichkeit für individuelle App-Hintergründe

# Technischer Aufbau

- Verwendung von Kotlin/Jetpack Compose für den Aufbau einer modernen und reaktionsfähigen Benutzeroberfläche mit deklarativer Syntax und dynamischen Layouts

## Projektaufbau

- Ordnerstruktur gemäß MVVM + Repository - Architektur:
  
  - Modell
  - View
  - ViewModel
  - Repository

## Datenspeicherung

- RoomDB

## API Calls

-  Open-Meteo-API für Wetterdaten

## 3rd-Party Tools und Frameworks



## Ausblick

- Vergleiche von Wetter-/Klimadaten eines Standorts
  - z.B. aktuelle Woche mit Woche vor ein/ zwei/ fünf Jahren
  - evtl. daraus resultierende Ableitungen
