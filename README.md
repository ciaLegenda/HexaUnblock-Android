# HexaUnblock

## Mod de Rulare a Proiectului  
1. Deschide proiectul în **Android Studio**.  
2. Rulează aplicația pe dispozitivul dorit în **mod debugging** sau direct pe **emulator**.  

## Tehnologii Utilizate  
- **Android Canvas API** – folosit pentru randarea și gestionarea graficii jocului.  

## Scurtă Prezentare  
**HexaUnblock** este un joc puzzle pentru dispozitive Android. Scopul este să muți piesa roșie spre **partea dreaptă a tablei de joc**.  

###  Structura tablei  
- Formată din **hexagoane**, fiecare piesă ocupând unul sau mai multe hexagoane colorate.  
- Piesele pot fi mutate doar **într-o singură direcție**, pe un **spațiu liber** (hexagon gri).  

###  Direcții de mișcare  
1. **Stânga - Dreapta**  
2. **Diagonal Stânga-Jos ↘️ Dreapta-Sus ↖️**  
3. **Diagonal Stânga-Sus ↙️ Dreapta-Jos ↗️**  


![image](https://github.com/user-attachments/assets/442a1a24-acf4-4069-83df-821e6f41bf57)


## Funcționalități  

###  Generare Automată a Nivelurilor  
- Piesa roșie este plasată prima.  
- Se adaugă alte piese, evitând coliziunile.  
- Piesele sunt mutate **aleator** de mai multe ori, garantând un nivel **rezolvabil**.  

###  Indicii de Rezolvare (Hint System)  
- Se testează **toate mișcările posibile** folosind **BFS** (Breadth-First Search).  
- Se obțin toate configurațiile posibile, mutând piesele **pe rând** (1 poziție, apoi 2 etc.).  
- Configurațiile sunt memorate pentru a evita testarea repetată.  
- **Indicația pentru jucător** este afișată pe **hexagonul galben**.  

![HexaUnblock Hint](https://github.com/user-attachments/assets/7b15b3bf-c925-4737-812d-6e2e40e70d05)  

🚀 **Jocul este optimizat pentru performanță și oferă o experiență de joc fluidă și captivantă!**
