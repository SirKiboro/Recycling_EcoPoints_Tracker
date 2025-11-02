# 🌍 Eco-Points Recycling Tracker

A Java-based console application that promotes environmental responsibility by tracking household recycling activity and rewarding users with **Eco-Points** based on material type and weight collected.

---

## 🧠 Project Overview

The **Eco-Points Recycling Tracker** system allows users to:
- Register households.
- Log recycling events with material type and weight.
- Automatically calculate eco-points based on recyclable material.
- View detailed recycling records per household.
- Generate summarized reports for all households.
- Save and load data using **serialization** to persist information between sessions.

Essentially, it turns good recycling habits into quantifiable achievements — because saving the planet should feel as satisfying as compiling code without an error.

---

## ⚙️ Core Features

| Feature | Description |
|----------|-------------|
| 🏠 **Register Household** | Add a new household with ID, name, and address. |
| ♻️ **Log Recycling Event** | Record a recycling entry for a specific household. |
| 🧮 **Eco-Points Calculation** | Automatically calculates points per material type. |
| 💾 **Data Persistence** | Saves and loads all household data via `households.ser` file. |
| 📊 **Reports** | Displays household data and cumulative eco-points. |

---

## 📐 Eco-Points Formula

| Material Type | Code | Points per kg |
|----------------|------|---------------|
| Plastic | `pl` | 25 pts |
| Glass | `g` | 15.5 pts |
| Metal | `m` | 30 pts |
| Paper | `pp` | 10 pts |

**Example:**  
If a household recycles 5 kg of metal → `5 × 30 = 150 eco-points`.

---

## 🧩 Project Structure

```
EcoPointsRecyclingTracker/
├── EcoPointsRecyclingTracker.java   # Main class with menu and program logic
├── HouseHold.java                   # Handles household data and events
├── RecyclingEvent.java              # Defines recycling event and eco-point calculation
├── households.ser                   # Serialized file storing saved data
└── README.md                        # You’re reading it right now
```

---

## 🚀 How to Run

1. **Compile the code**
   ```bash
   javac EcoPointsRecyclingTracker.java HouseHold.java RecyclingEvent.java
   ```

2. **Run the program**
   ```bash
   java EcoPointsRecyclingTracker
   ```

3. **Choose from the main menu:**
   ```
   1. Register Household
   2. Log Recycling Event
   3. Display Households
   4. Display Household Recycling Events
   5. Generate Reports
   6. Save and Exit
   ```

---

## 💡 Example Output

```
Recycling event logged successfully!
Material: Plastic
Weight: 4.0 kg
Points Earned: 100.0
Date: 2025-11-02
```

And the generated report:

```
Household ID: HH001 | Name: Family1 | Address: Estate 1
Plastic - 4.0 kg - 100.0 pts
Metal   - 3.5 kg - 105.0 pts
Total Points: 205.0
```

---

## 🧠 Concepts Demonstrated

- Object-Oriented Programming (Encapsulation, Composition, Reuse)
- Exception Handling
- File I/O and Serialization
- Data Validation
- Modular Code Design

---

## 🏁 Future Enhancements

- GUI interface (JavaFX or Swing) for a cleaner user experience.
- CSV or database export for data analysis.
- Points leaderboard for top recyclers.
- Integration with mobile apps for tracking on the go.

---

## 🤝 Contributors

Developed by **SirKiboro** — powered by caffeine, recursion, and mild frustration at compiler errors.  
