# 🌱 Carbon Footprint Tracker

## Monitorizează și reduce impactul tău asupra mediului!

### 📌 Descriere
Carbon Footprint Tracker este o aplicație desktop realizată în **Java (Swing)** care ajută utilizatorii să își monitorizeze și să își reducă amprenta de carbon prin urmărirea activităților zilnice. Utilizatorii pot adăuga activități specifice, vizualiza statistici personalizate și participa la provocări zilnice pentru a încuraja un stil de viață mai sustenabil.

## 🛠️ Tehnologii folosite
- **Java (Swing)** – Interfață grafică
- **MySQL** – Stocarea datelor utilizatorului și activităților
- **JDBC (Java Database Connectivity)** – Conectarea la baza de date
- **CardLayout & JPanel** – Gestionarea interfeței grafice
- **JLayeredPane** – Schimbarea dinamică a ferestrelor

## 🎯 Funcționalități
✅ **Adăugarea activităților** – Utilizatorii pot introduce activități din categoriile Transport, Energie și Alimentație.  
✅ **Provocarea zilnică** – În fiecare zi, utilizatorii primesc o provocare sustenabilă pentru a reduce impactul lor asupra mediului.  
✅ **Statistici personalizate** – Utilizatorii pot vizualiza grafice despre consumul lor și compara emisiile de carbon din diferite perioade de timp.  
✅ **Clasament (Leaderboard)** – Oferă utilizatorilor posibilitatea de a concura pentru cel mai mic nivel de emisii.  
✅ **Pagini interactive** – Navigare ușoară între diferite secțiuni ale aplicației.

## 📂 Structura proiectului
```plaintext
/CarbonFootprintTracker
│── /src
│   ├── /DBManager
│   │   ├── JdbcConnector.java
│   ├── /Ui
│   │   ├── MainScreen.java
│   │   ├── ActivityScreen.java
│   │   ├── CarbonFootprintIntroPage.java
│   │   ├── DailyChallengeScreen.java
│   │   ├── LeaderBoardScreen.java
│   │   ├── Chart.java
│   ├── /User
│   │   ├── User.java
│   │   ├── Activity.java
│   │   ├── Category.java
│── /Images
│── /Docs
│── README.md
│── carbon_footprint_tracker.sql (Script pentru baza de date)
```

## 🛠️ Instalare și Configurare
1️⃣ **Clonează proiectul**  
```bash
git clone https://github.com/username/CarbonFootprintTracker.git
cd CarbonFootprintTracker
```

2️⃣ **Configurează baza de date**  
- Importă fișierul `carbon_footprint_tracker.sql` în MySQL Workbench.  
- Verifică și configurează conexiunea la baza de date în `JdbcConnector.java`.

3️⃣ **Rulează aplicația**  
- Deschide proiectul într-un IDE precum **IntelliJ IDEA** sau **Eclipse**.  
- Rulează `MainScreen.java` pentru a porni aplicația.

## 📌 Capturi de ecran
📸 *Adaugă imagini ale interfeței grafice aici pentru o mai bună prezentare.*

## 📌 Contribuție
Dacă dorești să contribui la acest proiect:  
1. Fă un **fork** al repository-ului  
2. Creează un **branch nou** (`git checkout -b feature-noua`)  
3. Fă modificările și creează un **pull request**  

## 📜 Licență
📄 Proiectul este licențiat sub **MIT License** – poate fi utilizat, modificat și distribuit liber.

---

🔗 **Repository GitHub**: *[Adaugă link-ul repository-ului aici]*

