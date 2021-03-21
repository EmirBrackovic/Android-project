# Goal And Habit Tracker

![Logo](logo.png "Logo")

---

### Kreator aplikacije

| Ime i prezime    | Email                    | Broj indexa |
| ---------------- | ------------------------ | ------------ |
| Emir   Bračković | brackovic97@gmail.com    | 64/IT-18     |
### Mentor
| Ime i prezime    | E-mail                    |
| ---------------- | ------------------------ | 
|Prof. Dr. Elmedin Selmanović | eselmanovic@pmf.unsa.ba    |


---

U dokumentaciji ispod je detaljno opisan rad aplikacije, kao i koncepata korištenih tokom rada. U pitanju je *Goal And Habit Tracker*, namijenjen korisnicima android uređaja. Ova aplikacija je osmišljena da prati svakodnevne aktivnosti korisnika. Korisnik može izabrati između pet osnovnih početnih kategorija u koje može zapisivati svoje rezultate. 
Osnovne kategorije su: *Medication* , *Workout* , *Eat* , *Sleep* , *Study* . Pored toga korisnik može dodati svoje kategorije koje može prilagođavati svojim potrebama. 
Napisana je u programskom jeziku *Kotlin*, koristeći najnovije i preporučene konvencije Android programiranja. Podešena je na engleski jezik.

### Struktura projekta

Aplikacija se sastoji od početnog zaslona na kojem je logo aplikacije, naslov aplikacije i dugme za nastavak, odnosno pokretanje glavnog dijela aplikacije. Klikom na dugme otvara se glavni menu u kojem su ispisane kategorije korisnika. Tačnije otvara se glavna **aktivnost**. 

Activity je osnovna klasa koja je zadužena za prikaz korisničkog okruženja i prihvatanje događaja koje izvršava korisnik. Activity predstavlja akcije odnosno aktivnosti naše android aplikacije. Kada se aplikacija pokrene, pokreće se specifična akcija. Ona je definisana u manifestu. Obično je to MainActivity klasa. Ova klasa se kreira pri kreiranju projekta. Klasa MainActivity nasljeđuje veoma bitnu klasu, AppCompatActivity. Ta klasa zapravo čini klasu MainActivity aktivnošću. AppCompatActivity je podklasa androida koja nam daje pristup modernim android mogućnostima na starijim android uređajima. Koristimo ovu klasu da napravimo našu aplikaciju dostupnom što većem broju uređaja. Aktvinosti ne koriste konstruktor već se "pregazi" metoda onCreate i postavlja se layout koji je povezan sa ovom aktivnošću. To postižemo metodom setContentView metodom koja kao parametar prima layout koji će aktivnost koristiti.

Za prikazivanje podataka i interakciju sa krajnjim korisnikom korišteni su *Fragmenti*.

## Fragmenti

[**Fragment**](https://developer.android.com/guide/components/fragments) je dio korisničkog interfejsa. Kombinacija fragmenata sačinjava jedan korisnički interfejs.
Mora pripadati aktivnosti, iako ima svoj ciklus života (*lifecycle*), svoju interakciju s korisnikom. Može se dodati ili ukloniti za vrijeme rada aktivnosti.
Može se smatrati kao "podaktivnost", koja je iskoristiva u drugim *Activity* klasama

Fragment prolazi kroz sličan proces kao i aktivnost. Ima svoje *lifecycle* metode, kao npr. `onCreate()`, `onStart()`, `onPause()`, `onStop()`, itd.

Najkorisnije lifecycle metode su:
- **onCreate()** :  poziva se pri samom kreiranju fragmenta, korisna je za početnu inicijalizaciju nekih parametara koje bi željeli sačuvat kada se fragment nastavi nakon zaustavljanja ili pauziranja.
- **onCreateView()** :  poziva se kada se prvi put User intefrace (UI) kreira za dati fragment, potrebno je vratiti *View* objekat koji je vrhovni element u XML-u tog fragmenta (*null* se vraća ukoliko fragment nema UI).
- **onPause()** :  Android OS poziva ovu metodu kada aplikacija izađe iz fokusa, ovdje se mogu  sačuvati neki parametri korisni za nastavak aplikacije. Npr. ako fragment sadrži neki tajmer, a korisnik dobije poziv, potrebno je sačuvati trenutno stanje kako bi se u `onResume()` lifecycle metodi to moglo pravilno iskoristiti.

U aplikaciji se koriste sljedeći fragmenti:
- MenuFragment
- ActivityFragment
- ActivityInstanceFragment
- DetailsFragment

Svaki fragment je opisan pojedinačno.

##### Menu Fragment

- U okviru ovog fragmenta kao što i samo ime kaže nalazi se glavni *menu*. U okviru menija imamo pet osnovnih početnih kategorija u koje može zapisivati svoje rezultate. Osnovne kategorije su: *Medication* , *Workout* , *Eat* , *Sleep* , *Study* . Pored toga korisnik može dodati svoje kategorije koje može prilagođavati svojim potrebama. Pored kategorija, ovaj fragment sadrži i *FloatingActionButton* koji služi za pozivanje *Dialog-a* za dodavanje nove kategorije. *Floating Action Button* ili *FAB* je kružni dugmić koji trigeruje primarnu akciju u aplikacijskom User interface-u, koju mu moramo sami zadati. Pored funckionalnosti, možemo mu uređivati izgled, veličinu i slično, kao što sam i uradio u svojoj aplikaciji. 

##### Activity Fragment 

- U okviru ovog fragmenta prikazuje nam se aktivnost, odnosno aktivnosti iz kategorije koju je korisnik odabrao. U ovoj aplikaciji napravio sam da postoje 3 vrste aktivnosti, a to su: *incremental* , *time* i *amount* (tačnije aktivnosti koja mjeri broj ponavljanja i može se povećavati i smanjivati, vremenska aktivnost koja mjeri vrijeme početka i vrijeme završetka i količinska aktivnost koja mjeri unesenu količinu tog dana). Također i u okviru ovog fragmenta imamo *Floating Action Button* *(FAB)* koji ovaj put omogućava dodavanje novog događaja (aktivnosti) u okviru date kategorije. U zavisnosti od vrste aktivnosti imamo tri slučaja, gdje pored navedenih stvari u *incremental* aktivnosti imamo dva dodatna dugmića na čiji klik se povećava vrijednost za 1.0 ili smanji. U *time* aktivnosti imamo također 2 dugmića, samo su ovaj put **Start** i **Stop** dugme, tačnije dugme za početak mjerenja koliko ta aktivnost traje i dugme za zaustavljanje istog. I treća opcija su *amount* aktivnosti i one imaju samo jedan dugmić *plus* koji služi za dodavanje unesene (upisane) količine. 

##### Activity Instance Fragment

- Ovo je fragment koji prikazuje "more info" (detaljnije informacije) za datu aktivnost koji može korisnik kliknuti u otvorenoj kategoriji. Tako npr. ako je vrsta aktivnosti *amonut* , a odabrana kategorija *Eat* , korisnik će vidjeti u jedinici mjerenja za tu kategoriju (kcal) odnosno u kalorijama koliko je svaki put kada je unosio podatke unio kalorija pri jelu. Ako je vrsta aktivnosti *time* korisniku će se ispisati vrijeme početka aktivnosti i vrijeme završetka aktivnosti. Za treću opciju tačnije *incremental* nisam kreirano ovaj tip fragmenta, jer nije bilo smisla. 

##### Details Fragment 

- Ovaj fragment je po svojoj složenosti najjednostavniji. Pristupa mu se iz "glavnog menija", odnosno *MenuFragmenta* uz pomoć bočnog menija. U okviru ovog fragmenta ispisani su podaci o aplikaciji, tačnije informacije o kreatoru i mentoru ovog projekta. 

Za navigaciju između fragmenata, iskorišten je **Navigation** paket iz **AndroidX** Namespace-a koji pripada novoj **Android jetpack** biblioteci. Navigation paket omogućava lakše kretanje između fragmenata i proslijeđivanje argumenata. Također reguliše takozvani *back stack*, koji je u potpunosti opisan svojim imenom, na koji fragment ili na koju aktivnost ćemo otići ako se u datom trenutku pritisne *back* dugme. 

Informacije i postavke navigacije u okviru ove aplikacije možemo naći u *RES* folderu, u datotieci "**navigation.xml**" . U našem slučaju navigacija je implementirana na slj. način: 
- action *Menu Fragment* to *Details Fragment* ( Iz glavnog menija u fragment koji sadrži informacije o aplikaciji )
- action *Details Fragment* to *Menu Fragment* ( Ista putanja kao i prošla akcija samo u drugom smjeru, uz pomoć *back* dugmića )
- action *Menu Fragment* to *Activity Fragment* ( Iz glavnog menija u fragment koji otvara kategoriju i prikazuje sve aktivnosti iz iste )
- action *Activity Fragment* to *Activity Instance Fragment* (Iz fragmenta kategorije u detaljniji opis odabrane aktivnosti )
- action *Activity Instance Fragment* to *Activity Fragment* 
- i naravno action *Activity Fragment* to *Activity Instance Fragment* ( Ista putanja u oba slučaja samo u obrnutom smjeru )

---

Za kontrolu tzv. promjene konfiguracije (*Configuration change*) je iskorištena klasa [**ViewModel**](https://developer.android.com/reference/androidx/lifecycle/ViewModel). Android promjene konfiguracije smatra kao drastične promjene u User interfaceu (UI), pa i reaguje tako što restartuje trenutno aktivni fragment ili aktivnost, pri čemu dolazi do gubljenja podataka. Primjer ovakvog događaja je promjena jezika aplikacije ili promjena orijentacije ekrana ( vertikalna i horizontalna ).

Za lokalnu bazu su implementirane Android [**RoomDatabase**](https://developer.android.com/reference/androidx/room/RoomDatabase) i [**Room**](https://developer.android.com/reference/androidx/room/Room) klase.
O ovim principima će detaljnije biti objašnjeno u nastavku dokumentacije .

---

## Arhitektura 

Arhitektura koja je  korištena za razvoj aplikacije je MVVM (Model, View, ViewModel). Značenje ovih komponenata je:
- **Model**: dio koji je zadužen za čuvanje podataka, ne smije direktno komunicirati sa *View* dijelom, inače se narušava smisao koncepta. Podatke kontroliše ViewModel.
- **View**: UI dio arhitekture bez ikakve poveznice sa biznis logikom aplikacije. Veoma koristan koncept su *Observeri* koji osluškuju promjene podataka, na osnovu kojih se mijenja stanje UI.
- **ViewModel**: Poveznica između Model i View dijelova. Zadužen je za mijenjanje podataka, slušanje *callback* funkcija nastalih interakcijama nad korisničkim interfejsom.

Pored jasnijeg koda i bolje strukture aplikacije, ova arhitektura je korisna za odvajanje biznis logike od User Interface elemenata, popravljanje sitnih bugova, lakšeg oporavka od konfiguracijskih promjena u aplikaciji.

Veliku korist ovoj arhitekturi daje LiveData objekat, koji ima uvid u *lifecycle* metode unutar fragmenta ili aktivnosti u kojem se nalazi. LiveData je preporučeni način slanja podataka u User Interface komponente.

ViewModel je klasa zadužena za serviranje podataka prema *Model* (fragmenti i aktivnosti) dijelu MVVM arhitekture. 

ViewModel je implementiran tako da se veže za jedan određeni fragment ili aktivnost, životni vijek je duži od klase za koju se veže, stoga je idealan za čuvanje podataka. Drugim riječima, prilikom promjene konfiguracije, fragment ili aktivnost će se restartovati, ViewModel ne, već će se ponovo povezati s instancom tog fragmenta ili aktivnosti.

Podaci u ViewModel-u se trebaju čuvati kao `LiveData`, jer *LiveData* omogućava "posmatranje" podataka, uvid u brisanje, dodavanje, mijenjanje podataka.
Iz UI je moguće osluškivati promjene tih podataka kroz `Observer` klasu, time programeri mogu da ažuriraju UI, bez čuvanja podataka u istoj klasi.

Podatke koje ne treba čuvati unutart ViewModela su:
- **context**: Slanjem Context podataka u ViewModel, dolazi do curenja memorije i nije preporučeno (isto vrijedi i za referencu na aktivnost ili fragment).
- **Views**: ViewModel *ne* treba da pristupa ni u koje slučaju hijerarhiji korisničkog interfejsa.

Više fragmenata se može povezati s jednom instancom ViewModel klase, pa je to čini idealnom za slanje podataka između fragmenata, ukoliko se radi o podacima koji nisu primitivnog tipa .

U ovom projektu za svaki od već navedenih fragmenata postoji i posebno *ViewModel* fajl, a to su: 
- MenuViewModel
- ActivityViewModel
- ActivityViewModelFactory
- ActivityInstanceViewModel
- ActivityInstanceViewModelFactory

**MenuViewModel** sadrži spajanje sa našom bazom podataka i pozivanje funkcije iz baze pomoću koje prikazujemo kategoriju, odnosno sve kategorije koje su prikazane u *glavnom meniju* 

**ActivityViewModel** također uzima podatke iz baze podataka i prima vrijednost varijable *CategoryID*, koja je kao što i samo ime kaže id broj selektovane kategorije. Ovdje pored toga kreiramo i pozivamo funkcije koje su također kreirane u bazi podataka poput *insertIncrementalActivity*, *insertActivity*, *insertActivityInstance*, *updateActivityInstance*, *increaseValue* i *decraseValue* pomoću kojih se unose podaci u bazu, dodaju aktivnosti, povećavaju i smanjivaju vrijednosti u "increment" aktivnostima i slično.

**ActivityViewModelFactory** nam je potreban za *ActivityViewModel* i to je klasa koja implementira *ViewModelProvider.Factory* i to će kreirati *ViewModel* iz parametara tipa ".class" . Kada se pozove get() metoda *ViewModelProvider* poziva create() metodu iz *ViewModel Factory* i po tipu klase metoda vraća instancu *ViewModel-a*.

**ActivityInstanceViewModel** također dobija podatke iz baze podataka i prima 3 informacije *activityID*, *categoryID* I *UnitOfMeasure* koji označava jedinicu za aktivnosti tipa *Amount* gdje se mjeri unesena količina u odabranoj kategoriji. Pozivaju se dvije funkcije po potrebi koje su kreirane u okviru nje, a to su: *insertActivityInstance* (koja ubacuje novu instancu u okviru date aktivnosti) i *updateActivityInstance* koji osvježava podatke i prikazuje podatke u realtime-u . 

**ActivityInstanceViewModelFactory** ista priča kao i za *ActivityViewModelFactory*, poziva se metoda i pomoću provjere uz funkciju *isAssignableFrom* vraća  *ActivityInstanceViewModel* sa već gore navedenim parametrima (appContext, activityId, categoryId, unitOfMeasure).

---

##### Data Binding

Da bi smo koristili data binding u ovom projektu, prvo moramo odobriti korištenje data bindinga u fajlu build.gradle. Nakon toga omotamo xml layout u layout tag. Kreiramo binding varijablu i instanciramo je u onCreate funkciji. Nakon toga možemo pristupati elementima preko biding objekta (npr. binding.startButton). Na ovaj način kod postaje mnogo čitljiviji. Najveća prednost je pri korištenju samih podataka. Generalno *Data Binding Library* je pomoćna bibilioteka koja nam omogućava da spojimo User Interface komponente u našim Layoutima sa bazom podataka u našoj aplikaciji. Ova metoda je višestruko puta iskorištena u okviru ovog projekta i vrlo je korisna.

---

#### Room

Room je trenutno preporučeni način čuvanja podataka u androidu, tako da sam ga koristio u svojoj bazi podataka. Zanimljivo je da je u pozadini implementiran SQLite koji nije preporučeni način čuvanja podataka.

Room se sastoji od 3 glavne komponente:
1. Entity
2. DAO
3. Database
---
##### Entity

[**Entitet**](https://developer.android.com/reference/androidx/room/Entity) predstavlja tabelu unutar same baze. Potrebno je napraviti klasu (data class) sa anotacijom `@Entity()`

Kolone u ovom slučaju nisu ništa drugo osim atributa te klase, s tim da je potrebno anotacijom naglasiti da se radi o koloni, isto kao i sa `@Entity()` anotacijom.

Neke vrste anotacija:
- `@ColumnInfo()`: služi za označavanje obične kolone unutar baze, tip je određen eksplicitnim naglašavanjem u kotlin kodu. Unutar zagrada je moguće naglasiti `name` atribut, koji će se koristiti *isključivo* u SQL jeziku u upitima ka bazi.
- `@PrimaryKey()`: Označava kolonu koja je primarni ključ entiteta u kojem se nalazi. Kolona može biti automatski generisana ako navedemo `autoGenerated = true`. Kada entitet ima više primarnih ključeva (kompozitni ključ), potrebno je u `@Entity(primaryKeys = {})` dodati imena kolona koja sačinjavaju ključ.
- `@Relation()` : Umjesto npr. da koristimo dva upita da bi dobili neke podatke, korištenjem *Room* metodom ne trebaju ta dva upita, dovoljna nam je ova anotacija, koja se deklariše ovako: `@Relation()`( parentColumn = "", entityColumn = "" )

U ovom projetku napravio sam pet različitih *Entitiy* fajlova, a to su:
- Category  (`@PrimaryKey()` je *category_id* , a  `@ColumnInfo()`: *category_name , category_type , measure_unit , increment, created_at*)
- Activity (`@PrimaryKey()` je *activity_id* , a  `@ColumnInfo()`: *activity_name , category_id i created_at*)
- ActivityInstance (`@PrimaryKey()` je *instance_id* , a  `@ColumnInfo()`: *activity_id , value , start_time , end_time , created_at* )
- Full Activity  (`@Relation()`( *parentColumn = "activity_id"* , *entityColumn = "activitiy_id"* ))
- Property (`@PrimaryKey()` je *property_id* , a  `@ColumnInfo()`: *property_name, category_id* )

---
##### DAO

[**DAO**](https://developer.android.com/reference/androidx/room/Dao) je skraćenica za *Data access object*. Svrha ove klase je pokretanje upita nad bazom.

Deklariše se anotacijom `@Dao` iznad **interfejsa** objekta u kotlin kodu.
Svaki upit također mora biti označen jednom od sljedećih anotacija:
- `@Insert`: metoda koja prima odgovarajući objekat koji je potrebno unijeti u bazu, bilo da se radi o integeru ili nekom kompleksnom objektu.
- `@Query()`: Prima string u kojem se piše SQL query po želji. Inače se koristi za izvlačenje podataka. SQL query naveden u anotaciji mora navoditi eksplicitno navedena imena kolona (ukoliko ih ima).
- `@Delete`: Kao i *@Insert* ne prima nikakve parametre, već uzima objekat koji je proslijeđen funkciji, traži ga u toj tabeli i briše ako je pronađen.

Jedna prednost Room paketa je što RoomDatabase instanca ne ovisi o jednom DAO interfejsu. To znači da se za svaki entitet ili *layer* u bazi može kreirati svoj DAO, što rezultira ljepšem, jasnijem kodu i boljoj preglednosti.

---
#### Database

Klasa koja naslijeđuje *RoomDatabase* klasu, koristi se za kreiranje instance baze podataka. U ovoj aplikaciji je implementirana kao *singleton* klasa.
Da bi sistem pravilno protumačio instancu, potrebno je naglasiti `@Database()` anotaciju i prima razne parametre koji se mogu pronaći u Android developer [dokumentaciji](https://developer.android.com/reference/kotlin/androidx/room/Database). Jedan bitan parametar je *niz* entiteta.

Unutar klase definišemo sve DAO interfejse kao apstraktne metode, nad kojima će se pozivati upiti.

Pošto je navedeno da je klasa singleton, a u kotlinu ne postoje statički objekti, logika iza singleton klase je smjestena u `companion object`. Ako instanca postoji, treba je vratiti, u suprotnom poziva se funkcija `createInstance()` koja kreira novu instancu baze.

Kreiranje instance radi se sa [`Room.databaseBuilder()`](https://developer.android.com/reference/androidx/room/RoomDatabase.Builder) metodom, uz obično instanciranje pruža i mnoge druge pogodnosti, kao npr. kreiranje baze iz predefinisanog *"pre-packaged database"* fajla.

---

#### Dialog Fragment

Dialog fragment je fragment koji prikazuje *dialog* prozor koji se pojavljuje preko prozora aktivnosti. Ovaj fragmens sadrži Dialog ojekat koji će se prikazati. Kontrola dialoga ( kada se pojavljuje, sakriva i nestaje ) trebala bi se urediti pomoću API-ja a ne direktnim pozivima na dialog. Imprementacijom *Fragment.onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)* dobijamo sadržaj dialoga. Drugi način pozivanja je  *onCreateDialog(android.os.Bundle)* da se kreira potpuno *custom* dialog kao npr. *AlertDialog*, sa njegovim sadržajem.

U okviru naše aplikacije **Dialog Fragment** implementiran je dva puta, a to su: 
*Activity Dialog* i *Category Dialog*. 

**Activity Dialog** sam namjestio da se može pozvati samo u okviru neke kategorije, odnosno aktivnosti. Njegovim pozivom dobijamo *menu* za kreiranje nove aktivnosti unutar kategorije. On u sebi ima *prazno polje* u koje korisnik upisuje ime nove aktivnosti i dva dugmića *ADD* i *CANCEL* , tačnije za dodavanje nove aktivnosti i prekidanja procesa dodavanja, odnosno uklanjanja *Dialog-a* . 

**Dialog Fragment** sam namjestio da se može pozvati samo u okviru *glavnog menija* . Njegovim pozivom dobijamo skočni menu za kreiranje nove kategorije unutar menija . Kao i kod *Activity Dialog-a* on u sebi ima *prazno polje* u koje korisnik upisuje ovaj put ime nove kategorije. Pored toga postoji *drop-down menu* u kojem korisniku izlaze tri opcije novih kategorija i mora izabrati jednu od ponuđenih i također opet imamo dva dugmića *ADD* i *CANCEL*. 

---
#### Adapter 

Objekat **Adapter** ponaša se kao most između *AdapterView-a* i podataka za taj *view*. Adapter dopušta pristup podatkovnim artiklima (*itemim-a*). Adapter je također odgovoran za kreiranje *View-a* za svaki *item* u skupu podataka. 

*RecyclerView.Adapter* koji je najčešće korišten u okviru ove aplikacije omogućava spajanje skupa podataka aplikacije sa *View-s* i prikazuje ga kao *RecylcerView*. 

U okviru ovog projekta kreirano je više adaptera da bi omogućili njihov prikaz, a to su:
- Category Adapter (Adapter koji služi za izgled izgled odnosno ispis svih kategorija. U njemu postavljamo ime kategorije, ikonicu i slično, a on to kasnije spoji sa *RecyclerView-om*)
- Fragment Activity Instance Adapter ( Ovo je adapter koji služi za ispis instance aktivnosti, tačnije u dijelu "more info" ispisuje se lista svih vremenskih aktivnosti, kada su one počele i završile )
- Fragment Activity Instance Amount Adapter (Ovaj adapter je sličan *Fragment Activity Instance Adapteru* samo je razlika da se u ovom slučaju ispisuju količine koje je korisnik unio u aktivnosti, tačnije tip aktivnosti je iz kategorije *Eat* )
- Amount Adapter (Adapter koji nam omogućuje ispis kategorije koje imaju aktivnosti tipa *Amount* )
- Incremental Adapter (Adapter koji nam omogućuve ispis kategorije koje imaju aktivnosti tipa *Increment* )
- Time Adapter (Adapter koji nam omogućuje ispis kategorije koje imaju aktivnosti tipa *Time* )





