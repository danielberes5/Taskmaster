# Taskmaster

## Projekt leírása
A **Taskmaster** egy egyszerű, feladatkezelő alkalmazás, amely lehetővé teszi a felhasználók számára, hogy feladatokat hozzanak létre, módosítsanak, kommenteljenek és feladatokat töröljenek.
## Fejlesztési környezet
- **Backend**: Java, Spring Boot
- **Adatbázis**: H2 (fejlesztési szakaszban), később MySQL
- **Frontend**: React
- **Jogosultságkezelés**: Spring Security
- **Adatbázis kezelés**: JPA

## Funkciók
- Feladatok létrehozása, módosítása, törlése.
- Kommentek hozzáadása feladatokhoz.
- Logikai törlés a feladatoknál és kommenteknél (nem fizikailag törli az adatokat).

## Architektúra
- **Backend**: REST API Spring Boot használatával.
- **Frontend**: React alapú felhasználói felület, amely REST API-kon keresztül kommunikál a back-enddel.
- **Adatbázis**: H2 (fejlesztési célra), majd MySQL-re migrálás a későbbiekben.
- **Jogosultságkezelés**: Spring Security, felhasználó-specifikus jogosultságokkal.

## Adatmodell
### Task (Feladat)
- `id`: Egyedi azonosító
- `title`: A feladat címe
- `description`: Részletes leírás
- `createdAt`: Létrehozás időpontja
- `updatedAt`: Utolsó módosítás időpontja
- `deleted`: Logikai törlés jelző (boolean)

### Comment (Komment)
- `id`: Egyedi azonosító
- `taskId`: A hozzátartozó feladat azonosítója
- `content`: A komment tartalma
- `createdBy`: A komment készítőjének felhasználóazonosítója
- `createdAt`: Létrehozás időpontja
- `updatedAt`: Utolsó módosítás időpontja
- `deleted`: Logikai törlés jelző (boolean)

### User (Felhasználó)
- `id`: Egyedi azonosító
- `username`: Felhasználónév
- `password`: Jelszó (titkosítva)
- `roles`: Jogosultsági szerepkörök

## API Végpontok

### Feladatok kezelése:
- `GET /tasks`: Összes feladat lekérdezése.
- `GET /tasks/{id}`: Adott id-val rendelkező feladat lekérdezése.
- `POST /tasks`: Új feladat létrehozása.
- `PUT /tasks/{id}`: Feladat szerkesztése.
- `DELETE /tasks/{id}`: Feladat logikai törlése.

### Kommentek kezelése:
- `POST /tasks/{id}/comments`: Új komment hozzáadása a feladathoz.
- `PUT /comments/{id}`: Komment szerkesztése.
- `DELETE /comments/{id}`: Komment logikai törlése.

## Felhasználói jogosultságok
- A rendszer Spring Security alapú bejelentkezést használ.
- Csak a feladat készítője szerkesztheti/törölheti a saját feladatait és kommentjeit.
- Minden végpont jogosultságokkal védett.

## Fejlesztési terv
1. **fázis**: A backend REST API végpontok létrehozása Spring Boot-ban, H2 adatbázissal.
2. **fázis**: React frontend fejlesztése, amely a REST API-val kommunikál.
3. **fázis**: H2 átváltása MySQL-re.
4. **fázis**: Felhasználói jogosultságok bevezetése Spring Security segítségével.

## Jövőbeli bővítések
- E-mail értesítések feladatok módosításakor.
- Feladatok csoportosítása projektekbe.
- Fejlettebb jogosultságkezelés adminisztrációval.

## Készítók
Név: Béres Dániel
E-mail: danielberes5@gmail.com
