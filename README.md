Pentru mediul de dev este nevoie de urmatoarele:
- JetBrains IntelliJ
- Docker desktop
- Licenta jmix.io

Dupa instalarea docker trebuie adaugat un nou container pentru postgres:
docker run —name btdconstruct-portal -e POSTGRES_USER=cub_dev  -e POSTGRES_PASSWORD=password -e POSTGRES_DB=btd_portal_distrib
 -p 7432:5432 -d postgres
 Configurarile sunt deja facute in application.properties.
