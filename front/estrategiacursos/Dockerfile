FROM node:14 as builder

ENV NODE_TLS_REJECT_UNAUTHORIZED=0

RUN apt-get install make python g++ -y && \
    npm install -g @angular/cli@11.2.2


WORKDIR /app
COPY . /app
ARG AMBIENTE

RUN echo '*********************** pastas antes de buildar *****************'
RUN ls -la
RUN env
RUN npm install
RUN npm ci --verbose
RUN echo AMBIENTE=${AMBIENTE} > /tmp/ambiente
RUN ng build --configuration=${AMBIENTE}

RUN echo '&&&&&&&&&&&&&&&&&&&&&&&&&&&&& pastas apos o build ***************************************'
RUN ls -la
RUN ls -la /app/dist/frontend


FROM nginx:1.19-alpine-perl
## Remover site padrão
RUN rm -rf /usr/share/nginx/html/*

## Copiar conteúdo construído para pasta do servidor HTTP
COPY --from=builder /app/dist/frontend /usr/share/nginx/html
COPY --from=builder /tmp/ambiente /tmp/ambiente

RUN env
EXPOSE 80
