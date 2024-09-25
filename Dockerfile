# Usa la imagen base de WildFly de Bitnami
FROM bitnami/wildfly:33.0.1

# Copia el archivo .ear al contenedor en el directorio de despliegue de WildFly
COPY ear/target/practico1.ear /opt/bitnami/wildfly/standalone/deployments/

# Expone los puertos en los que WildFly escucha (8080 para la app, 9990 para el admin)
EXPOSE 8080 9990

# Comando para iniciar WildFly
CMD ["/opt/bitnami/scripts/wildfly/entrypoint.sh", "/opt/bitnami/scripts/wildfly/run.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]