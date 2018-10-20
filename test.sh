#
#  -Dorg.slf4j.simpleLogger.log.org.jboss.weld=warn \
#  -Dorg.jboss.weld.development=true \
#

mvn  \
  -Dorg.jboss.logging.provider=slf4j \
  -Dorg.slf4j.simpleLogger.log.org.eclipse.jetty=warn \
  -Dorg.slf4j.simpleLogger.log.org.jose=debug \
  test
