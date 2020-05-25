# Short Docker HowTo

Setup postgresql container name as environment variable:

```bash
export POSTGRESQL_CONTAINER=marketplace_postgresql
```

- create database

  ```bash
  docker exec -it $POSTGRESQL_CONTAINER bash
  createdb -U postgres database_name

  docker exec -it $POSTGRESQL_CONTAINER psql -U postgres
  ```

```bash
createdb -U postgres book
```

```bash
psql -U postgres book
```

## Export

```bash
docker exec -it marketplace_postgresql pg_dump -U postgres --schema-only --no-owner marketplace > marketplace.sql
```

## psql

### Switch Database

```sql
\connect DBNAME
```

or

```sql
\c DBNAME
```

## Tomcat deploy

### `.env` file

```env
ASSETS_ROOT=/home/user/path/to/project/assets/on/the/host/machine/
```

## TODO

1. Build **war** inside container
