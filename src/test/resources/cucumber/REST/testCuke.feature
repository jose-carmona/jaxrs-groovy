# language: es
Característica: Uso de jetty + jaxrs + groovy + cucumber + rest-assured
  Como programador
  Quiero un test mínimo
  Para comprobar que el sistema de testeo funciona

  Hemos implementado un servicio 'test' que invoca un pequeño script Groovy que
  devuelve {"resultado":"4"}. Esto debe servir para comprobar que funciona:
    - jetty
    - REST sobre jaxrs
    - Groovy
    - cucumber
    - rest-assured
    - resultado en json

  Escenario: El servicio REST de test debe responder con un json tal que { "resultado" : "4" }
    Cuando invoco al servicio REST de test
    Entonces el API debe devolver un statusCode 200
      Y el API debe devolver un tipo de contenido json
      Y el API debe devolver un resultado igual a "4"
