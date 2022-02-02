# Manual de utilizare

[**<b>Async Api Documentation</b>**](/asyncapi.yaml)

[**<b>Document de analiză a cerințelor clientului</b>**](/analysis_document.md)

## Prerequisites:
- Server MySQL ruland la localhost:3306/ilock
- Server Mosquitto ruland la port 1883
- Maven
- JDK 1.8
- *Pentru utilizarea functionalitatii de face recognition* -
https://azure.microsoft.com/en-us/services/cognitive-services/face/#overview
- Clone project
- Instalarea dependintelor maven -
`mvn clean install`


## Flow

### 1. Inregistrare device
`GET| /api/pin/update`

### 2. Ready to use - Lock / Unlock device
- Lock device - `/api/lock`
- Unlock device - diferite metode de unlock, descrise mai jos

### 3. Check device status using MQTT
Listen topic `mosquitto_sub -t mytopic{deviceId}`

## Unlocking methods

### 1. Pin
- Validate code - `/api/pin/validate`
- Update code - `/api/pin/update`

### 2. Face Recognition
- Enable - `/api/face/enable`
- Add data (face images as url) - `/api/face/add`
- Validate face - `/api/face/validate`

### 3. Fingerprint
- Register data (fingerprints as base64) - `/api/fingerprint/register`
- Validate fingerprint - `/api/fingerprint/validate`

### 4. NFC
- Get NFC Secret - `api/nfc/get-secret`
- Register secret using a TOTP App - https://totp.danhersam.com 
- Validate code - `api/nfc/validate`

### 5. One Time Use Code
- Generate code - `api/otc/generate`
- Validate code - `api/otc/validate`

## Extra features

### Auto Block device
Daca intr-un interval de 30 de secunde sunt facute 3 incercari eronate de deblocare, dispozitivul este blocat pentru 30 de secunda <br/>
Daca acest lucru se intampla de 3 ori, dispozitivul este blocat pentru o ora.

### Security message
In momentul in care dispozitivul este blocat pentru o ora, se va trimite un Email de informare pe adresa de email asociata dispozitivului.
