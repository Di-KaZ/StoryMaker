FROM node:lts-alpine AS build-stage

WORKDIR /app

COPY . .

RUN npm install

RUN npm run build

FROM nginx:stable-alpine AS production-stage

COPY --from=build-stage /app/dist /usr/share/nginx/html

EXPOSE 80

ENTRYPOINT ["nginx", "-g", "daemon off;"]