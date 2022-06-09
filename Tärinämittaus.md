# Tärinämittaus

Java Springillä Reactilla tehty lomakeprojekti, johon voi tallentaa tärinämittaus tuloksia.

## Yleistä

Käyttöliittymä on tehty reactilla. Palvelin Java Spring Bootilla. Tietokantana Oracle.

Projektin kansiorakenne pääpiirteissään:

- db: Sisältää oracle skriptit kannan pystyttämiseen esim lokaalia kehitystä varten.
- deployment: Tiedostot  ja skriptit deploymista varten. Deploymislogiikka on kopioitu tietokatalogista. Ks kohta julkaisu
- lib: Oracle legacy ajurit
- src/main: Sisältää sekä käyttöliittymäkoodin kansiossa ```app``` ja palvelimen ```java```
- resources Javan käyttämät asetustiedostot
- build.sh: Skripti projektin buildaamiseen, käytetään julkaisuvaiheessa.
- Dockerfile_oracle: Dockerfile lokaalin dockerin pystyttämiseen.
- start_oracle_docker.sh: Skripti dockerin pystyttämiseen.

Palvelimen kansiorakenne:

- Configs: konfiguraatiot swaggeriin ja tietokantayhteyden muodostamiseen. HUOM! Tällä hetkellä softa osaa lukea konfiguraatiot vain ```PersistenceJPAConfig.java``` tiedostosta. Eli hibernate.cfg ei toimi jostain syystä. (5.7.2021)
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

Tällä hetkellä kaikki koodi on repositoriossa <https://github.com/finnishtransportagency/Tarinamittaus-Backend> Projektin toinen branchi <https://github.com/finnishtransportagency/Tarinamittaus-UI> on tällä hetkellä vielä tyhjä, otetaan käyttöön siinä vaiheessa kun migraatio on tehty aws:sään.

### Branchit ja workflow

Työbranchi (pyritään nimeämään ANALPK-XXXX_lyhytkuvaus) -> master -> test2 -> production

- downgrade: yhteensopiva Tomcat 7 palvelimen kanssa, julkaisut tästä branchista Väylään. Myöhemmin aws:n kanssa voidaan poistaa käytöstä
- test2: Testibranchi, myöhemmin julkaisut tästä testiympäristöön
- master: Työbranchit mergetään masteriin, josta tehdään taas puolestaan testiin ja tuotantoon
- production: Ei vielä luotu

## Komponentit

### Tietokanta

- Oracle 11g
- Testikanta on osoitteessa ```jdbc:oracle:thin:@***REMOVED***:1521:***REMOVED***```
- Skeema ```tarinam```

### Backend

Huom! Alla olevat versiot ovat downgrade branchistä, lukuunottamatta Javan versiota jolla ohjelma on tehty. Nämä ovat tarkoituksella alennettuja versioita yhteensopivuuden saamiseksi Väylän Tomcat 7 palvelimelle. <https://github.com/finnishtransportagency/Tarinamittaus-Backend/commit/4b658c2f7a93d00f5c8dc0c0043eda973c263c86>

- Java versio 1.8
- Java Spring Boot 1.5.22.RELEASE
- Java Spring 4.3.25.RELEASE
- JPA 2.1
- Hibernate 5.0.12.Final
- Hibernate Validator 5.3.6.Final
- tomcat-embed-core 7.0.47

### Frontend

- react 17.0.2
- mobx 6.3.0 <https://mobx.js.org/README.html>
- bootstrap 3.4.1 <https://getbootstrap.com/>
- formik 2.2.6 <https://formik.org/>
- typescript 4.1.2
- webpack 5.37.0

### Palvelin

Tomcat 7 väylän ylläpitämä. Siirtyy aws:sään myöhemmin.

## Julkaisu

Julkaisu tapahtuu samalla perjaattella kuin Tietokatalogiin. Muista käyttää oikeaa tiedostoa `xxx_test` tai `xxx_prod` riippuen ympäristöstä.

- password.txt tiedostoon kopioidaan salasana.
- ```cd deployment```
- ```inventory/inventory_test``` enkryptaus/dekryptaus tapahtuu komennolla ```ansible-vault encrypt/decrypt inventory/inventory_test```. HUOM inventory_test tiedostossa olevia muuttujia ei käytetä mihinkään tällä hetkellä, lukuunottamatta ktunnusta. Tietokatalogissa nämä kopioitaisiin hibernate.template.cfg:hen joka sitten puolestaan kopioidaan palvelimelle.
- `inventory_test` pitää olla enkryptattuna ennen julkaisun aloittamista.
- Aloita julkaisu ajamalla ```sh deploy_tarina_test.sh```
