# Tärinämittaus

Java Springillä Reactilla tehty lomakeprojekti, johon voi tallentaa tärinämittaus-tuloksia.

## Yleistä

Käyttöliittymä on tehty reactilla. Palvelin Java Spring Bootilla. Tietokantana PostgreSQL.

Projektin kansiorakenne pääpiirteissään:

- db: Sisältää sql skriptit kannan pystyttämiseen esim lokaalia kehitystä varten.
- src/main: Sisältää sekä frontendin kansiossa ```app``` ja backendin kansiossa ```java```
- src/main/resources Javan käyttämät asetustiedostot
- src/main/static Tänne siirretään buildattu frontend-projekti, jotta spring boot osaa tarjota sen selaimelle
- Dockerfile_postgres: Dockerfile lokaalin dockerin pystyttämiseen.
- Dockerfile: Paikallinen dockerfile
- Dockerfile.aws: AWS-ympäristössä ajettava dockerfile
- docker-compose.yaml: Pystyttää koko projektin paikallisesti

Palvelimen kansiorakenne:
- auth: Käyttäjän tunnistaminen json web tokenin avulla
- Specification: Asetukset hakuoperaatioita varten
- Util: dto - model mäppäykset käyttäen mapstruct pakettia <https://mapstruct.org/>
- validator: Kustomoitu validaatioyritelmä kutsuihin, ei käytössä tällä hetkellä.

Käyttöliittymän kansiorakenne:

- config: paths.js kopioitu tietokatalogista. Luultavasti ei tarvita, mutta alunperin julkaisussa oli ongelmia urlien määrityksissä ja tämä oli yksi yritelmä korjata ongelma
- environment: ympäristömuuttujia
- src: react komponentit
- Dockerfile: Tuotantojulkaisun dockerfile. Vain testikäytössä
- webpack.development.ts: webpack devaus konfiguraatiot
- webpack.production.ts: webpack tuotanto konfiguraatiot

## Git

Sovelluskoodi on repositoriossa <https://github.com/finnishtransportagency/tarinamittaus>.
Infrakoodi: https://github.com/finnishtransportagency/tietokatalogi-infra

### Branchit ja workflow

Työbranchi (pyritään nimeämään ANALPK-XXXX_lyhytkuvaus) -> dev -> master

- dev: Branchit mergetään tänne, tai pienemmät muutokset suoraan tänne. Päivittyy testipalvelimelle.
- master: Päivittyy tuotantopalvelimelle

## Komponentit

### Tietokanta

- Postgres
- Tietokannat: https://extranet.vayla.fi/wiki/pages/viewpage.action?pageId=17076833
- Suoran yhteyden muodostaminen tietokantoihin: https://extranet.vayla.fi/wiki/display/tietokatalogi/Kantayhteys+omalta+koneelta
- Paikallista kehitystä varten paikallinen kanta dockerilla
- Yhteysasetukset: https://extranet.vayla.fi/wiki/display/tietokatalogi/Tietokatalogi+Asennusohje ja aws secretsmanager

### Backend

- Java-versio 17
- Java Spring Boot 3.3.0
- Java Spring 6.1.8
- JPA 3.1.0
- Hibernate 6.5.2
- Hibernate Validator 8.0.1.Final
- tomcat-embed-core 10.1.24

### Frontend

- react 17.0.2
- mobx 6.3.0 <https://mobx.js.org/README.html>
- bootstrap 3.4.1 <https://getbootstrap.com/>
- formik 2.2.6 <https://formik.org/>
- typescript 4.1.2
- webpack 5.37.0

### Palvelin

Sovellusta ajetaan kontitettuna AWS:n ECS:ssä Väyläpilvi-ympäristössä.

## Julkaisu

Julkaisu testi- ja tuotantoympäristöihin tapahtuu puskemalla koodia dev- ja prod-haaroihin, vastaavasti.