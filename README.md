# spring-hms

We have 3 maven profiles configured
1. Dev profile which uses Postgresql-dev db
2. Test profile which uses H2 db
3. Prod profile which uses Postgresql-prod db

How to run either in dev or prod mode:
build the project using maven profile Dev then run HmsApplication.java so that it refers dev db 
build the project using maven profile Prod then run HmsApplication.java so that it refers prod db

