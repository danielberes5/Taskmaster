# Taskmaster

## Projekt leírása
A **Taskmaster** egy egyszerű, feladatkezelő alkalmazás, amely lehetővé teszi a felhasználók számára, hogy feladatokat hozzanak létre, módosítsanak, kommenteljenek és feladatokat töröljenek. A rendszer felhasználó-specifikus jogosultságokat biztosít.

## Fejlesztési környezet
- **Backend**: Java, Spring Boot
- **Adatbázis**: H2 (fejlesztési szakaszban), később MySQL
- **Frontend**: React
- **Jogosultságkezelés**: Spring Security
- **Adatbázis kezelés**: JPA/Hibernate

## Funkciók
- Feladatok létrehozása, módosítása, törlése.
- Kommentek hozzáadása feladatokhoz.
- Csak a komment készítője módosíthatja/törölheti a saját kommentjeit.
- Felhasználók jogosultság alapú hozzáférése a feladatokhoz.
- Logikai törlés a feladatoknál és kommenteknél (nem fizikailag törli az adatokat).
- Időbélyeg alapú rendezés.

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
- `status`: A feladat állapota (pl. "nyitott", "folyamatban", "lezárt")
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
- `POST /tasks`: Új feladat létrehozása.
- `PUT /tasks/{id}`: Feladat szerkesztése.
- `DELETE /tasks/{id}`: Feladat logikai törlése.

### Kommentek kezelése:
- `POST /tasks/{id}/comments`: Új komment hozzáadása a feladathoz.
- `PUT /comments/{id}`: Komment szerkesztése.
- `DELETE /comments/{id}`: Komment logikai törlése.

## Adatbázis migráció
- Fejlesztés során H2 adatbázist használunk, amelyet később MySQL-re váltunk.
- Az adatbázis-váltás Spring profillal és JPA/Hibernate konfigurációval történik.

## Felhasználói jogosultságok
- A rendszer Spring Security alapú bejelentkezést használ.
- Csak a feladat készítője szerkesztheti/törölheti a saját feladatait és kommentjeit.
- Minden végpont jogosultságokkal védett.

## Fejlesztési terv
1. **fázis**: A backend REST API végpontok létrehozása Spring Boot-ban, H2 adatbázissal.
2. **fázis**: React frontend fejlesztése, amely a REST API-val kommunikál.
3. **fázis**: H2 adatbázis migrációja MySQL-re.
4. **fázis**: Felhasználói jogosultságok bevezetése Spring Security segítségével.

## Jövőbeli bővítések
- E-mail értesítések feladatok módosításakor.
- Feladatok csoportosítása projektekbe.
- Fejlettebb jogosultságkezelés adminisztrációval.

## Készítók
Név: danielberes5
E-mail: danielberes5@gmail.com
