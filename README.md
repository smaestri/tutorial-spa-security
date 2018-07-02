# REST Authentication with Angular and Spring Securit

This project is the source code of the [REST Authentication with Angular and Spring Security](http://www.effectivecoding.fr) tutorial.
It shows how to make authentication working with Angular 6 and Spring Security 5.

## Build backend

Run `mvn clean install` on root project

## Build frontend

Run `npm install` in `frontend` to retrieve frontend dependencies
Run `ng build` in `frontend` folder to generate transpiled frontend files in `target/classes/static/`

## Run project

Run `mvn spring-boot:run` to run project

## Test REST Authentication

- Go to localhost:8080
- Try to click retrieve : you get a 403 since you're not authenticated
- Click Login and fill user with `username` and password with `password`
- Click retrieve : you're allowed to call ressource on server
- Click Logout
- Click Retrieve : you can not access resource since you are no more authenticated.

