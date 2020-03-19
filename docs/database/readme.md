# Database Architecture and Notes

## Tasks

## Questions

- [x] What about `JOIN` sequence?

  > It is OK.

## Do it tomorrow (or never)

- recommendation by **like** service

- [ ] "Customers who bought this item also bought"

## Tips and Tricks

### Export Database from Docker

```bash
export CONTAINER="postgresql_postgres_1"
export PG_USER="postgres"
export DB_NAME="marketplace"

docker exec -it $CONTAINER pg_dump -U $PG_USER --schema-only --no-owner $DB_NAME > "${DB_NAME}_schema_pg_dump.sql"
```

## Links

- https://developer.paypal.com/docs/commerce-platform/
- [Как правильно считать деньги в базе данных / Роман Друзягин (404 Group)](https://www.youtube.com/watch?v=MwpYniXqthE)

## Notes

- `docs/database/marketplace_to_complicated.dbm` contains scratch of database with billing system, which looks to complicated to realize right now.

- `docs/database/double_entry_bookkeeping.dbm` contains database schema from [Как правильно считать деньги в базе данных / Роман Друзягин (404 Group)](https://www.youtube.com/watch?v=MwpYniXqthE)
