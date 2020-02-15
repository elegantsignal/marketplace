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
docker exec -it $POSTGRESQL_CONTAINER pg_dump -U postgres --schema-only --no-owner marketplace > marketplace.sql
```
