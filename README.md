# Taskmaster

## Projekt leírása
A **Taskmaster** egy egyszerű, feladatkezelő alkalmazás, amely lehetővé teszi a felhasználók számára, hogy feladatokat hozzanak létre, módosítsanak, kommenteljenek és feladatokat töröljenek.
## Fejlesztési környezet
- **Backend**: Java, Spring Boot
- **Adatbázis**: H2 (fejlesztési szakaszban), később MySQL
- **Frontend**: Thymeleaf
- **Jogosultságkezelés**: Spring Security
- **Adatbázis kezelés**: JPA

## Funkciók
- Feladatok létrehozása, módosítása, törlése.
- A felhasználó megadhatja a feladat státuszát, annak szerkeztésekor.
- Logikai törlés a feladatoknál és kommenteknél (nem fizikailag törli az adatokat).


## Felhasználói jogosultságok
- A rendszer Spring Security alapú bejelentkezést használ.
- Csak a feladat készítője szerkesztheti/törölheti a saját feladatait.
- Szinte minden végpont jogosultságokkal védett.

## Fejlesztési terv
1. **fázis**: A backend REST API végpontok létrehozása Spring Boot-ban, H2 adatbázissal.
2. **fázis**: Felhasználói jogosultságok bevezetése Spring Security segítségével.
3. **fázis**: H2 átváltása MySQL-re.
4. **fázis**: React frontend fejlesztése, amely a REST API-val kommunikál.


## Jövőbeli bővítések
- E-mail értesítések feladatok módosításakor.
- Feladatok átruházása más felhasználókra.
- Fejlettebb jogosultságkezelés adminisztrációval.
- Backend api készítése
- Mysql migráció
- React frontend

## Készítók
Név: Béres Dániel
E-mail: danielberes5@gmail.com
