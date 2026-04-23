# Bookstore Management

## Requirements
- Java 17+
- MySQL server running
- Either:
  - Maven, or
  - `mysql-connector-j-<version>.jar` in project root or `lib/`

## Configure Database (optional)
Defaults:
- `DB_HOST=localhost`
- `DB_PORT=3306`
- `DB_NAME=BookstoreManagement_db`
- `DB_USER=root`
- `DB_PASSWORD=` (empty)

Override with environment variables before running.

Example:
```bash
export DB_HOST=localhost
export DB_PORT=3306
export DB_NAME=BookstoreManagement_db
export DB_USER=root
export DB_PASSWORD=your_password
```

## Run
```bash
./run.sh
```

`run.sh` behavior:
- Uses local `mysql-connector-j` jar if present.
- Otherwise uses Maven (`mvn compile exec:java`) to fetch dependency and run.

## Notes
- The app auto-creates database and required tables on startup.
- If MySQL driver or DB credentials are invalid, startup now fails with a clear error message.
