# iLock - Document de analiză a cerințelor clientului

## Scopul aplicației

Scopul aplicației este sa facem accesul oamenilor în propriile locuințe mai sigur și mai ușor folosind tehnologii precum "Face Recognition" și "Fingerprint Scanner". Prin crearea unui Smart Lock, "iLock", revolutionam un domeniu care a evoluat minimal în ultima sută de ani.

## Obiectivele aplicației

Aplicatia noastra are ca obiectiv sa ofere o soluție sigură și ieftina, capabila de securizarea locuinței sau a altor bunuri, reducand astfel riscul unei spargeri avand de asemenea abilitatea de a memora fata unui posibil intrus și de a înștiința autoritățile în acest caz.

## Grupul țintă

Aceasta aplicatie este adresată persoanelor care își doresc un mediu cat mai securizat și mai inteligent. Ne dăm seama de acest grup tinta ascultând de User Stories identificate online : 
<ul>
  <li>“Îmi doresc sa pot intra mai ușor în casa folosind amprenta, în loc sa imi car cheia mereu cu mine”</li>

  <li>"Ca Office Manager imi doresc sa dau mai usor acces angajatilor in diferite incaperi pastrand filtrarea persoanelor care au acces prin metode biometrice."</li>

  <li>“Ca locatar intr-un cartier foarte populat  in cazul în care un necunoscut încearca sa intre-n casa, as vrea sa am o poza cu fata lui în momentul în care greseste PINul sau pune amprenta neinregistrata”</li>

  <li>“As vrea ca sistemul sa cheme automat autoritățile atunci cand o persoana încearcă obsesiv sa intre, greșind de multe ori PIN-ul”</li>
  <li>“Mi-ar plăcea sa fiu identificat chiar și prin face recognition, exact ca pe telefonul meu”</li>
  <li>“Am nevoie de un sistem unde e ușor de schimbat PIN-ul sau amprenta.”</li>
  <li>“Cred ca ar fi folositor sa pot da accesul cuiva în casa de la distanța, prin telefon”</li>
</ul>

### Colectarea cerințelor

iLock oferă posibilitatea deblocarii prin:
<ul>
  <li>Amprenta folosind un fingerprint scanner încorporat</li>
  <li>Face recognition</li>
  <li>Cod pin</li>
  <li>În cazul în care codul a fost uitat, este apăsat butonul "Reset" și se cere introducerea codului specific iLock-ului (primit la cumparare fizic).</li>
  <li>În cazul nerecunoasterii amprentei sau introducerii unui pin greșit se va realiza o poza folosind camera incorporata și se va afișa un mesaj de avertisment. Dacă utilizatorul va continua sa introducă un input eronat, se vor apela autoritățile.</li>
  <li>Dispozitivul mai oferă posibilitatea, prin intermediul aplicației de pe telefon, a deblocarii de la distanță.</li>
</ul>



## Interpretarea și prioritizarea cerințelor  
### Cerinte functionale:
<ul>
  <li>Deblocare prin fingerprint</li>
  <li>Deblocare prin face recognition </li>
  <li>Deblocare prin cod pin (primind string)</li>
  <li>Setari pentru interzicerea deblocarii dupa diferiti parametriiDeblocare prin NFC (primind array de bytes)</li>
  <li>Generare cod one time use</li>
</ul>
<br/>
### Cerinte non-functionale:
<ul>
  <li>Trimiterea de email-uri prin API extern pentru a comunica informatii detinatorului</li>
  <li>Resetarea codului pin</li>
  <li>Stocarea informatiilor legate de contextul in care s-au facut multiple incercari gresite de deblocare</li>
  <li></li>
<ul/>
 
 
2. Gruparea cerințelor 
Identificați criterii de gruparea cerințelor care ulterior credeți că vă vor ajuta la dezvoltare.  
 - Folosiți criterii pentru a grupa cerințele într-un mod care vi se pare util – după zona de tehnologie (BE, DevOps ș.a.) după eventualele module ale app (comunicare, procesare, stocarea datelor ș.a.), după feature-uri. Menționați aceste categorii și în documentul de analiză. 
 
Unlocking Methods:
 
Deblocare prin fingerprint
Deblocare prin face recognition
Deblocare prin cod pin
Deblocare prin NFC
Generare cod one time use
 
Security:
 
Stocarea informatiilor legate de contextul in care s-au facut multiple incercari gresite de deblocare
 
User Experience:
 
Trimiterea de email-uri prin API extern pentru a comunica informatii detinatorului
Resetarea codului pin
Setari pentru interzicerea deblocarii dupa diferiti parametrii
 
3. Github: https://github.com/PaulVLAD22/SoftwareEngineering--Uni
Resources: 
The Product Backlog: A Step-by-step Guide În general sunt bune articolele de la toptal pe software engineering.  
 
4. Play planning poker.
 
 Deblocare prin fingerprint 7  3-4
Deblocare prin face recognition 7 6-7
Deblocare prin cod pin (primind string) 2 1
Deblocare prin NFC (primind array de bytes) 6 6
Setari pentru interzicerea deblocarii dupa diferiti parametrii 4 6
Generare cod one time use 3 4
Trimiterea de email-uri prin API extern pentru a comunica informatii detinatorului 5 6-7
Resetarea codului pin 3 4
Stocarea informatiilor legate de contextul in care s-au facut multiple incercari gresite de deblocare 5 6-7
 
 
5. Plot the issues.  
 Deblocare prin fingerprint 	dificil-valoros
Deblocare prin face recognition	 dificil-nevaloros
Deblocare prin cod pin (primind string) 	usor-valoros
Deblocare prin NFC (primind array de bytes) 	dificil-nevaloros
Setari pentru interzicerea deblocarii dupa diferiti parametrii	 usor-nevaloros
Generare cod one time use 3 4	usor-valoros
Trimiterea de email-uri prin API extern pentru a comunica informatii detinatorului 5 6-7  dificil-nevaloros
Resetarea codului pin 3 4		usor-valoros
Stocarea informatiilor legate de contextul in care s-au facut multiple incercari gresite de deblocare dificil-nevaloros
 
 
6. Check the features.  - Good
 
7. Add technical issues. - Done
 
 
Alocarea rolurilor 
 Vlad Munteanu - Deblocare cod pin
Cristian Grecu - dev setup, email api, unit/integration testing
 
 
Documentul de analiză va fi adăugat în GitHub-ul proiectului.  


