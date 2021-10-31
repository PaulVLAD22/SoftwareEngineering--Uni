# iLock - Document de analiză a cerințelor clientului

## Scopul aplicației

Scopul aplicației este sa facem accesul oamenilor în propriile locuințe mai sigur și mai ușor folosind tehnologii precum "Face Recognition" și "Fingerprint Scanner". Prin crearea unui Smart Lock, "iLock", revolutionam un domeniu care a evoluat minimal în ultima sută de ani.

## Obiectivele aplicației
<ul>
  <li>Autorizarea deschiderii lock-ului prin metode biometrice rapide fingerprint reader, face recognition.</li>
  <li>Securitate oferita prin setarea unui numar de telefon ce va fi apelat in cazul accesului interzis succesiv.</li>
  <li>Software user friendly care sa prezinte solutie de back-up in cazul uitarii PINului.</li>
  <li>Oferirea optiunii de a deschide lock-ul de la distanta.</li>
  <li>Setarea unui time-frame in care lock-ul poate fi utilizat.</li>
</ul>

## Grupul țintă

Aceasta aplicatie este adresată persoanelor care își doresc un mediu cat mai securizat și mai inteligent. Ne dăm seama de acest grup tinta ascultând de User Stories identificate online : 
<ul>
  <li>“Îmi doresc sa pot intra mai ușor în casa folosind amprenta, în loc sa imi car cheia mereu cu mine”</li>
  <li>"Ca Office Manager imi doresc sa dau mai usor acces angajatilor in diferite incaperi pastrand filtrarea persoanelor care au acces prin metode biometrice."</li>
  <li>“Ca locatar intr-un cartier foarte populat  in cazul în care un necunoscut încearca sa intre-n casa, as vrea sa am o poza cu fata lui în momentul în care greseste PINul sau pune amprenta neinregistrata”</li>
  <li>“Ca Security Manager as vrea ca sistemul sa cheme automat autoritățile atunci cand o persoana încearcă obsesiv sa intre, greșind de multe ori PIN-ul”</li>
  <li>“Ca Teen Tech Enthusiast mi-ar plăcea sa fiu identificat chiar și prin face recognition, exact ca pe telefonul meu”</li>
  <li>“In cazul unei renovari cred ca ar fi folositor sa pot da accesul cuiva în casa de la distanța, prin telefon”</li>
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
</ul>
 
 
## Gruparea cerințelor 

### Unlocking Methods:
<ul>
  <li>Deblocare prin fingerprint</li>
  <li>Deblocare prin face recognition</li>
  <li>Deblocare prin cod pin</li>
  <li>Deblocare prin NFC</li>
  <li>Generare cod one time use</li>
</ul>

### Security:
<ul>
  <li>Stocarea informatiilor legate de contextul in care s-au facut multiple incercari gresite de deblocare</li>
</ul>
 
### User Experience:
<ul>
  <li>Trimiterea de email-uri prin API extern pentru a comunica informatii detinatorului</li>
  <li>Resetarea codului pin</li>
  <li>Setari pentru interzicerea deblocarii dupa diferiti parametrii</li>
</ul>

## Github Link 
https://github.com/PaulVLAD22/SoftwareEngineering--Uni 

## Play planning poker.

<ul>
  <li>Deblocare prin fingerprint <b>7  3-4</b></li>
  <li>Deblocare prin face recognition <b>7 6-7</b></li>
  <li>Deblocare prin cod pin (primind string) <b>2 1</b></li>
  <li>Deblocare prin NFC (primind array de bytes) <b>6 6</b></li>
  <li>Setari pentru interzicerea deblocarii dupa diferiti parametrii <b>4 6</b></li>
  <li>Generare cod one time use <b>3 4</b></li>
  <li>Trimiterea de email-uri prin API extern pentru a comunica informatii detinatorului <b>5 6-7</b></li>
  <li>Resetarea codului pin <b>3 4</b></li>
  <li>Stocarea informatiilor legate de contextul in care s-au facut multiple incercari gresite de deblocare <b>5 6-7</b></li>
</ul>
 
## Plot the issues.  
<ul>
  <li> Deblocare prin fingerprint <b>dificil-valoros</b> </li>
  <li> Deblocare prin face recognition	<b>dificil-nevaloros</b></li>
  <li> Deblocare prin cod pin (primind string)  <b>usor-valoros</b></li>
  <li> Deblocare prin NFC (primind array de bytes) 	<b>dificil-nevaloros</b></li>
  <li> Setari pentru interzicerea deblocarii dupa diferiti parametrii	 <b>usor-nevaloros</b></li>
  <li> Generare cod one time use <b>usor-valoros</b></li>
  <li> Trimiterea de email-uri prin API extern pentru a comunica informatii detinatorului <b>dificil-nevaloros</b></li>
  <li> Resetarea codului pin <b>usor-valoros</b></li>
  <li>Stocarea informatiilor legate de contextul in care s-au facut multiple incercari gresite de deblocare <b>dificil-nevaloros </b></li>
</ul>
