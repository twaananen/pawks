# PAWKS JAVA IMPLEMENTATION

# Database configuration
This service uses the [PostGIS](https://postgis.net/) database, which is PostgreSQL with preinstalled extensions to add geographic objects.

On first time setup, the extension has to be enabled by running this query in the database: `CREATE EXTENSION postgis;`