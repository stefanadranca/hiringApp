# hiringApp

Este necesar sa importati libraria externa “com.googlecode.json-simple:json-simple:1.1.1”
pentru a putea testa aplicatia (este necesara pentru parsarea fisierului json). Pentru a o importa:
- Deschideti proiectul in Intellij
- Click dreapta pe proiect (“TEMAbun”) si selectati “Open Module Settings”
- Selectati “Global Libraries”, apasati pe plus, selectati “From Maven”
- Cautati “com.googlecode.json-simple:json-simple:1.1.1” si sapasati “ok”
Toate clasele care tin de Arhitectura aplicatiei contin toate metodele cerute in enunt. De asemenea,
am implementat si Sabloanele de proiectare din cerinta 3: Application –
Singleton, Oberver/Subject – User/Company, Builder – ResumeBuilder si Factory –
DepartmentFactory. Faptul ca Application este Singleton ne ajuta sa putem gasi utilizatorii si
comaniile inregistrate in aplicatie si in alte clase.
Pentru testarea aplicatiei am introdus companiile si departamentele acestora in aplicatie.
Apoi, am parsat fisierul consumers.json cu ajutorul metodelor din clasa Test.
In implementare am folosit obiecte ale claselor JSONParser, JSONObject si JSONArray pentru
a extrage datele din fisier si pentru a crea instantele corespunzatoare de User, Manager,
Employee si Recruiter. Pentru testarea functionalitatii metodelor se ruleaza Test.
In output se observa lista de angajati pe departamente a fiecarei companii, numele
managerului si posturile pe care au fost angajati utilizatorii dupa apelarea metodei
de process pentru fiecare job – metoda se aplica instantelor de manageri corespunzatoare.
Pentru testarea interfetei grafice se ruleaza Test. Se va deschide o pagina de login.
Pentru a intra in aplicatie drept admin si pentru a vizualiza Admin Page-ul, trebuie
introdus “admin” atat ca email, cat si ca parola si selectat butonul de admin.
Pe Admin Page putem vedea toti utilizatorii, dar si toate companiile si odata ce este
selectata una, departamentele ei, iar dupa ce se selecteaza si un departament, si
angajatii si joburile din acel departament. Daca dupa selectarea unui departament
se apasa pe “Afiseaza salariu” se calculeaza totalul salariilor din acel departament.
Pentru a intra in aplicatie drept un utilizator, trebuie introdus un email valid al
unui utilizator inscris (spre ex: “dedmund@gmail.com”) si o parola oarecare, si
trebuie selectat campul “user”. Atentie! Daca un utilizator a fost angajat, atunci
nu se mai poate conecta ca user. Pentru a testa logarea mai multor utilizatori din
consumers.json comentati liniile care contin procesarea joburilor (si angajarea
  utilizatorilor) – liniile 412-415 din Test. Daca va logati dupa procesarea
  joburilor cu mailul “dedmund@gmail.com” veti putea vedea notificarile aparute
  in urma angajarilor facute. Daca apasati pe Profile Page se deschide aceasta pagina,
  in care cautand un nume valid de user, spre exemplu “Daniel Edmund” putem vizualiza CV-ul acestuia.
Pentru a intra in aplicatie drept manager, trebuie introdus un email valid al unui
manager inscris, (“spichai@gmail.com” sau “jbezos@amaon.com”), o parola oarecare
si trebuie selectat campul manager. In continuare va aparea pagina ManagerPage
in care apar toate requesturile primite de acesta.
Daca doriti sa testati separat paginile AdminPage, ProfilePage si ManagerPage,
decomentati cate o linie din liniile 443-445 din Test.
