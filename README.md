**Design Pattern-uri folosite**
1. Singleton (pentru Database)
   - Deoarece clasa database reprezinta practic o baza de date in care sunt stocate toate datele necesare aplicatiei, aceasta trebuie sa previna instantierea multipla. Astfel, am ales sa folosesc design pattern-ul Singleton care are ca scop o singura instantiere globala a obiectului.
2. Builder (pentru Museum si Location)
   - Deoarece cele doua clase au mai multe campuri optionale, iar utilizarea constructorilor care se ocupa de toate atributele ar fi greu de citit/intretinut, am ales sa folosesc acest design pattern care permite crearea obiectelor cu ajutorul unui constructor in care este nevoie sa se specifice numai atributele obligatorii, celelalte putand fi adaugate prin alte metode.
3. Factory Method (pentru Person si subclasele Student, Professor)
   - Deoarece Student si Profesor sunt subclase ale Person, crearea obiectelor se face in mod dinamic in functie de datele primite in input. Acest design pattern ajuta la separarea logicii de creare de cea de utilizare efectiva si elimina eventuallul cod duplicat pentru intantierea obiectelor subclaselor, folosind un singur constructor care diferentiaza crearea in functie de datele primite.
4. Command (pentru procesarea comenzilor)
   - Am ales sa folosesc acest design pattern pentru a separa logica fiecarei comenzi intr-o clasa separata, evitand astfel supraincarcarea din main.
5. Observer (pentru notificarea ghizilor atunci cand sunt anuntate evenimente)
   - Deoarece muzeele trebuie sa trimita notificari numai ghizilor asociati cu acestea, atunci cand sunt anuntate evenimente, am ales sa folossc design pattern-ul observer care permite trimiterea mesajelor numai grupurilor relevante, un muzeu nefiind fortat sa ia in considerare toate grupurile existente in baza de date.

**Pachetul entities**
- cuprinde clasele care creaza obiectele prezente in baza de date
1. Database
    - atribute:
      - database - instanta bazei de date
      - museums - multimea muzeelor disponibile
      - groups - multimea grupuriloe
    - constructori:
      - initializarea set-urilor de muzee si de grupuri
    - metode:
      - getInstance - creaza o instanta obiectului database numai daca acesta nu a mai fost creat anterior
      - addMuseum - adauga un muzeu in lista de muzee si il printeaza in fisierul aferent
      - addMuseums - adauga o lita de muzee la set-ul de muzee specific clasei
      - addGroup - adauga un grup la lista de grupuri
      - addGroups - adauga o lista de grupuri la set-ul de grupuri
      - findGroup - cauta in lista de grupuri si returneaza grupul cu codul de muzeu si orarul dat
      - findMuseum - cauta in lista de muzee si returneaza muzeul cu codul primit ca parametru
2. Location
    - atribute:
      - obligatorii:
        - county - judetul locatiei
        - sirutaCode - codul locatiei
      - optionale:
        - locality - localitatea locatiei
        - adress - adresa locatiei
        - longitude - longitudinea locatiei
        - latitude - latitudinea locatiei
    - clasa Builder:
      - atribute:
          - aceleasi ca si clasa Location
      - constructori:
          - atribuie variablielor obligatorii valoarile primite ca parametrii
      - metode:
          - setLocality, setAdress, setLongitude, setLatitude - atribuie variabilelor optionale valoarea primita ca parametru si returneaza obiectul actualizat
          - build - practic functionalitatea de constructor a builder; returneaza un nou obiect de tip Location
   - constructor:
       - asociaza atributele clasei Location cu cele corespondente din clasa interna Builder
3. Museum
    - atribute:
      - obligatorii:
        - name - numele muzeului
        - code - codul aferent muzeului
        - supervisorCode - codul muzeului care are in subordine muzeul curent
        - location - locatia muzeului
      - optionale:
        - manager - persoana care este directorul muzeului
        - foundingYear - anul de inaugurare al muzeului
        - email - adresa de mail de contact a muzeului
      - observers - set de observatori (grupuri) aferenti muzeului
    - clasa Builder:
      - atribute:
        - aceleasi ca si clasa Museum
      - constructori:
        - atribuie variablielor obligatorii valoarile primite ca parametrii
      - metode: 
        - setManager, setFoundingYear, setEmail - atribuie variabilelor optionale valoarea primita ca parametru si returneaza obiectul actualizat
        - build - practic functionalitatea de constructor a builder; returneaza un nou obiect de tip Museum
    - constructor:
      - asociaza atributele clasei Museum cu cele corespondente din clasa interna Builder
    - metode:
      - getCode, getName - getteri pentru atribute
      - toString - string cu datele care trebuiesc afisate pentru un muzeu
      - addObserver - adauga un observator la lista de observatori a muzeului
      - removeObserver - elimina un observator din lista muzeului
      - notifyObservers - parcurge lista de observatori si ii notifica pe fiecare cand apare un eveniment
4. Observer
    - interfata pentru clasa Group; contine antetul metodei update
5. Group
    - atribute:
      - members - lista cu membrii unui grup
      - guide - ghidul grupului
      - museumCode - codul muzeului din care face parte grupul
      - timetable - intervalul orar al grupului
    - constructori;
      - fara parametrii - instanteaza o noua lista pentru membri
      - cu parametri - atribuie valorile primite ca parametrii atributelor clasei
    - metode:
      - getMembers, getMuseumCode, getTimetable, getGuide - gettere pentru atributele clasei
      - addGuide - adauga un ghid turului
      - addMember - adauga un membru in lista de membri a grupului
      - removeMember - elimina membrul dat ca parametru din lista de membri a grupului
      - resetGuide - reseteaza ghidul la null; practic sterge ghidul
      - findMember - cauta un membru in lista de membri a grupului si il returneaza in caz ca este gasit
      - update - functia care executa instructiunile primite de catre observer de la subject; afiseaza mesajul primit de ghid de la muzeu
6. FactoryPerson
    - createPerson - creaza un obiect de tip Student sau Profesor in functie de titlul primit la input
7. Person
    - atribute:
      - surname - prenumele persoanei
      - name - numele persoanei
      - role - rolul in cadrul grupului; vizitator/ghid
      - age - varsta persoanei
      - email - emailul persoanei
    - constructori:
      - initializeaza numele, prenumele si rolul cu datele primite ca parametru
    - metode:
      - setAge, setEmail - setteri pentru datele care nu sunt initializate in constructor
      - getEmail - getter pentru mail
      - toString - returneaza intr-un string datele necesare afisarii unei persoane; daca mailul nu exista; acesta este facut null
      - personEguals - verifica daca persoana primita ca parametru are aceleasi valori ale atributelor; practic este aceasi persoana, dar sunt 2 obiecte diferite
8. Professor (extinde clasa Person, deci mosteneste toate atributele si metodele)
    - atribute:
      - experience - anii de experienta ai profesorului
      - school - scoala la care preda profesorul
    - constructor:
      - apelez constructorul clasei parinte si initializez atributele specifice calsei curente
    - metode:
      - toString - returnez datele despre persoana si cele specifice profesorului necesare pentru afisare
9. Student (extinde clasa Person, deci mosteneste toate atributele si metodele)
    - atribute:
        - school - scoala la care invata elevul
        - studyYear - anul de invatamant al elevului
    - constructor:
        - apelez constructorul clasei parinte si initializez atributele specifice calsei curente
    - metode:
        - toString - returnez datele despre persoana si cele specifice elevului necesare pentru afisare

**Pachetul example**
1. Command
    - clasa abstracta care reprezinta o interfata pentru comenzi
    - contine antetul metodei execute
- la finalul tuturor claselor urmatoare prind si tratez exceptiile
2. AddMuseum
    - metoda execute:
      - seteaza locatia muzeului cu ajutorul clasei Builder si a metodei build
      - instantiaza un obiect Museum cu ajutorul clasei Builder si a metodei build
      - adauga muzeul in baza de date
3. AddGuide
   - metoda execute:
     - creez o persoana folosind factoryPerson cu datele pentru ghid
     - caut grupul cu codul de muzeu si oarul primite la input si il salvez in mainGroup
     - daca persoana este student arunc exceptia GuideTypeException
     - altfel, verific daca mainGroup exista si in caz afirmativ verific daca exista deja un ghid
     - daca ghidul exista arunc exceptia GuideExistsException, altfel adaug ghidul la grup
     - daca main group nu exista, creez un nou grup pe care il adaug la baza de date si ca observer la muzeul aferent
     - la final printez in fisier mesajul cu datele adaugate
4. FindGuide
   - metoda execute:
      - creez o persoana folosind factoryPerson cu datele pentru ghid
      - daca persoana este student arunc exceptia GuideTypeException
      - altfel, caut grupul cu codul de muzeu si oarul primite la input si il salvez in mainGroup
      - verific daca mainGroup exista si in caz afirmativ verific daca ghidul este egal cu persoana initializata anterior
      - daca sunt egale, printez mesaj de succes, altfel spun ca sunt diferiti
      - daca main group nu exista, arunc eroarea GroupNotExistsException
5. RemoveGuide
   - metoda execute:
      - creez o persoana folosind factoryPerson cu datele pentru ghid
      - caut grupul cu codul de muzeu si oarul primite la input si il salvez in mainGroup
      - verific daca mainGroup exista si in caz afirmativ resetez ghidul si printez mesaj de succes
      - altfel, arunc exceptia GroupNotExistsException
6. AddMember
   - metoda execute:
      - creez o persoana folosind factoryPerson cu datele pentru membru
      - caut grupul cu codul de muzeu si oarul primite la input si il salvez in mainGroup
      - verific daca mainGroup exista si in caz afirmativ verific daca numarul de membri este mai mic ca 10
      - daca este mai mic, adaug noul membru, altfel arunc exceptia GroupThresholdException
      - in cazul in care mainGroup nu exista, arunc exceptia GroupNotExistsException
      - la final printez in fisier mesajul de succes cu datele adaugate
7. FindMember
   - metoda execute:
      - creez o persoana folosind factoryPerson cu datele pentru membru
      - caut grupul cu codul de muzeu si oarul primite la input si il salvez in mainGroup
      - verific daca mainGroup exista si in caz afirmativ verific daca persoana initializata anterior se afla in lista de membri a grupului
      - daca se afla, printez mesaj de succes, altfel spun ca nu exista acea persoana la grupul dat
      - daca main group nu exista, arunc eroarea GroupNotExistsException
8. RemoveMember
   - metoda execute:
      - creez o persoana folosind factoryPerson cu datele pentru membru
      - caut grupul cu codul de muzeu si oarul primite la input si il salvez in mainGroup
      - verific daca mainGroup exista si in caz afirmativ caut persoana initializata anterior in lsita de membri a rupului
      - daca exista, o elimin din lista si afisez mesaj de succes, altfel arunc exceptia PersonNotExistsException
      - daca main group nu exista, arunc exceptia GroupNotExistsException
9. AddEvent
   - metoda execute:
     - caut muzeul in baza de date si il salvez
     - daca exista uzeul cautat notific toti observatorii aferenti
10. PathTypes
   - am adaugat metoda getType care primeste valoarea enum-ului si returneaza PathType-ul aferent valorii
11. Main
   - db - declar "global" instanta unica a bazei de date
   - metoda main:
     - verific cate argumente am primit
     - daca sunt 2, aflu PathType-ul si extrag path-ul dat ca argument, iar in functie de valoarea acestuia procesez informatiile despre muzee, grupuri sau evenimente
     - daca sunt 4, aflu PathType-ul si extrag path-urile si procesez informatiile de toate tipurile
   - metoda processMuseums:
     - creez mediul pentru afisare cu FileOutputStream si PrintStream, si mediul pentru citire cu Scanner
     - folosind scanner citesc fiecare linie a fisierului de citire, pe care o impart in componente folosind split
     - apelez metoda execute din clasa AddMuseum
     - tratez exceptiile si inchid stream-urile de citire si afisare
   - metoda processGroups:
      - creez mediul pentru afisare cu FileOutputStream si PrintStream, si mediul pentru citire cu Scanner
      - folosind scanner citesc fiecare linie a fisierului de citire, pe care o impart in componente folosind split
      - in functie de comanda citia (prima componenta) apelez metoda execute a clasei aferenta comenzii (AddGuide, FindGuide, RemoveGuide, AddMember, FindMember, RemoveMember)
      - tratez exceptiile si inchid stream-urile de citire si afisare
   - metoda processEvents:
      - creez mediul pentru afisare cu FileOutputStream si PrintStream, si mediul pentru citire cu Scanner
      - folosind scanner citesc fiecare linie a fisierului de citire, pe care o impart in componente folosind split
      - apelez metoda execute din clasa AddEvent
      - tratez exceptiile si inchid stream-urile de citire si afisare

**Pachetul exceptions**
- contine exceptiile personalizate utilizate in cadrul programului (GroupNotExistsException, GroupThresholdException, GuideExistsException, GuideTypeException, PersonNotExistsException)