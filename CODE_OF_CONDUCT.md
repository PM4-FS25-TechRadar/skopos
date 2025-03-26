# Code of Conduct – Projekt Skopos

## Branches

- ``\*\* Branches\*\*

  - Für neue Features
  - Benennung: `feat/<kurzer-beschreibender-name>`

- ``\*\* Branches\*\*

  - Für Bugfixing
  - Benennung: `fix/<kurzer-beschreibender-name>`

- ``\*\* Branch\*\*

  - Staging Umgebung

## Commits – Konvention

Alle Commits folgen dem Schema:

```bash
<type>: <kurze Beschreibung>
```

### Gängige Commit-Typen:

| Typ          | Beschreibung                                               |
| ------------ | ---------------------------------------------------------- |
| `feat:`      | Neues Feature                                              |
| `fix:`       | Bugfix                                                     |
| `chore:`     | Allgemeine Aufgaben (z. B. Linting, Cleanups, Buildsystem) |
| `migration:` | Datenbankmigrationen, Schema-Änderungen mit Flyway etc.    |
| `docs:`      | Dokumentation (Änderungen an Markdown, READMEs etc.)       |
| `refactor:`  | Code-Refactoring ohne funktionale Änderung                 |
| `style:`     | Formatierung, Whitespaces, fehlende Semikolons etc.        |
| `test:`      | Tests hinzufügen oder anpassen                             |
| `perf:`      | Performance-Verbesserungen                                 |
| `build:`     | Buildsystem (Änderungen an z. B. Maven, npm)               |
| `ci:`        | CI/CD-Konfigurationen (z. B. GitHub Actions, Pipelines)    |

### Beispiel:

```bash
feat: implement authentication
```

## Pull Requests

- Jeder Merge nach `main` oder `dev` erfordert einen **Pull Request (PR)**.
- Jeder PR benötigt mindestens **1 Review durch eine andere Person**.
- Der Titel des PRs soll die wesentlichen Änderungen beschreiben.
- Kleinere, thematisch fokussierte PRs sind bevorzugt.

## Weekly & Reviews

- **Weekly Meeting**
  - 1× pro Woche zur Koordination, Demo, Feedback, Fragen.
  - Freitag 14:00 Uhr via Teams

