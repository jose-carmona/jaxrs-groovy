[![Build Status](https://travis-ci.org/jose-carmona/ltr-jaxrs-groovy.svg?branch=master)](https://travis-ci.org/jose-carmona/ltr-jaxrs-groovy)

# ltr-jaxrs-groovy

Prueba de concepto de servicio REST para el cálculo de liquidaciones tributaria.

### Objetivos

* Sencillez en la sintaxis
* Fácilmente testeable

### Usaremos

* Servicio REST con jaxrs
* Markdown con Commonmark
* Groovy
* Test con cucumber

### Imagen Docker de WildFly para tests

* Build:

```
cd docker
docker build --tag=wildfly-tests .
```

* Run:

```
docker run -it -p 8080:8080 -p 9990:9990 --name wildfly-tests wildfly-tests
```

Administración de WildFly en http://localhost:9990.


### Tests

Asumimos que tenemos WildFly accesible en localhost:9990 (ver apartado anterior). Entonces simplemente:

```
mvn clean test -Parq-wildfly-remote
```
