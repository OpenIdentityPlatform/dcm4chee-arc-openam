FROM openidentityplatform/openam

ENV CATALINA_OPTS="${CATALINA_OPTS} -DXUI.enable=false -agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n"

USER root

ADD wait-for-it.sh /usr/openam/

RUN chmod +x /usr/openam/wait-for-it.sh