# 🌐 Skopos Tech Radar

## 🌍 Warum Skopos Tech Radar?  

In einer Welt in der die technologische Entwicklung rasant voranschreitet, sehen wir uns täglich mit einer Flut an neuen Tools, Frameworks und Programmiersprachen konfrontiert. Für Entwickler, Teams und Unternehmen stellt sich die Frage: **Wie behalten wir den Überblick über all die Innovationen und deren Nutzen für uns?**  

Wir als Open-Source-Community wissen, dass Wissensaustausch und kontinuierliches Lernen die Grundlage für nachhaltige Entwicklung sind. Deshalb haben wir das **Skopos Tech Radar** ins Leben gerufen. Mithilfe unseres Dozenten welcher uns die Idee und Vorlage angeboten hatte, versuchten wir etwas weiterzuentwickeln um Usern ein einfaches und logisches Tracking von verschiedensten Dingen zu ermöglichen.

## 🪄 Woher kommt der Name Skopos?
Der Name Skopos hat seine Wurzeln im Altgriechischen und bedeutet so viel wie "Ziel", "Aussicht" oder "Zweck". 

Für uns als Entwickler-Community verkörpert Skopos genau diesen Gedanken:
- Wir wollen nicht einfach nur Daten sammeln sondern Technologie-Trends im Blick behalten.
- Wir wollen nicht nur Informationen verwalten, sondern sie gezielt nutzen.
- Wir wollen nicht nur passiv beobachten, sondern proaktiv die Zukunft gestalten.

## 🚀 Unsere Vision  
Skopos Tech Radar soll eine zentrale, kollaborative Plattform bieten, um Technologien systematisch zu bewerten und deren Entwicklung zu verfolgen. Statt die neuesten Trends und deren Potenziale in verstreuten Notizen, Excel-Listen oder sonstigen Website Tabs zu verwalten, schaffen wir einen **gemeinsamen Wissensraum** – immer einfach zugänglich und strukturiert.

### 💡 Use Cases  
- **Teamentscheidung:** Bei der Wahl neuer Technologien stehen viele Optionen zur Verfügung. Mit dem Tech Radar können Teams fundierte Entscheidungen treffen.  
- **Technologie-Monitoring:** Welche Tools und Frameworks werden von der Community unterstützt? Welche sind vielversprechend, welche sollten gemieden werden?  
- **Wissensmanagement:** Zentralisierte und kontinuierlich aktualisierte Informationen verhindern, dass wertvolles Wissen verloren geht.  
- **Open Source Spirit:** Mit einer offenen und transparenten Plattform möchten wir auch andere inspirieren, sich zu beteiligen und Technologien gemeinsam zu bewerten.  

## 🌱 Community-getrieben und nachhaltig  
Der Skopos Tech Radar ist mehr als nur ein Tool. Er ist eine **Initiative zur Förderung von Austausch und Transparenz**. Gemeinsam können wir Technologie-Trends besser verstehen und sinnvoll in Projekten einsetzen.  

Egal ob erfahrene Entwickler oder Teams, die nach Orientierung suchen – unser Ziel ist es, eine offene und verlässliche Ressource bereitzustellen, die allen hilft, die technologische Zukunft aktiv mitzugestalten.  

Lasst uns zusammen die Technologie von morgen entdecken


## 📋 Projektbeschreibung

Skopos Tech Radar ist eine webbasierte Anwendung zur Visualisierung und Verwaltung technologischer Trends im Unternehmen. Die Applikation basiert auf Quarkus und verwendet Keycloak für die Authentifizierung. Ziel ist es, Technologien (Programmiersprachen, Frameworks, Tools, Plattformen) systematisch zu erfassen, zu bewerten und nachzuverfolgen.

### 🎯 Qualitätsziele
- ✅ **Benutzerfreundlichkeit**: Einfache Bedienung für technische und nicht-technische Nutzer.
- 📈 **Skalierbarkeit**: Unterstützung wachsender Mengen an Technologien und Teams.
- 🔧 **Wartbarkeit**: Leicht erweiterbar für neue Technologien und Bewertungsmodelle.
- 🔒 **Sicherheit**: Geschützte Benutzerverwaltung und Berechtigungen.

### 👥 Stakeholder
- 💻 **Entwicklungsteam**: Implementierung und Pflege des Systems.
- 🧑‍💼 **Teamleiter / CTO**: Nutzung zur Technologiebewertung und Entscheidungshilfe.
- 👨‍🏫 **Mitarbeiter**: Beitrag zur Bewertung und Ergänzung neuer Technologien.

## 🏗️ Architektur
### 🗺️ Systemarchitektur
Die Anwendung ist als Microservice konzipiert und folgt einem Container-First-Ansatz. Das Backend basiert auf **Quarkus** und nutzt **Hibernate ORM** zur Datenpersistenz. Quarkus wird in einem **Docker-Container** ausgeführt und über **Kubernetes/OpenShift** bereitgestellt.

### 🛠️ Backend-Komponenten
- 🗃️ **RadarService**: Verwaltung und Speicherung von Radardaten.
- 🪧 **QuadrantService und RingService**: Strukturierung der Radardaten.
- 🌐 **REST-API**: Bereitstellung der Schnittstellen mit JAX-RS.
- 🗄️ **Datenbankzugriff**: Implementierung mit Panache, einer JPA-Erweiterung von Quarkus.

### 🌍 API-Endpunkte
- 📥 **GET /radars**: Liefert alle verfügbaren Radare.
- 🔍 **GET /radars/{radarId}**: Details zu einem spezifischen Radar.
- 📊 **GET /radars/data/{year}**: Radar-Daten eines bestimmten Jahres.
- ➕ **POST /radars**: Erzeugt ein neues Radar.
- ❌ **DELETE /radars/{radarId}**: Löscht ein Radar.

## 🗂️ Datenmodell
Die Hauptentitäten sind:
- 📌 **Radar**: Repräsentiert eine Sammlung von Technologien.
- 🧭 **Quadrant**: Bereich innerhalb eines Radars, z.B. Programmiersprachen.
- 🎯 **Ring**: Kategorisierung innerhalb eines Quadranten, z.B. "Adopt", "Trial", "Assess", "Hold".
- 📝 **RadarEntry**: Einzelne Technologie in einem Radar.

### 🗃️ Datenbankschema
Die PostgreSQL-Datenbank besteht aus den Tabellen `radar`, `quadrant`, `ring`, und `radar_entry`, wobei Beziehungen über Fremdschlüssel realisiert werden. Hibernate ORM übernimmt die Verwaltung der Relationen.

## 🔐 Sicherheitsaspekte
- 🛡️ **Authentifizierung**: Mittels Keycloak, Benutzerverwaltung über OpenID Connect.
- 🔑 **Zugangskontrolle**: Rollenbasiert, Admin-Benutzer mit erweiterten Berechtigungen.
- 🔏 **Passwortverwaltung**: Sicher über Kubernetes Secrets abgelegt.

## 🚀 Deployment und Infrastruktur
Die Applikation läuft in Containern und wird mit Kubernetes/OpenShift orchestriert. Die Bereitstellung erfolgt in mehreren Stages:
1. 🏗️ **Build**: Erzeugung des Quarkus-Bundles.
2. 📦 **Containerisierung**: Erstellung des Docker-Images.
3. 🌐 **Deployment**: Bereitstellung über Kubernetes.

### Local run

`mvn quarkus:dev` 

oder 

`quarkus dev`

Benötigt `docker` oder `podman` mit Docker-Kompatibilität. 


### 📂 Wichtige Konfigurationsdateien
- 📝 **docker-compose.yml**: Lokale Container-Orchestrierung.
- 🌐 **deployment.yaml**: Kubernetes-Deployment der Anwendung.
- 🔧 **configs.yaml**: Datenbankverbindungsparameter.
- 🛠️ **keycloak-deploy.yaml**: Keycloak-Deployment.

## 📄 Lizenz
Diese Software ist Open Source.
Entwickelt und gepflegt von PM4 TechRadar Team ;) 

