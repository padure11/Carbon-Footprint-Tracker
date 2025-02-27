[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/31XZyb90)
# CARBON FOOTPRINT TRACKER
### Pădure Alexandru

## Descriere
O aplicație în Java care permite utilizatorilor să înregistreze activitățile zilnice și să estimeze emisiile de dioxid de carbon (CO₂) aferente fiecărei activități. Aplicația oferă sugestii pentru a reduce amprenta de carbon, stimulează utilizatorii să atingă obiective de sustenabilitate și permite compararea progresului în timp.

## Obiective Principale


* Promovarea sustenabilității
* Utilizarea tehnologiei pentru un posibil mic impact pozitiv asupra mediului
* Aprofundare cunoștiințe JAVA 


## Arhitectura

* Posibila structura a programului:
    - DBManager (clasa): va gestiona conexiunea cu baza de date.
    - Main: punctul de intrare în aplicație (login screen).
    - Progress: cum s-a schimbat amprenta de carbon în ultima lună.
    - Daily Challenge: provocarea zilnică propusă pentru utilizator
    - Tips: sfaturi pentru utilizatori pentru a reduce amprenta de carbon.

* Posibila structura a bazei de date:
    - Utilizator: 
        + id 
        + nume
        + prenume
        + data_curenta
        + carbon_foot_print
        
    - Activitati:
        + id
        + categorie
        + descriere
        + done_date
    
    - Sfaturi:
        + id
        + descriere
        + carbon_saving 

## Functionalitati/Exemple utilizare
* Adăugarea activităților zilnice și calculul amprentei de carbon
* Emisiile pentru fiecare categorie sunt calculate pe baza unor valori medii
* Utilizatorul poate vizualiza un grafic cu emisiile zilnice, săptămânale și lunare.
* Aplicația sugerează modalități de reducere a emisiilor, cum ar fi alternative pentru transport sau sfaturi pentru economisirea energiei.
* „Daily Challenge” ar putea motiva utilizatorul să facă mici schimbări (cum ar fi folosirea bicicletei în locul mașinii)
* Utilizatorul poate stabili obiective pentru a-si reduce amprenta de carbon.
* Clasament și scoruri.
* Categorii de activități:
    - Transport: (bicicletă, mașină, tren, avion, etc.)
    - Locuință: consum de energie, apă, gaz
    - Obiceiuri alimentare: tipul de dietă și numărul de mese pe zi
