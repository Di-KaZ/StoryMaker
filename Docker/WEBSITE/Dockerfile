FROM node:lts-alpine as build-stage

WORKDIR /app

COPY ./vuesaxwebsite/*.json ./vuesaxwebsite/babel.config.js ./

COPY ./vuesaxwebsite .

RUN npm install

RUN npm build

FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]